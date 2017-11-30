package com.houhan.library.aop

import com.houhan.library.support.resp.ResponseBean
import com.houhan.library.support.resp.ResponseBeanUtil
import com.houhan.library.support.resp.ResultEnum
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/30 13:43.
 */
@Aspect
class ControllerAop {
    val log: Logger = LoggerFactory.getLogger(ControllerAop::class.java)
    //匹配com.houhan.library包及其子包中所有类中的所有方法，返回类型任意，方法参数任意 && 被RequestMapping注解的方法
//    @Pointcut("execution(* com.houhan.library..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    @Pointcut("execution(* com.houhan.library..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    fun requestMappingMethod() {

    }

    @Around("requestMappingMethod()")
    fun handlerControllerMethod(pjp: ProceedingJoinPoint): ResponseBean<*> {
        val startTime = System.currentTimeMillis()
        var result: ResponseBean<*>
        try {
            result = pjp.proceed() as ResponseBean<*>
            //            统一日志处理
            log.info(pjp.signature.toString() + " cost time:" + (System.currentTimeMillis() - startTime))
        } catch (e: Throwable) {
            //            统一异常处理
            result = handlerException(pjp, e)
        }

        return result
    }

    private fun handlerException(pjp: ProceedingJoinPoint, e: Throwable): ResponseBean<*> {
        var result: ResponseBean<*>
        // 已知异常 TODO
        if (e is NullPointerException) {
            result = ResponseBeanUtil.fail(ResultEnum.SERVER_ERROR)
        } else {
            log.error(pjp.signature.toString() + " error ", e)
            result = ResponseBeanUtil.exception(e)
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }

        return result
    }
}
