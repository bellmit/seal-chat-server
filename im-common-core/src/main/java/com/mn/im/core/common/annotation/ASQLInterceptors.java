package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ASQLInterceptors {

    ASQLInterceptor[] value();

}
