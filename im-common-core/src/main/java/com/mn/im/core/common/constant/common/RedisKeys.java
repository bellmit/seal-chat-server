package com.mn.im.core.common.constant.common;

/**
 * @author qiaomengnan
 * @ClassName: RedisKeys
 * @Description: redis key前缀
 * @date 2019/2/27 0027
 */
public class RedisKeys {

    /**
     * @Fields  : 用户token 使用UUID做前缀
     * @author qiaomengnan
     */
    public static final String USER_TOKEN_PREFIX = "dragon:userToken:token:";

    /**
     * @Fields  : 用户token 使用用户名做前缀
     * @author qiaomengnan
     */
    public static final String USER_TOKEN_NAME_PREFIX = "dragon:userToken:name:";

    /**
     * @Fields  : 忘记密码手机验证码check
     * @author qiaomengnan
     */
    public static final String FORGET_PASSWORD_PREFIX= "dragon:forgetPassword:code:";

    /**
     * @Fields  : 游客绑定手机号check
     * @author qiaomengnan
     */
    public static final String VISITOR_BIND_PREFIX= "dragon:visitorBind:code:";

}
