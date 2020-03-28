package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * Created by qiaohao on 17/3/7
 */
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {

    String value() default "";

    int sort() default 0;

    int [] types() default {};

    String codeType() default "";

    String[] typeValues() default {};

    String joinDelimiter() default "";

    String areaType() default "";

    String bizType() default "";

    boolean index() default  false;

}
