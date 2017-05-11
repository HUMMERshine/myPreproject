package com.cintel.elp.service.system.log;

import java.util.List;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.system.log.LoginLog;

public interface LoginLogService {

	public Page<LoginLog> findByPage(LoginLog o,Page<LoginLog> page);	
	
	public void saveLoginLog(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
