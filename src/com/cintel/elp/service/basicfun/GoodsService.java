package com.cintel.elp.service.basicfun;

import java.util.List;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.service.base.BaseService;

public interface GoodsService extends BaseService<Goods> {

	List<Goods> findByCode(Goods o);

}
