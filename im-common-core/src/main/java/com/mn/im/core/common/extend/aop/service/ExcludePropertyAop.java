package com.mn.im.core.common.extend.aop.service;

import com.mn.im.core.common.annotation.ExcludeProperty;
import com.mn.im.core.common.utils.ExpressionParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ExcludePropertyAop {

    @Pointcut("@annotation(com.mn.im.core.common.annotation.ExcludeProperty)")
    public void aspect() { }

    @Around("aspect() && @annotation(excludeProperty)")
    public Object around(ProceedingJoinPoint joinPoint,
                         ExcludeProperty excludeProperty) throws Throwable {
        String[] values = excludeProperty.value();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        // check
        AopUtils.validParams(values,parameterNames,args);
        for (String value : values) {
            ExpressionParserUtils.BuildExpData buildExpData = ExpressionParserUtils.buildExpData(value,parameterNames,args);
            ExpressionParserUtils.setValue(buildExpData.getExp(),buildExpData.getObjKey(),buildExpData.getObjValue(),null);
        }
        return joinPoint.proceed();
    }

}
