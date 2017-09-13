package com.houhan.library.entity

import com.houhan.library.helper.DateUtil
import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:15
 * @version V0.1
 */
@Entity
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String = "userA",
        val totalBorrowNum: Int = 5,
        val privilege: String = "*",
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime
)
