package com.cintel.elp.repository.system.log;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.system.log.LoginLog;
import com.cintel.elp.repository.base.JYBatis;

@JYBatis
public interface LoginLogDao {

	public List<LoginLog> findByPage(@Param("param")LoginLog o,Page<LoginLog> page);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
