package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: ExcludeProperty
 * @Description: 排除掉某些属性
 * @date 2019/12/19
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcludeProperty {

    String[] value() default {};

    Class valueType() default String.class;

}
