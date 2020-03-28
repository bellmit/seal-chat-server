package com.mn.im.core.common.extend.filter;

import com.mn.im.core.common.extend.config.WebProperties;
import com.mn.im.core.common.utils.StringUtils;
import com.mn.im.core.common.utils.UrlMatchingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: WebTokenFilter
 * @Description: 延长web token时长和验证
 * @date 2018/1/15
 */
@Slf4j
public class WebTokenFilter implements Filter {

    @Autowired
    private WebProperties webProperties;

    private String[] prefixIgnores;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<String> res = new ArrayList<>();
        if(webProperties.getNotAuthUrls() != null) {
            for (String notAuthUrl : webProperties.getNotAuthUrls()) {
                res.add(notAuthUrl);
            }
        }
        String[] arr = new String[]{"/","/index.html","/css/**","/fonts/**","/img/**","/js/**","/favicon.ico"};
        for (String notAuthUrl : arr) {
            res.add(notAuthUrl);
        }
        //将被过滤的资源  不用鉴权
        resource(res.toArray(new String[]{}));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            boolean result = false;
            /**
             * 如果不在过滤的资源中
             */
            String requestUri = request.getRequestURI();
            boolean ignores = UrlMatchingUtils.matching(requestUri,prefixIgnores);
            if(ignores){
                //拿到token
                String token = request.getHeader(webProperties.getTokenHeader());
                if(StringUtils.isNotTrimBlank(token)){

                }
            }else{
                result = true;
            }
            if(result)
                filterChain.doFilter(servletRequest,servletResponse);
            else{
                //鉴权失败返回401
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
            //鉴权失败返回401
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public void destroy() {

    }

    public void resource(String...params){
        prefixIgnores = params;
    }

}

