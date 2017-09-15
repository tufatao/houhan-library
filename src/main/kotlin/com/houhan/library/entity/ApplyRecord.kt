package com.houhan.library.entity

import com.houhan.library.entity.AffairStatus.START
import com.houhan.library.entity.ApplyType.BORROW
import com.houhan.library.helper.DateUtil
import java.util.*
import javax.persistence.*

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:13
 * @version V0.1
 */
@Entity
data class ApplyRecord(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @ManyToOne
        val user: User,
        @ManyToOne
        val book: Book,
        val type: Int = BORROW.code,
        var status: Int = START.code,
        var applyRemark: String,
        var reviewRemark: String,
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime,
        var endTime: Date
)
