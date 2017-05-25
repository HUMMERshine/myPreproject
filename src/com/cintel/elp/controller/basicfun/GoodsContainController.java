package com.cintel.elp.controller.basicfun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cintel.elp.common.ajax.AjaxRes;
import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.common.utils.base.Const;
import com.cintel.elp.controller.base.BaseController;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.GoodsContain;
import com.cintel.elp.service.basicfun.GoodsContainService;
import com.cintel.elp.service.basicfun.GoodsService;

@Controller
@RequestMapping("/backstage/goodscontain")
public class GoodsContainController extends BaseController<Goods> {

	@Autowired
	private GoodsContainService service;
	
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/goods/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<GoodsContain> page,GoodsContain o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/goods/index"))){
			try {
				Page<GoodsContain> result=service.findByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}	
		}
		return ar;
	}
	
}
