package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.repository.basicfun.PreGoodsDao;
import com.cintel.elp.service.base.BaseServiceImp;

@Service("PreGoodsService")
public class PreGoodsServiceImp extends BaseServiceImp<PreGoods> implements PreGoodsService {

	@Autowired
	private PreGoodsDao dao;
	
	@Override
	public void deleteByPreId(PreGoods preGoods) {
		// TODO Auto-generated method stub
		dao.deleteByPreId(preGoods);
	}

	@Override
	public Page<PreGoods> findByPage2(PreGoods o, Page<PreGoods> page) {
		// TODO Auto-generated method stub
		page.setResults(dao.findByPage2(o, page));
		return page;
	}
	
	@Override
	public Page<PreGoods> findByPage3(PreGoods o) {
		// TODO Auto-generated method stub
		Page<PreGoods> page = new Page<>();
		page.setResults(dao.findByPage3(o));
		return page;
	}

	@Override
	public List<PreGoods> findPreGoodsAll(PreGoods o) {
		// TODO Auto-generated method stub
		return dao.findByPage3(o);
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
