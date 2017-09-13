package com.houhan.library.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:15
 * @version V0.1
 */
@Entity
data class UserBorrow(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val curBorrow: Int,
        val totalBorrow: Int = 5,
        val createTime: Date,
        var updateTime: Date
)
