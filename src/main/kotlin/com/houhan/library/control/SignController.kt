package com.houhan.library.control

import com.houhan.library.entity.Department
import com.houhan.library.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:24
 * @version V0.1
 */
@RequestMapping("/sign")
@Controller
class SignController{
    @Autowired
    internal val departmentService: DepartmentService? = null
    @GetMapping("/signup")
    fun signup(model: Model): String {
        println("sign-signup")
        val deptList: List<Department> = departmentService?.list()!!
        model.addAttribute("deptList", deptList)
        return "signup"
    }

    @GetMapping("/findpw")
    fun findPw(): String {
        println("sign-findPw")
        return "findpw"
    }

    @GetMapping("/login")
    fun signin(): String {
        println("sign-login")
        return "login"
    }
}