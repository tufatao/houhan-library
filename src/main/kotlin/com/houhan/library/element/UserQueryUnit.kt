package com.houhan.library.element

/**
 * @author tufatao
 * @version V 0.1
 * @describe {}
 * @time 2017/9/18 9:52.
 */
data class UserQueryUnit(
        var name: String = "",
        var nickName: String = "",
        var mobile: String = "",
        var deptName: String = "",
        var roleName: String = "",
        var keyword: String = "",
        var sex: Int? = null
)
