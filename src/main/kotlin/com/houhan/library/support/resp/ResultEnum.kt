package com.houhan.library.support.resp

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/29 17:18.
 */
enum class ResultEnum private constructor(var code: String, var message: String) {
    UNKNOWN_FAIL("1", "未知错误"),
    BAD_REQUEST("400", "请求格式错误"),
    AUTH_FAIL("401", "非法请求"),
    SERVER_ERROR("500", "服务器异常"),
    SERVER_BUSY("503", "服务器繁忙, 请稍候再试")
}
