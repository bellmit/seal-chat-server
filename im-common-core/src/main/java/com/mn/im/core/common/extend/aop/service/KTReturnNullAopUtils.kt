
package com.mn.im.core.common.extend.aop.service

import com.mn.im.core.common.utils.ArrayUtils
import com.mn.im.core.common.utils.StringUtils
import org.aspectj.lang.ProceedingJoinPoint

class KTReturnNullAopUtils {

    companion object {
        @Throws(Throwable::class)
        @JvmStatic fun around(joinPoint: ProceedingJoinPoint): Any? {
            val objects = joinPoint.args
            if (ArrayUtils.isNotNullAndLengthNotZero(objects)) {
                if (StringUtils.isTrimBlank(objects[0])) {
                    return null
                }
            }
            return joinPoint.proceed()
        }
    }

}