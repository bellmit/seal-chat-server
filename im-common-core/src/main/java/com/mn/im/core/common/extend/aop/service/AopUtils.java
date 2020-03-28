package com.mn.im.core.common.extend.aop.service;

import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.utils.ArrayUtils;
import com.mn.im.core.common.utils.StringUtils;

public class AopUtils {

    public static void validParams(String value , String[] parameterNames , Object[] args) {
        if (StringUtils.isTrimBlank(value)) {
            throw new ServiceException("未获取到参数配置");
        }
        validParams(parameterNames,args);
    }

    public static void validParams(String[] values , String[] parameterNames , Object[] args) {
        if (ArrayUtils.isNullOrLengthZero(values)) {
            throw new ServiceException("未获取到参数配置");
        }
        for (String value : values) {
            if(StringUtils.isTrimBlank(value)) {
                throw new ServiceException("未获取到参数配置");
            }
        }
        validParams(parameterNames,args);
    }

    public static void validParams(String[] parameterNames , Object[] args) {
        if (parameterNames == null || parameterNames.length < 1) {
            throw new ServiceException("未获取到方法参数");
        }
        if (ArrayUtils.isNullOrLengthZero(args)) {
            throw new ServiceException("未获取到参数");
        }
    }

}
