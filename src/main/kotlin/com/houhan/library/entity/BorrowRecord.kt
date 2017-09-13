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
 * @date 2017/9/3 16:12
 * @version V0.1
 */
@Entity
data class BorrowRecord(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val user: User,
        val book: Book,
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime,
        val borrowTime: Date = createTime,
        val returnTime: Date,
        val shouldReturnTime: Date = DateUtil.addMonth(borrowTime, 1)!!
)
