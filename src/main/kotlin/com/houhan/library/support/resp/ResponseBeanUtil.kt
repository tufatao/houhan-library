package com.houhan.library.support.resp

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/29 17:10.
 */
object ResponseBeanUtil {
    /**
     * 正常返回(无data)
     * @return
     */
    fun success(): ResponseBean<Unit> {
        return ResponseBean()
    }

    /**
     * 正常返回
     * @param p
     * *
     * @param <P>
     * *
     * @return
    </P> */
    fun <P> success(p: P): ResponseBean<P> {
        return ResponseBean(p)
    }


    /**
     * 捕获错误返回(无data)
     * @param resultEnum
     * *
     * @return
     */
    fun fail(resultEnum: ResultEnum): ResponseBean<Unit> {
        return ResponseBean(resultEnum.code, resultEnum.message)
    }

    /**
     * 未知异常返回(data 是 异常信息)
     * @param e
     * *
     * @return
     */
    fun exception(e: Throwable): ResponseBean<Throwable> {
        return ResponseBean(ResultEnum.UNKNOWN_FAIL.code, ResultEnum.UNKNOWN_FAIL.message, e)
    }
}
