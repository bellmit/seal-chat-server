package com.mn.im.core.common.extend.interceptor;

import com.mn.im.core.common.annotation.IgnorePathValid;
import com.mn.im.core.common.extend.common.util.SpringUtils;
import com.mn.im.core.common.extend.config.WebProperties;
import com.mn.im.core.common.utils.UrlMatchingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author qiaomengnan
 * @ClassName: RoleInterfaceInterceptor
 * @Description:
 * @date 2019-12-29
 */
@Slf4j
public class RoleInterfaceInterceptor implements HandlerInterceptor {


    private WebProperties webProperties;

    private String[] prefixIgnores;

    public RoleInterfaceInterceptor() {
        this.webProperties = SpringUtils.getBean(WebProperties.class);
        //将被过滤的资源  不用鉴权
        resource(webProperties.getNotAuthUrls());
    }

    public void resource(String...params){
        prefixIgnores = params;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        IgnorePathValid ignorePathValid = method.getAnnotation(IgnorePathValid.class);
        boolean ignore = false;
        if(ignorePathValid != null) {

        }
        if(!UrlMatchingUtils.matching(request.getRequestURI(),prefixIgnores)) {
            return true;
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("您没有权限访问[" + request.getRequestURI() + "]接口,如果有问题,请联系管理员");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {

    }
}
