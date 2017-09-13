package com.houhan.library.entity

import com.houhan.library.helper.DateUtil
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:12
 * @version V0.1
 */
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String,
        var pw: String,
        val sex: Int = 0,
        var mobile: String,
        var curBorrowNum: Int = 0,
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime,
        var role: Role
)
