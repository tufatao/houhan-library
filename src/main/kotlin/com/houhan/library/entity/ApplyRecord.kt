package com.houhan.library.entity

import com.houhan.library.element.AffairStatus.START
import com.houhan.library.element.ApplyType.BORROW
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
class ApplyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @ManyToOne
    lateinit var user: User
    @ManyToOne
    lateinit var book: Book
    @OneToOne
    var borrowRecord: BorrowRecord? = null
    var type: Int = BORROW.code
    var status: Int = START.code
    var applyRemark: String = ""
    var reviewRemark: String = ""
    val createTime: Date = DateUtil.now()!!
    var updateTime: Date = createTime
    var endTime: Date? = null
}
