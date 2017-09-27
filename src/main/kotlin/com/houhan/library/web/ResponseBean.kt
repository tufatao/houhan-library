package com.houhan.library.web

import java.io.Serializable

/**
 * @author tufatao
 * @version V 0.1
 * @describe {}
 * @time 2017/9/20 11:39.
 */
class ResponseBean<T> : Serializable {
    companion object {
        private const val serialVersionUID = 1L
        val SUCCESS = 0
        val FAIL = 1
        val NO_PERMISSION = 2
    }

    var msg = "success"
    var code = SUCCESS
    var data: T? = null

    constructor() : super()
    constructor(data: T) : super() {
        this.data = data
    }

    constructor(e: Throwable) : super() {
        this.msg = e.toString()
        this.code = FAIL
    }
}
