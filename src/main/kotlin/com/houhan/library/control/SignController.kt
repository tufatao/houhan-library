package com.houhan.library.control

import com.houhan.library.entity.Department
import com.houhan.library.repository.DepartmentRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
    val log: Logger = LoggerFactory.getLogger(SignController::class.java)
    @Autowired
    internal lateinit var departmentRepo: DepartmentRepo

    /**
     * 注册
     */
    @GetMapping("/signup")
    fun signup(model: Model): String {
        log.info("sign-signup")
        val deptList: List<Department> = departmentRepo.findAll()
        model.addAttribute("deptList", deptList)
        return "signup"
    }

    /**
     * 登录
     */
    @GetMapping("/login")
    fun signin(): String {
        log.info("sign-login")
        return "login"
    }

    /**
     * 找回密码
     */
    @GetMapping("/findpw")
    fun findPw(): String {
        log.info("sign-findPw")
        return "findpw"
    }

}
