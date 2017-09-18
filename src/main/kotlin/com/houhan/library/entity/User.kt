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
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    lateinit var name: String
    lateinit var pw: String
    var sex: Int = 0
    //生日
    lateinit var birth: String
    lateinit var mobile: String
    //所属部门
    @ManyToOne
    lateinit var department: Department
    lateinit var keyword: String
    //职位
    lateinit var position: String
    @OneToOne
    var userBorrow: UserBorrow = UserBorrow()
    val createTime: Date = DateUtil.curTime()!!
    var updateTime: Date = createTime
    @ManyToOne
    lateinit var role: Role
}
