package com.cintel.elp.service.system.tool;

import com.cintel.elp.entity.system.tool.Email;
import com.cintel.elp.service.base.BaseService;

public interface EmailService extends BaseService<Email>{
	
	/**发送邮件（简单版）
     * @param o
     * @return
     */
	public boolean sentEmailSimple(Email o);

}
