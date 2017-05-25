package com.cintel.elp.controller.basicfun;

import java.io.UnsupportedEncodingException;
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
import com.cintel.elp.entity.basicfun.Disaster;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.service.basicfun.DisasterService;
import com.cintel.elp.service.basicfun.PreOperateService;
@Controller
@RequestMapping("/backstage/logRecord")
public class PreLogRecordController extends BaseController<PreOperate>{
	@Autowired
	private PreOperateService service;
	
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/basicfun/logrecord/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findNoApprovalByPage", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PreOperate> page,PreOperate o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/operate/index"))){
			try {
				Page<PreOperate> result=service.findNoApprovalByPage(o, page);
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
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(PreOperate o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				List<PreOperate> list=service.find(o);
				PreOperate obj=list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="findAll", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll(PreOperate o){
		AjaxRes ar=getAjaxRes();
		//if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				List<PreOperate> list=service.find(o);
				System.out.println(list);
				ar.setSucceed(list);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		//}
		return ar;
	}
}
