package com.cintel.elp.repository.basicfun;

import java.util.List;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis
public interface GoodsDao extends BaseDao<Goods> {
	public List<Goods> findByCode(Goods o);
}
