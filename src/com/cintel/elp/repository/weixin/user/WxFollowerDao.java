package com.cintel.elp.repository.weixin.user;

import java.util.List;

import com.cintel.elp.entity.weixin.user.WxFollower;
import com.cintel.elp.repository.base.BaseDao;
import com.cintel.elp.repository.base.JYBatis;


/**
 * 微信关注者数据层
 */
@JYBatis
public interface WxFollowerDao  extends BaseDao<WxFollower>{
 
    public void clearFollower();
    
    public void insertFollowers(List<WxFollower> o);
}
