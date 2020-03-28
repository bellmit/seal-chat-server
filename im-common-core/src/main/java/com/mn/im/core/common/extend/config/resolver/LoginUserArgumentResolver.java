package com.mn.im.core.common.extend.config.resolver;

import com.mn.im.core.common.extend.common.annotation.AuthUserInfo;
import com.mn.im.core.common.extend.common.util.UserInfoUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author qiaomengnan
 * @ClassName: LoginUserArgumentResolver
 * @Description: 注入登录用户
 * @date 2018/1/7
 */

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AuthUserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws
            Exception {
        return UserInfoUtils.getUser(methodParameter.getParameterType());
    }

}
