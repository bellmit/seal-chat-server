package com.mn.im.core.common.extend.aop.service

import com.mn.im.core.common.annotation.ValidCodeRepeat
import com.mn.im.core.common.exception.ServiceException
import com.mn.im.core.common.extend.common.util.SpringUtils
import com.mn.im.core.common.utils.ArrayUtils
import com.mn.im.core.common.utils.ReflectUtils
import com.mn.im.core.common.utils.StringUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.slf4j.LoggerFactory

class KTValidCodeRepeatAopUtils {

    companion object {
        val log = LoggerFactory.getLogger(KTValidCodeRepeatAopUtils.javaClass)
        @Throws(Throwable::class)
        @JvmStatic fun around(joinPoint: ProceedingJoinPoint, validCodeRepeat: ValidCodeRepeat): Any? {
            val params = ArrayUtils.getFirst(joinPoint.args)
            if (params != null) {
                var validFlag: Boolean = true
                val codeRepeatConditionService: Class<out ValidCodeRepeatConditionService> = validCodeRepeat.conditionService.java
                if(codeRepeatConditionService != ValidCodeRepeatConditionService::class.java) {
                    validFlag = SpringUtils.getBean(codeRepeatConditionService).validCodeRepeatConditionCheck(joinPoint.args)
                }
                if(validFlag) {
                    val id: String = validCodeRepeat.id
                    if (StringUtils.isTrimBlank(id)) {
                        throw ServiceException("ID配置不能为空")
                    }
                    val code: String = validCodeRepeat.code
                    if (StringUtils.isTrimBlank(id)) {
                        throw ServiceException("CODE配置不能为空")
                    }
                    var errMsg: String? = validCodeRepeat.errMsg
                    errMsg = if (StringUtils.isNotTrimBlank(errMsg)) errMsg else "CODE发生重复,请调整"
                    val validCodeRepeatService: Class<out ValidCodeRepeatService> = validCodeRepeat.service.java
                    try {
                        validCodeRepeatService.asSubclass(ValidCodeRepeatService::class.java)
                    } catch (ex: ClassCastException) {
                        ex.printStackTrace()
                        log.error(ex.message)
                        throw ServiceException("验证子类设置错误")
                    }
                    val idValue = ReflectUtils.getFieldValue(id, params)
                    val codeValue = ReflectUtils.getFieldValue(code, params)
                    if(StringUtils.isTrimBlank(codeValue)) {
                        throw ServiceException("codeValue不能为空")
                    }
                    val service = SpringUtils.getBean(validCodeRepeatService)
                    val detailData = service.detailByCode(codeValue,joinPoint.args)
                    if (detailData != null) {
                        if (StringUtils.isTrimBlank(idValue) || StringUtils.notEquals(idValue, ReflectUtils.getFieldValue(id, detailData))) {
                            throw ServiceException(errMsg)
                        }
                    }
                }
            } else {
                throw ServiceException("参数不能为空")
            }
            return joinPoint.proceed()
        }
    }


}