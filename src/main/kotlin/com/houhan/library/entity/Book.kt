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
    lateinit var name: String
    lateinit var author: String
    var icon: String = ""
    lateinit var press: String
    var description: String = ""
    @ManyToOne
    var category: Category? = null
    var keyword: String = ""
    var status: Int = 1
    val createTime: Date = DateUtil.now()!!
    var updateTime: Date = createTime
}