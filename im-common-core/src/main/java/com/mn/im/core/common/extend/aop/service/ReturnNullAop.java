package com.mn.im.core.common.extend.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ReturnNullAop {

    @Pointcut("@annotation(com.mn.im.core.common.annotation.ReturnNull)")
    public void aspect(){}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return KTReturnNullAopUtils.around(joinPoint);
    }

}
