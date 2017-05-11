package com.cintel.elp.repository.basicfun;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.entity.basicfun.Warehouse;
import com.cintel.elp.entity.system.dict.DataDict;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis
public interface PreprojectDao extends BaseDao<Preproject> {
	//public List<PreOperate> findIt(PreOperate page);
}
