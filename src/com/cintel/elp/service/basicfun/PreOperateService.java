package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.service.base.BaseService;

public interface PreOperateService extends BaseService<PreOperate>{
	//public Page<PreOperate> findIt(Page<PreOperate> page);
	public List<PreOperate> findByPreId(PreOperate o);

	public Page<PreOperate> findNoApprovalByPage(PreOperate o, Page<PreOperate> page);
}
