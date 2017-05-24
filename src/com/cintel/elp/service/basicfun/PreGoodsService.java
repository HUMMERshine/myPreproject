package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.service.base.BaseService;

public interface PreGoodsService extends BaseService<PreGoods>{

	void deleteByPreId(PreGoods preGoods);

	Page<PreGoods> findByPage2(PreGoods o, Page<PreGoods> page);

	Page<PreGoods> findByPage3(PreGoods o);
	
	List<PreGoods> findPreGoodsAll(PreGoods o);
}
