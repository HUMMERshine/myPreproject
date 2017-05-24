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
import com.cintel.elp.entity.basicfun.Warehouse;

@Controller
@RequestMapping("/backstage/statisticsAnalysis/")
public class StatisticsAnalysisController extends BaseController<Warehouse> {

	@RequestMapping("carindex")
	public String carindex(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/statisticsAnalysis/carStatistics";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping("goodsindex")
	public String goodsindex(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/statisticsAnalysis/goodsStatistics";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping("supplyindex")
	public String supplyindex(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/statisticsAnalysis/supplyStatistics";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping("warehouseindex")
	public String warehouseindex(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/statisticsAnalysis/warehouseStatistics";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping("reviewindex")
	public String reviewindex(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/statisticsAnalysis/reviewStatistics";
		}
		return Const.NO_AUTHORIZED_URL;
	}
}
