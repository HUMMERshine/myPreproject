package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreApproval;
import com.cintel.elp.service.base.BaseServiceImp;

@Service("PreApprovalService")
public class PreApprovalServiceImp extends BaseServiceImp<PreApproval> implements PreApprovalService {
	
	/*@Autowired
	private PreprojectDao dao;*/

	/*@Override
	public Page<PreOperate> findIt(Page<PreOperate> page) {
		List<PreOperate> list = dao.findIt(new PreOperate());
		System.out.println("=============");
		System.out.println(list.size() + list.toString());
		page.setResults(list);
		return page;
	}*/
	
	
}
