package com.cintel.elp.service.basicfun;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.GoodsContain;
import com.cintel.elp.service.base.BaseService;

public interface GoodsContainService  {

	Page<GoodsContain> findByPage(GoodsContain o, Page<GoodsContain> page);

}
