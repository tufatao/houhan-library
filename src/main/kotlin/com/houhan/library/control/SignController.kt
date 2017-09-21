package com.houhan.library.control

import com.houhan.library.entity.Department
import com.houhan.library.resposity.DepartmentRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:24
 * @version V0.1
 */
@Controller
class SignController{
    @Autowired
    internal lateinit var departmentRepo: DepartmentRepo

    /**
     * 注册
     */
    @GetMapping("/signup")
    fun signup(model: Model): String {
        println("sign-signup")
        val deptList: List<Department> = departmentRepo.findAll()
        model.addAttribute("deptList", deptList)
        return "signup"
    }

    /**
     * 找回密码
     */
    @GetMapping("/findpw")
    fun findPw(): String {
        println("sign-findPw")
        return "findpw"
    }

    /**
     * 登录
     */
    @GetMapping("/login")
    fun signin(): String {
        println("sign-login")
        return "login"
    }
}