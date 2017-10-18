package com.houhan.library.entity

import com.houhan.library.helper.DateUtil
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Size

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:15
 * @version V0.1
 */
@Entity
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Size(min = 2, max = 30)
    lateinit var name: String
    @Size(max = 255)
    lateinit var description: String
    var totalBorrowNum: Int = 5
    var privilege: String = ""
    val createTime: Date = DateUtil.now()!!
    var updateTime: Date = createTime
}
