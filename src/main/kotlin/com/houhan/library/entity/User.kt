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
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String,
        var pw: String,
        val sex: Int = 0,
        //生日
        val birth: String = "1996-02-28",
        var mobile: String,
        //所属部门
        @ManyToOne
        var department: Department,
        //职位
        var position: String,
        @OneToOne
        val userBorrow: UserBorrow,
        val createTime: Date = DateUtil.curTime()!!,
        var updateTime: Date = createTime,
        @ManyToOne
        var role: Role
)
