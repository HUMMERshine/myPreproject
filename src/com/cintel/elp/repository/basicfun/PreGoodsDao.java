package com.cintel.elp.repository.basicfun;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis
public interface PreGoodsDao extends BaseDao<PreGoods> {
}
