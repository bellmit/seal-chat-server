package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: SQLLike
 * @Description:
 * @date 2019-12-04
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLLike {

    String value() default  "";

}
