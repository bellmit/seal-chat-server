package com.mn.im.core.common.extend.aop.rest;

import com.mn.im.core.base.BaseEntity;
import com.mn.im.core.base.BaseVo;
import com.mn.im.core.common.extend.response.RestResponse;
import com.mn.im.core.common.utils.PageInfoExtend;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: RestResultAop
 * @Description:
 * @date 2019/12/12
 */
@Component
@Aspect
public class RestResultAop {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aspect(){}


    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        if(obj instanceof ResponseEntity) {
            ResponseEntity res = (ResponseEntity)obj;
            if(res.getBody() instanceof RestResponse) {
                RestResponse restResponse = (RestResponse)res.getBody();
                Object restData = restResponse.getData();
            }
        }
        return obj;
    }

    /**
     * @Title:
     * @Description:   根据常见的返回类型,查找实体类,并进行关键字屏蔽
     * @param restData
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/12 05:25:13
     */
    private void filterData(Object restData) {
        if(restData != null) {
            if(restData instanceof List) {

            } else if(restData instanceof Map) {

            } else if(restData instanceof PageInfoExtend) {

            } else if(restData instanceof BaseVo) {

            } else if(restData instanceof BaseEntity) {

            }
        }
    }

}
