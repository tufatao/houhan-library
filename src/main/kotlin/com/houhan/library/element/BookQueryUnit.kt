package com.houhan.library.element

/**
 * @author tufatao
 * @version V 0.1
 * @describe {}
 * @time 2017/9/18 9:52.
 */
data class BookQueryUnit(
        var name: String = "",
        var author: String = "",
        var catName: String = "",
        var status: Int? = 1,
        var press: String = "",
        var keyword: String = ""
)