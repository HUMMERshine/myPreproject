package com.cintel.elp.controller.basicfun;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.service.basicfun.PreprojectService;;

@Controller
@RequestMapping("/backstage/emergency/")
public class EmergencyController extends BaseController<Goods> {

	@Autowired
	PreprojectService service;
	
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		System.out.println("*********");
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/basicfun/emergency/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	@RequestMapping(value = "preprojectMatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes preprojectMatch(@RequestParam Map<String, Object> map) {
		/*String disaster_type, String disaster_level, String disaster_time,
			String people_density, String disaster_area, String disaster_geography, String disaster_climate,
			String disaster_info*/
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/emergency/index"))) {
			try {
				
				System.out.println("-----------" + map);
				//Page<Preproject> result =  service.match(map);
				//Page<Preproject> result =  service.findByPage(new Preproject(), new Page<Preproject>());
				Page<Preproject> result = service.match(map);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
}
