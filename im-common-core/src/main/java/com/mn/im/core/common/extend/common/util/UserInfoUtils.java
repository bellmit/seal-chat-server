package com.mn.im.core.common.extend.common.util;

import com.alibaba.fastjson.JSON;
import com.mn.im.core.base.BaseUser;
import com.mn.im.core.common.extend.config.WebProperties;
import com.mn.im.core.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author qiaomengnan
 * @ClassName: UserInfoUtil
 * @Description: 对当前登录用户进行操作
 * @date 2018/1/7
 */
@Slf4j
@Component
public class UserInfoUtils {

	private static UserInfoUtils userInfoUtils = null;


	public static Object getUser(Class entityClass) {
		String userInfoStr = getUserStr();
		if(StringUtils.isTrimBlank(userInfoStr))
			return null;
		try {
			userInfoStr = URLDecoder.decode(userInfoStr, "utf-8");
			Object userInfo = JSON.toJavaObject(JSON.parseObject(userInfoStr), entityClass);
			return userInfo;
		} catch (Exception ex) {
            log.error(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public static String getUserStr(){
		HttpServletRequest request = RequestUtils.getRequest();
		if(request != null) {
			String loginUser = StringUtils.getValue(request.getAttribute(WebProperties.loginUserHeader()));
			return loginUser;
		}
		return null;
	}

	public static String getChainHeader(){
		HttpServletRequest request = RequestUtils.getRequest();
		if(request != null) {
			String chainHeader = request.getHeader(WebProperties.chainHeader());
			return chainHeader;
		}
		return null;
	}


	public static BaseUser getUser(){
		BaseUser user = (BaseUser) userInfoUtils.getUser(BaseUser.class);
		return user;
	}


	public static String getChain(){
		return userInfoUtils.getChainHeader();
	}


	public static String getUserName(){
		BaseUser user = getUser();
		if(user == null)
			return null;
		else
			return user.getUserName();
	}

	public static String getUserId(){
		return null;
	}

	public static void setUserInfoUtils(UserInfoUtils userInfoUtils){
		UserInfoUtils.userInfoUtils = userInfoUtils;
	}

	public static UserInfoUtils getUserInfoUtils(){
		return userInfoUtils;
	}

}
