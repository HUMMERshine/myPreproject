package com.cintel.elp.repository.basicfun;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis
public interface PreGoodsDao extends BaseDao<PreGoods> {

	void deleteByPreId(PreGoods preGoods);

	List<PreGoods> findByPage2(@Param("param")PreGoods o, Page<PreGoods> page);

	List<PreGoods> findByPage3(@Param("param")PreGoods o);
}
