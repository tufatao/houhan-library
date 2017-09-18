package com.houhan.library.element

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:15
 * @version V0.1
 */
enum class AffairStatus(val code: Int) {
    START(1), REVIEWING(2), PASSED(3), REJECT(4), DONE(5)
}
