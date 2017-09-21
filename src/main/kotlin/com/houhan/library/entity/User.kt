package com.houhan.library.entity

import com.houhan.library.helper.DateUtil
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Range
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

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
    @Size(min = 3, max = 60)
    lateinit var name: String
    @Size(min = 6, max = 50)
    lateinit var pw: String
    @Range(min = 0, max = 2, message = "性别超出已知范畴!")
    var sex: Int = 0
    @Email(message = "email格式错误")
    lateinit var email: String
    //生日
    @Pattern(regexp = "^[12]\\d{3}-1?\\d-[123]?\\d$", message = "生日格式错误")
    @Past
    lateinit var birth: String
    @Size(max = 500)
    var evaluation: String = ""
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号码格式错误")
    lateinit var mobile: String
    //所属部门
    @ManyToOne
    lateinit var department: Department
    var keyword: String = ""
    //职位
    @Size(max = 50)
    lateinit var position: String
    @OneToOne
    var userBorrow: UserBorrow = UserBorrow()
    @Past
    val createTime: Date = DateUtil.curTime()!!
    var updateTime: Date = createTime
    @ManyToOne
    lateinit var role: Role
}
