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
class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @ManyToOne
    var user: User? = null
    @ManyToOne
    var book: Book? = null
    var status: Int = AffairStatus.START.code
    val createTime: Date = DateUtil.curTime()!!
    var updateTime: Date = createTime
    lateinit var returnTime: Date
    var shouldReturnTime: Date = DateUtil.addMonth(createTime, 1)!!
}
