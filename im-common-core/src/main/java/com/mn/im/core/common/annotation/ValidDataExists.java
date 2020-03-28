package com.mn.im.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: ValidDataExists
 * @Description:
 * @date 2019-12-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDataExists {

    ValidDataExist [] value();

}
