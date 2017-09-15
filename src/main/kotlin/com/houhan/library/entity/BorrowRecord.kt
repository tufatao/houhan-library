package com.houhan.library.entity

import com.houhan.library.helper.DateUtil
import java.util.*
import javax.persistence.*

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
        @ManyToOne
        val user: User,
        @ManyToOne
        val book: Book,
        var status: Int = AffairStatus.START.code,
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime,
        val returnTime: Date,
        var shouldReturnTime: Date = DateUtil.addMonth(createTime, 1)!!
)
