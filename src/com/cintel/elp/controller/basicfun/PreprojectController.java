package com.cintel.elp.controller.basicfun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jplus.hyberbin.excel.PreprojectToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cintel.elp.common.ajax.AjaxRes;
import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.common.utils.base.Const;
import com.cintel.elp.common.utils.security.AccountShiroUtil;
import com.cintel.elp.controller.base.BaseController;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreApproval;
import com.cintel.elp.entity.basicfun.PreCondition;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.entity.system.resources.Resources;
import com.cintel.elp.service.basicfun.GoodsService;
import com.cintel.elp.service.basicfun.PreApprovalService;
import com.cintel.elp.service.basicfun.PreGoodsService;
import com.cintel.elp.service.basicfun.PreOperateService;
import com.cintel.elp.service.basicfun.PreprojectService;
import com.cintel.elp.service.basicfun.PreprojectServiceImp;
import com.cintel.elp.service.system.account.AccountService;
import com.cintel.elp.task.utils.GoodsExcel;
import com.cintel.elp.task.utils.PreOperateExcel;
import com.cintel.elp.task.utils.PreprojectExcel;
import com.cintel.elp.task.utils.redis.ListTranscoder;
import com.cintel.elp.task.utils.redis.ObjectsTranscoder;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/backstage/preproject/")
public class PreprojectController extends BaseController<Preproject> {
	private static int lishutao = 1;
	private String excelPath = "/Users/lishutao/Documents/excelexport/";
	Map<String, List<PreGoods>> preGoods_update_map = new HashMap<>();
	Map<String, List<PreGoods>> preGoods_delete_map = new HashMap<>();
	List<PreGoods> preGoods_update_list = new ArrayList<>();
	List<PreGoods> preGoods_delete_list = new ArrayList<>();

	@Autowired
	private PreprojectService preproject_service;

	@Autowired
	private PreOperateService preoperate_service;

	@Autowired
	private PreGoodsService pregoods_service;

	@Autowired
	private AccountService service4;

	@Autowired
	private GoodsService goods_service;

	/**
	 * 保存预案的修改数据，pre_id+time+type是key，value是预案内容或者物资内容
	 * type包括:insert、update、delete key：0000000001_2015-7-12 12:12:12_insert
	 * 
	 */
	private Jedis jedis = new Jedis("127.0.0.1", 6379);
	ObjectsTranscoder<Preproject> objTranscoder = new ObjectsTranscoder<>();
	ListTranscoder<PreGoods> listTranscoder = new ListTranscoder<>();
	HashMap<String, Preproject> preprojectMap = new HashMap<>();
	HashMap<String, List<PreGoods>> preGoodsMap = new HashMap<>();

	/**
	 * 缓存list.jsp的按钮和功能图标权限，主要是为了读取文件后刷新页面用。 signal主要起到标识该次访问是文件导入刷新功能的作用。
	 */
	private boolean signal = false;
	private List<Resources> tempList;
	private List<Resources> tempList2;

	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			tempList = getPermitBtn(Const.RESOURCES_TYPE_FUNCTION);
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/basicfun/preproject/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	@RequestMapping("logRecord")
	public String logRecord(Model model) throws UnsupportedEncodingException {
		System.out.println("++++++++++++++++++");
		if (doSecurityIntercept(Const.RESOURCES_TYPE_MENU)) {
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			System.out.println("++++++++++++++++++");

			return "/basicfun/logrecord/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<Preproject> page,
			@RequestParam(value = "keyWord", required = false) String keyWord) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				Preproject o = new Preproject();
				if (keyWord == null || keyWord.length() > 0)
					o.setPre_name(keyWord);
				Page<Preproject> result = preproject_service.findByPage(o, page);
				/**
				 * 导出所有预案信息。
				 */
				if (lishutao++ == 1) {
					// exportToExcel(result.getResults(), "预案基本信息表", "基本信息",
					// "预案基本信息");
					// importFormExcel();
				}
				Map<String, Object> p = new HashMap<String, Object>();

				/*
				 * 避免文件上传后刷新不出现按钮的bug
				 */
				if (signal) {
					p.put("permitBtn", tempList2);
					signal = false;
				} else {
					if (tempList2 == null && getPermitBtn(Const.RESOURCES_TYPE_BUTTON).size() != 0) {
						tempList2 = getPermitBtn(Const.RESOURCES_TYPE_BUTTON);
					}
					p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				}

				p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "findPreGoodsByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPreGoodsByPage(Page<PreGoods> page,
			@RequestParam(value = "pre_id", required = false) String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				System.out.println("********" + page + "__________ " + pre_id);
				PreGoods o = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				o.setPreproject(preproject);
				Page<PreGoods> result = pregoods_service.findByPage(o, page);
				System.out.println(result.getResults());
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

	@RequestMapping(value = "findPreGoodsByPage2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPreGoodsByPage2(@RequestParam(value = "pre_id2", required = false) String pre_id2) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				Page<PreGoods> page = new Page<>();
				System.out.println("********" + page + "__________ " + pre_id2);
				PreGoods o = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id2);
				o.setPreproject(preproject);
				Page<PreGoods> result = pregoods_service.findByPage2(o, page);
				System.out.println(result.getResults());
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

	@RequestMapping(value = "findPreGoodsByPage3", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPreGoodsByPage3(@RequestParam(value = "pre_id", required = false) String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				Page<PreGoods> page = new Page<>();
				System.out.println("********" + page + "__________ " + pre_id);
				PreGoods o = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				List<Preproject> list = preproject_service.find(preproject);
				o.setPreproject(preproject);
				Page<PreGoods> result = pregoods_service.findByPage3(o);
				result.getResults().get(0).setPreproject(list.get(0));
				System.out.println(result.getResults());
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

	@RequestMapping(value = "findPreGoods", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findPreGoods(Page<PreGoods> page, @RequestParam(value = "pre_id", required = false) String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				System.out.println("********" + page + "__________ " + pre_id);
				PreGoods o = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				o.setPreproject(preproject);
				List<PreGoods> list = pregoods_service.find(o);
				page.setResults(list);
				// System.out.println(page.getResults().size());
				System.out.println(page.getResults());
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", page);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "getFinalData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findFinalGoods(Page<PreGoods> page, @RequestParam(value = "pre_id", required = false) String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				System.out.println("********" + page + "__________ " + pre_id);
				PreGoods o = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				o.setPreproject(preproject);
				List<PreGoods> list = pregoods_service.findPreGoodsAll(o);
				// System.out.println(page.getResults().size());
				System.out.println(list);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list", list);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "updatePregoods", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePregoods_tmp(String amount, String preGoods_id, String pre_id, String code, String save_cycle,
			String priority) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				// System.out.println(preGoods_id + "********" + code +
				// "__________ " + pre_id);
				Goods goods = new Goods();
				goods.setCode(code);
				PreGoods preGoods = new PreGoods();
				preGoods.setGoods(goods);
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				preGoods.setPreproject(preproject);
				preGoods.setAmount(Double.parseDouble(amount));
				preGoods.setPre_goods_id(Integer.parseInt(preGoods_id));
				preGoods.setPriority(priority);
				preGoods.setSave_cycle(Integer.parseInt(save_cycle));

				//pregoods_service.update(preGoods);
				preGoods_update_list.add(preGoods);

				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				// p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "insertPregoods", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insertPregoods(String amount, String pre_id, String code, String save_cycle, String priority) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				// System.out.println(preGoods_id + "********" + code +
				// "__________ " + pre_id);
				Goods goods = new Goods();
				goods.setCode(code);
				PreGoods preGoods = new PreGoods();
				preGoods.setGoods(goods);
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				preGoods.setPreproject(preproject);
				preGoods.setAmount(Double.parseDouble(amount));
				preGoods.setPriority(priority);
				preGoods.setSave_cycle(Integer.parseInt(save_cycle));

				pregoods_service.insert(preGoods);

				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				// p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "deletePregoods", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes deletePregoods_tmp(String preGoods_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				System.out.println(preGoods_id + "********" + preGoods_id + "__________ " + preGoods_id);
				PreGoods preGoods = new PreGoods();
				preGoods.setPre_goods_id(Integer.parseInt(preGoods_id));

				//pregoods_service.delete(preGoods);
				preGoods_delete_list.add(preGoods);

				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				// p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "deletePregoodsByPreId", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes deletePregoodsByPreId(String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				PreGoods preGoods = new PreGoods();
				Preproject preproject = new Preproject();
				preproject.setPre_id(pre_id);
				preGoods.setPreproject(preproject);
				if (pre_id != null) {
					pregoods_service.deleteByPreId(preGoods);
				}
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				// p.put("list", result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(Preproject o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				List<Preproject> list = preproject_service.find(o);
				Preproject obj = list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	public boolean isExist(Preproject o) {
		List<Preproject> list = preproject_service.find(o);
		if (list.isEmpty())
			return true;
		return false;
	}

	public PreOperate putPreprojectMap(Preproject p, String operate, String operateName) {
		Timestamp time = new Timestamp(new Date().getTime());
		p.setPre_time(time);

		PreOperate preOperate = new PreOperate();
		preOperate.setPreproject(p);
		preOperate.setOperater_name(AccountShiroUtil.getCurrentUser().getName());
		preOperate.setOperate_type(operate);
		preOperate.setOperate_describe(operateName + ":" + p.toString());
		preOperate.setOperate_time(time);
		String key = "preproject" + "_" + p.getPre_id() + "_" + preOperate.getOperate_time().toString() + "_" + operate;
		/*
		 * pregoods--update和delete
		 */
		preGoods_update_map.put(key, new ArrayList<PreGoods>(preGoods_update_list));
		preGoods_update_list.clear();
		preGoods_delete_map.put(key, new ArrayList<PreGoods>(preGoods_delete_list));
		preGoods_delete_list.clear();
		
		jedis.set(key.getBytes(), objTranscoder.serialize(p));
		return preOperate;
	}

	public Preproject getPreprojectMap(PreOperate preOperate) {
		String pre_id = preOperate.getPreproject().getPre_id();
		String operate_time = preOperate.getOperate_time().toString();
		String operate_type = preOperate.getOperate_type();
		String key = "preproject" + "_" + pre_id + "_" + operate_time + "_" + operate_type;
		System.out.println("key:" + key);
		/*
		 * pregoods--update和delete
		 */
		preGoods_update_list = preGoods_update_map.get(key);
		preGoods_delete_list = preGoods_delete_map.get(key);
		preGoods_update_map.remove(key);
		preGoods_delete_map.remove(key);
		
		Preproject preProject = null;
		byte[] bytes = jedis.get(key.getBytes());
		if (bytes == null) {
			System.out.println("没有这个key");
		} else {
			preProject = objTranscoder.deserialize(bytes);
			jedis.del(key.getBytes());// 审核过了，不管结果都删除这个key。
		}
		return preProject;
	}
	
	public void updatePreGoods(){
		if(preGoods_update_list != null && preGoods_update_list.size() != 0){
			for (PreGoods preGoods : preGoods_update_list){
				pregoods_service.update(preGoods);
			}
		}
		preGoods_update_list.clear();
	}
	
	public void deletePreGoods(){
		if(preGoods_delete_list != null && preGoods_delete_list.size() != 0){
			for (PreGoods preGoods : preGoods_delete_list){
				pregoods_service.delete(preGoods);
			}
		}
		preGoods_update_list.clear();
	}

	/**
	 * 审核人员的审核后的结果操作。
	 * 
	 * @param preOperate
	 * @return
	 */
	@RequestMapping(value = "approval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes approvalPreproject(PreOperate preOperate) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				System.out.println("***********");
				System.out.println(preOperate);
				List<PreOperate> list = preoperate_service.find(preOperate);// 搜索出已经审核过的那条记录
				System.out.println("list:" + list);
				PreOperate updatedPreOperate = list.get(0);
				updatedPreOperate.setApproval_state(true);
				updatedPreOperate.setApproval_name(AccountShiroUtil.getCurrentUser().getName());
				updatedPreOperate.setApproval_proposal(preOperate.getApproval_proposal());
				updatedPreOperate.setApproval_result(preOperate.getApproval_result());
				updatedPreOperate.setApproval_time(new Timestamp(new Date().getTime()));
				Preproject preproject = getPreprojectMap(updatedPreOperate);// 删除这个key
				System.out.println("map:" + preprojectMap);
				System.out.println(preproject);

				if (preOperate.getApproval_result().equals("不通过")) {
					/*
					 * 清空链表，不进行更新操作。
					 */
					preGoods_update_list.clear();
					preGoods_delete_list.clear();
					
					// 仅更新操作数据库。
					if (updatedPreOperate != null) {
						preoperate_service.update(updatedPreOperate);
					}
				} else {
					/*
					 * 更新物资数据。
					 */
					updatePreGoods();
					deletePreGoods();
					
					// 更新两个数据库。
					if (preproject != null) {
						String operate_type = updatedPreOperate.getOperate_type();
						if (operate_type.equals("update")) {
							preproject_service.update(preproject);
						} else if (operate_type.equals("insert")) {
							preproject_service.insert(preproject);
						} else if (operate_type.equals("delete")) {
							preproject_service.delete(preproject);
						}
					}
					if (updatedPreOperate != null) {
						preoperate_service.update(updatedPreOperate);
					}
				}
				System.out.println(preprojectMap);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(@RequestBody Preproject p) {

		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {
				PreOperate preOperate = putPreprojectMap(p, "insert", "新增预案");
				preoperate_service.insert(preOperate);
				/*
				 * preproject_service.insert(preproject);
				 * ar.setSucceedMsg(Const.SAVE_SUCCEED);
				 */
				ar.setSucceedMsg(Const.DATA_APPROVAL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(Preproject o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				PreOperate preOperate = putPreprojectMap(o, "delete", "删除预案");
				preoperate_service.insert(preOperate);
				/*
				 * preproject_service.delete(o);
				 * ar.setSucceedMsg(Const.DEL_SUCCEED);
				 */
				ar.setSucceedMsg(Const.DATA_APPROVAL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(@RequestBody Preproject o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				PreOperate preOperate = putPreprojectMap(o, "update", "更新预案");
				preoperate_service.insert(preOperate);
				/*
				 * preproject_service.update(o);
				 * ar.setSucceedMsg(Const.UPDATE_SUCCEED);
				 */
				ar.setSucceedMsg(Const.DATA_APPROVAL);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "delBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))) {
			try {
				String[] chk = chks.split(",");
				List<Preproject> list = new ArrayList<Preproject>();
				for (String s : chk) {
					Preproject preproject = new Preproject();
					preproject.setPre_id(s);
					list.add(preproject);
				}
				preproject_service.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}

	public void importFormExcel(String filePath) {
		PreprojectToExcel pte = new PreprojectToExcel();
		try {
			/*
			 * String [] fileString = filePath.split("/");
			 * System.out.println("******"+Arrays.toString(fileString)); String
			 * [] stringArray =
			 * fileString[fileString.length-1].split("\\.")[0].split("-");
			 */
			Workbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
			List<PreprojectExcel> list = new ArrayList<>();
			Preproject preproject = null;
			PreCondition preCondition = null;

			boolean valid = pte.preprojectImport(workbook, list, PreprojectExcel.class, PreprojectExcel.getStrings(),
					"预案信息");
			if (valid) {
				for (PreprojectExcel school : list) {
					System.out.println(school);
					preCondition = school.getPreCondition();
					preproject = school.getPreproject();
				}
			} else {
				System.out.println("表单不存在");
			}
			/*
			 * 读取预案的物资信息（PreGoods）。
			 */
			String[] strings = new String[] { "20小时救援物资信息", "200小时救援物资信息", "200小时救援物资信息", "2000小时救援物资信息",
					"20000小时救援物资信息" };
			List<GoodsExcel> list2 = new ArrayList<>();
			List<PreGoods> preGoodsList = new ArrayList<>();
			for (int i = 0; i < strings.length; i++) {
				String s = strings[i];
				int save_cycle = 0;
				switch (i) {
				case 0:
					save_cycle = 20;
					break;
				case 1:
					save_cycle = 200;
					break;
				case 2:
					save_cycle = 2000;
					break;
				case 3:
					save_cycle = 20000;
					break;
				}
				valid = pte.preprojectImport(workbook, list2, GoodsExcel.class, GoodsExcel.getStrings(), s);
				if (valid) {
					for (GoodsExcel school : list2) {
						System.out.println(school);
						PreGoods preGoods = school.getPreGoods();
						preGoods.setPreproject(preproject);
						preGoods.setSave_cycle(save_cycle);
						preGoodsList.add(preGoods);
					}
				} else {
					System.out.println("表单" + s + "不存在");
				}
				list2.clear();
			}

			List<PreOperateExcel> list3 = new ArrayList<>();
			List<PreOperate> preOperateList = new ArrayList<>();
			valid = pte.preprojectImport(workbook, list3, PreOperateExcel.class, PreOperateExcel.getStrings(),
					"操作审核信息");
			if (valid) {
				for (PreOperateExcel preOperateExcel : list3) {
					System.out.println(preOperateExcel);
					PreOperate preOperate = preOperateExcel.getPreOperate();
					preOperate.setPreproject(preproject);
					preOperateList.add(preOperate);
				}
			} else {
				System.out.println("表单操作审核信息不存在");
			}
			/*
			 * System.out.println("***********");
			 * System.out.println(preproject); System.out.println(preCondition);
			 * System.out.println(preGoodsList);
			 */

			preproject.setPreCondition(preCondition);

			/*
			 * preOperate = new PreOperate("lishutao", "新建", new Timestamp(new
			 * Date().getTime()), false); preOperate.setPreproject(preproject);
			 */
			/*
			 * 防止重复写入预案
			 */
			if (isExist(preproject)) {
				/*
				 * 写数据库表：preproject和pre_condition。
				 */
				preproject_service.insert(preproject);
				/*
				 * 写数据库表：pre_googds。
				 */
				for (PreGoods preGoods : preGoodsList) {
					pregoods_service.insert(preGoods);
				}

				for (PreOperate preOperate : preOperateList) {
					preoperate_service.insert(preOperate);
				}
			} else {
				System.out.println("***********已存在");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "exportOne", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes exportOne(Preproject o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				exportOneImpl(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}

	@RequestMapping(value = "exportAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes exportAll() {
		AjaxRes ar = getAjaxRes();
		try {
			List<Preproject> list = preproject_service.findAll(new Preproject());
			System.out.println(list);
			for (Preproject o : list) {
				exportOneImpl(o);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(Const.DATA_FAIL);
		}
		return ar;
	}

	public void exportOneImpl(Preproject o) {
		PreprojectToExcel excel = new PreprojectToExcel();
		Workbook workbook = new HSSFWorkbook();

		try {
			List<Preproject> pres = preproject_service.find(o);
			preproject_service.preprojectExport(excel, workbook, pres, "预案信息", "预案基本信息");

			PreGoods preGoods = new PreGoods();
			preGoods.setPreproject(o);
			List<PreGoods> goods = pregoods_service.find(preGoods);
			List<PreGoods> goodsList = new ArrayList<>();
			for (int i = 0; i < goods.size(); i++) {
				goodsList.add(goods.get(i));
				if (i == goods.size() - 1 || !goods.get(i).getSave_cycle().equals(goods.get(i + 1).getSave_cycle())) {
					preproject_service.preGoodsExport(excel, workbook, goodsList,
							goods.get(i).getSave_cycle() + "小时救援物资信息", "物资基本信息");
					goodsList.clear();
				}
			}

			PreOperate preOperate = new PreOperate();
			preOperate.setPreproject(o);
			List<PreOperate> operates = preoperate_service.findByPreId(preOperate);
			if (operates.size() != 0) {
				preproject_service.preOperateExport(excel, workbook, operates, "操作审核信息", "预案的操作和审核记录");

			}
			/*
			 * 向sheet（预案信息表）内插入lists的内容，预案信息是表内的第一行标题
			 * workbook代表该excel，名字在fos中填写。
			 */
			FileOutputStream fos = new FileOutputStream(
					excelPath + (o.getPre_id() + "-" + pres.get(0).getPre_name()) + ".xls");
			workbook.write(fos);

			if (null != fos) {
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "findOperateByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findRecordByPage(Page<PreOperate> page,
			@RequestParam(value = "pre_id", required = true) String pre_id) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU, "/backstage/preproject/index"))) {
			try {
				System.out.println("xxxxxxxxxxxxx" + page + pre_id);
				// PreOperate o = new PreOperate();
				// PreGoods o = new PreGoods();
				PreOperate o = new PreOperate();
				Preproject pro = new Preproject();
				pro.setPre_id(pre_id);
				o.setPreproject(pro);
				// if(keyWord == null || keyWord.length() > 0)
				// o.setPre_name(keyWord);
				Page<PreOperate> result = preoperate_service.findByPage(o, page);

				/*
				 * if(lishutao++ == 1){ exportToExcel(result.getResults()); }
				 */
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

	@RequestMapping("fileUpload")
	public String filesUpload(Model model, @RequestParam("files") MultipartFile[] files) {
		// 判断file数组不能为空并且长度大于0
		System.out.println(files.length);
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				if (!file.isEmpty()) {
					try {
						// 文件保存路径 ,如果不是excel文件则跳过该文件。
						if (file.getOriginalFilename().indexOf(".xls") == -1)
							continue;
						String filePath = excelPath + file.getOriginalFilename();
						// 转存文件
						file.transferTo(new File(filePath));
						importFormExcel(filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("*****" + file.getOriginalFilename());
			}
		}
		// 重定向
		// model.addAttribute("permitBtn",
		// getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
		/*
		 * ModelAndView mav = new ModelAndView("/basicfun/preproject/list");
		 * mav.addObject("account", "account -1");
		 */
		model.addAttribute("permitBtn", tempList);
		signal = true;
		System.out.println("*****" + getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
		return "/basicfun/preproject/list";
	}

	/*
	 * @RequestMapping("fileUpload") public String
	 * springUpload(HttpServletRequest request) throws IllegalStateException,
	 * IOException { long startTime = System.currentTimeMillis(); // 将当前上下文初始化给
	 * CommonsMutipartResolver （多部分解析器） CommonsMultipartResolver
	 * multipartResolver = new CommonsMultipartResolver(
	 * request.getSession().getServletContext()); //
	 * 检查form中是否有enctype="multipart/form-data" if
	 * (multipartResolver.isMultipart(request)) { // 将request变成多部分request
	 * MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)
	 * request; // 获取multiRequest 中所有的文件名 Iterator iter =
	 * multiRequest.getFileNames();
	 * 
	 * while (iter.hasNext()) { // 一次遍历所有文件 MultipartFile file =
	 * multiRequest.getFile(iter.next().toString()); System.out.println("******"
	 * + file.getOriginalFilename()); if (file != null) { String path =
	 * excelPath + file.getOriginalFilename(); // 上传 file.transferTo(new
	 * File(path)); }
	 * 
	 * }
	 * 
	 * } long endTime = System.currentTimeMillis();
	 * System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) +
	 * "ms"); return "/basicfun/preproject/list"; }
	 */
	@RequestMapping(value = "emergency", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes emergency(String str) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))) {
			try {
				/*
				 * 事件解析、匹配、
				 */

				List<Preproject> list = preproject_service.find(new Preproject());
				Preproject obj = list.get(0);
				ar.setSucceed(obj);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
}
