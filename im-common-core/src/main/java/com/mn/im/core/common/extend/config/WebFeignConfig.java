package com.mn.im.core.common.extend.config;

import com.mn.im.core.common.extend.common.util.UserInfoUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author qiaomengnan
 * @ClassName: WebFeignConfig
 * @Description: feign配置 传入当前登录用户信息
 * @date 2018/1/7
 */

@Component
public class WebFeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(WebProperties.loginUserHeader(), UserInfoUtils.getUserStr());
        requestTemplate.header(WebProperties.chainHeader(), UserInfoUtils.getChainHeader());
    }

}
