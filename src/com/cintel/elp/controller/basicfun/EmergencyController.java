package com.cintel.elp.controller.basicfun;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cintel.elp.common.ajax.AjaxRes;
import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.common.utils.base.Const;
import com.cintel.elp.controller.base.BaseController;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreCondition;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.service.basicfun.PreGoodsService;
import com.cintel.elp.service.basicfun.PreprojectService;
import com.cintel.elp.task.utils.svm.SVM;;

@Controller
@RequestMapping("/backstage/emergency/")
public class EmergencyController extends BaseController<Goods> {
	private List<Double> list = new ArrayList<>();
	
	@Autowired
	PreprojectService service;
	
	@Autowired
	PreGoodsService preGoods_service;

	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/basicfun/emergency/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	/*
	 * 进行预案的自动生成操作
	 */
	@RequestMapping("create")
	public String create(Model model, @RequestParam Map<String, Object> map) throws UnsupportedEncodingException {
		//model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
		
		/*
		 * svm文件存储路径
		 */
		String path = "/Users/lishutao/workspace/preproject/";
		
		/*
		 * 清空上次的预案数据。
		 */
		list.clear();
		
		Preproject preproject = new Preproject();
		PreCondition preCondition = new PreCondition();
		preCondition.setDisaster_kind(new String(((String)map.get("disaster_type")).getBytes("ISO-8859-1"),"UTF-8"));
		preCondition.setClimate(new String(((String)map.get("climate")).getBytes("ISO-8859-1"),"UTF-8"));
		preCondition.setGeography(new String(((String)map.get("geography")).getBytes("ISO-8859-1"),"UTF-8"));
		preCondition.setDisaster_area(Double.parseDouble((String)map.get("area")));
		preCondition.setDisaster_strength(Double.parseDouble((String)map.get("level")));
		preCondition.setDisaster_people(Integer.parseInt((String)map.get("people")));
		preproject.setPre_id("1000000001");
		preproject.setPre_time(Timestamp.valueOf((String)map.get("time")));
		preproject.setPreCondition(preCondition);
		preproject.setPre_name("default");
		preproject.setMatch("");
		PreGoods preGoods = new PreGoods();
		preGoods.setPreproject(preproject);
		
		/*
		 * 删除上次预测的结果，也即是删除编号1000000001的预案信息。
		 */
		preGoods_service.deleteByPreId(preGoods);
		service.delete(preproject);
		
		/*
		 * 进行svm预算，预测出受伤人数
		 */
		getData(list, map);
		double predict = SVM.getResult(path, list);
		
		/*
		 * 通过受伤人数计算出物资需求情况，进行数据库插入操作。
		 */
		service.getGoods(predict, preproject);
		service.insert(preproject);
		
		return "/basicfun/emergency/create";
	}

	/*
	 * 预案匹配操作
	 */
	@RequestMapping(value = "preprojectMatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes preprojectMatch(@RequestParam Map<String, Object> map) {
		/*
		 * String disaster_type, String disaster_level, String disaster_time,
		 * String people_density, String disaster_area, String
		 * disaster_geography, String disaster_climate, String disaster_info
		 */
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/emergency/index"))) {
			try {

				// Page<Preproject> result = service.match(map);
				// Page<Preproject> result = service.findByPage(new
				// Preproject(), new Page<Preproject>());
				Page<Preproject> result = service.match(map);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	/*
	 * 点击放弃预案后，进行清空10000000001号临时预案的操作
	 */
	@RequestMapping(value = "cleanPreproject", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes cleanPreproject(@RequestParam Map<String, Object> map) {
		/*
		 * String disaster_type, String disaster_level, String disaster_time,
		 * String people_density, String disaster_area, String
		 * disaster_geography, String disaster_climate, String disaster_info
		 */
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/emergency/index"))) {
			try {

				Preproject preproject = new Preproject();
				preproject.setPre_id("1000000001");
				PreGoods preGoods = new PreGoods();
				preGoods.setPreproject(preproject);
				
				preGoods_service.deleteByPreId(preGoods);
				service.delete(preproject);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	/*
	 * 格式化数据
	 */
	void getData(List<Double> list, Map<String, Object> map){
		
		String time = (String)map.get("time");
		String string = time.split("\\s")[1].split(":")[0];
		int t = Integer.parseInt(string);
		t = (t > 8 && t < 18) ? 1 : 2;
		
		Double people = Double.parseDouble((String)map.get("people"));
		Double area = Double.parseDouble((String)map.get("area"));
		Double density = people/area;
		
		double level = Double.parseDouble((String)map.get("level"));
		
		double mid_level = 9;
		double defend = 8;
		double build = 11078;
		double prediction = 1;
		
		list.add(level);
		list.add((double)t);
		list.add(mid_level);
		list.add(defend);
		list.add(people);
		list.add(density);
		list.add(build);
		list.add(prediction);
		list.add(0.0);
		
	}
}
