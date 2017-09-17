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
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var num: Long = id
    var name: String = ""
    var author: String = ""
    lateinit var icon: String
    var press: String = ""
    lateinit var description: String
    @ManyToOne
    lateinit var category: Category
    lateinit var keyword: String
    var status: Boolean = false
    val createTime: Date = DateUtil.curTime()!!
    var updateTime: Date = createTime
}