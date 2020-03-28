package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: IgnorePathValid
 * @Description:
 * @date 2019-12-29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnorePathValid {

    String value() default "";

}
