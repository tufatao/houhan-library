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
class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    lateinit var name: String
    lateinit var description: String
    @ManyToOne
    lateinit var parentDept: Department
    val createTime: Date = DateUtil.curTime()!!
    var updateTime: Date = createTime
}
