package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.repository.basicfun.PreOperateDao;
import com.cintel.elp.repository.basicfun.PreprojectDao;
import com.cintel.elp.service.base.BaseServiceImp;

@Service("PreOperateService")
public class PreOperateServiceImp extends BaseServiceImp<PreOperate> implements PreOperateService {

	@Autowired
	PreOperateDao preOperateDao;
	
	@Override
	public List<PreOperate> findByPreId(PreOperate o) {
		// TODO Auto-generated method stub
		return preOperateDao.findByPreId(o);
	}

	@Override
	public Page<PreOperate> findNoApprovalByPage(PreOperate o, Page<PreOperate> page) {
		// TODO Auto-generated method stub
		page.setResults(preOperateDao.findNoApprovalByPage(o, page));
		return page;
	}
	
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
