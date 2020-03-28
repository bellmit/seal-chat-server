package com.mn.im.core.common.extend.aop.service;

import com.mn.im.core.common.annotation.ValidCodeRepeat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author qiaomengnan
 * @ClassName: ValidCodeRepeatAop
 * @Description: 判断某code是否重复
 * @date 2019/12/14
 */
@Slf4j
@Component
@Aspect
public class ValidCodeRepeatAop {

    @Pointcut("@annotation(com.mn.im.core.common.annotation.ValidCodeRepeat)")
    public void aspect(){}

    @Around("aspect() && @annotation(validCodeRepeat)")
    public Object around(ProceedingJoinPoint joinPoint, ValidCodeRepeat validCodeRepeat) throws Throwable {
        return KTValidCodeRepeatAopUtils.around(joinPoint,validCodeRepeat);
    }

}
