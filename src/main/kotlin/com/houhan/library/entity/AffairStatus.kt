package com.houhan.library.entity

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:15
 * @version V0.1
 */
enum class AffairStatus(val code: Int) {
    START(1), REVIEWED(2), RUNNING(3), DONE(0)
}
