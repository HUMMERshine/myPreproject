package com.cintel.elp.repository.oa.leave;

import org.apache.ibatis.annotations.Param;

import com.cintel.elp.entity.oa.leave.Leave;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;
/**
 * 请假数据层
 */
@JYBatis
public interface LeaveDao extends BaseDao<Leave>{
	
	public Leave findLeaveByPId(@Param("pId")String pId);
	
	public void updateRejectReason(Leave leave);
	
	public void updateDescription(Leave leave);
}
