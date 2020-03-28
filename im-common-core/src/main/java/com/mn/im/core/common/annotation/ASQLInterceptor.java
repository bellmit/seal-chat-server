package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: ASQLInterceptor
 * @Description: SQL拦截
 * @date 2019/12/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ASQLInterceptors.class)
public @interface ASQLInterceptor {

    String value() default "";

    String left() default "";

}


