package com.cintel.elp.repository.basicfun;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis("preOperateDao")
public interface PreOperateDao extends BaseDao<PreOperate> {
	public List<PreOperate> findByPreId(PreOperate o);

	public List<PreOperate> findNoApprovalByPage(@Param("param")PreOperate o, Page<PreOperate> page);

}
