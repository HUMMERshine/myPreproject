package com.cintel.elp.repository.task.log;

import com.cintel.elp.entity.task.log.TaskLog;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;
/**
 * 动态任务数据层
 */
@JYBatis
public interface TaskLogDao extends BaseDao<TaskLog>{
	
}
