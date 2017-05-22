package com.cintel.elp.service.basicfun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.repository.basicfun.GoodsDao;
import com.cintel.elp.service.base.BaseServiceImp;

@Service("GoodsService")
public class GoodsServiceImpl extends BaseServiceImp<Goods> implements GoodsService {

	@Autowired
	GoodsDao goodsDao;
	
	@Override
	public List<Goods> findByCode(Goods o) {
		// TODO Auto-generated method stub
		List<Goods> list = goodsDao.findByCode(o);
		return list;
	}

}
