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
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    lateinit var name: String
    lateinit var description: String
    @ManyToOne
    var parentCat: Category? = null
    val createTime: Date = DateUtil.now()!!
    var updateTime: Date = createTime
}