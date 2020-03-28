package com.mn.im.core.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author qiaomengnan
 * @ClassName: MD5Utils
 * @Description:
 * @date 2019-04-25
 */
public class MD5Utils {


	public static String MD5(String value){
		return DigestUtils.md5Hex(value);
	}


}
