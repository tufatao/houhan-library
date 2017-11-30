package com.houhan.library.support.resp


/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/29 17:01.
 */

class ResponseBean<P> {
    var code = "0"
    var message = "成功"
    var p: P? = null

    constructor(p: P) {
        this.p = p
    }

    constructor(code: String, message: String) {
        this.code = code
        this.message = message
    }

    constructor(code: String, message: String, p: P?) {
        this.code = code
        this.message = message
        this.p = p
    }

    constructor()

}
