package com.edwin.security.core.social.weixin.api;

/**
 * Created by wenpuzhao on 2019/2/2.
 */
public interface Weixin {
    /* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
    WeixinUserInfo getUserInfo(String openId);
}
