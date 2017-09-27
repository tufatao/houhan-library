package com.houhan.library.api

import com.houhan.library.entity.User
import com.houhan.library.helper.DateUtil
import com.houhan.library.service.UserService
import com.houhan.library.web.ResponseBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.constraints.NotNull

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/19 16:36.
 */
@RequestMapping("/api/sign")
@RestController
class SignApi {
    val log: Logger = LoggerFactory.getLogger(UserApi::class.java)

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestParam name: String, @RequestParam pw: String, response: HttpServletResponse): ResponseBean<Map<String, Any>> {

        val countName = userService.countByName(name)
        val responseBean: ResponseBean<Map<String, Any>> = ResponseBean()
        responseBean.code = ResponseBean.FAIL
        if (countName < 1) {
            responseBean.msg = "登录, 用户名($name)不存在!"
            return responseBean
        } else {
            val user = userService.loginCheck(name, pw)
            if (null != user) {
//                TODO 记录操作, 终端, Ip, 用户名, 时间
                log.info("登录, 用户名($name), time(${DateUtil.now()})")
//                response.addCookie(Cookie("USER_NAME", name))
//                response.addCookie(Cookie("USER_ID", "$user.id"))
                val logParam = mapOf("userName" to name, "roleName" to user.role.name, "userId" to user.id)
                return ResponseBean(logParam)
            } else {
                responseBean.msg = "登录, 用户($name)密码错误!"
                return responseBean
            }
        }
    }

    @PostMapping("/signup")
    fun signup(@ModelAttribute @NotNull user: User): ResponseBean<String> {
        val name = user.name
        val countName = userService.countByName(user.name)
        val countEmail = userService.countByName(user.email)
        val countMobile = userService.countByName(user.mobile)
        val responseBean: ResponseBean<String> = ResponseBean()
        responseBean.code = ResponseBean.FAIL
        if (countName > 0) {
            responseBean.msg = "用户名($name)已存在!"
            return responseBean
        } else if (countEmail > 0) {
            responseBean.msg = "email已存在!"
            return responseBean
        } else if (countMobile > 0) {
            responseBean.msg = "手机号已存在!"
            return responseBean
        } else {
            userService.save(user)
//                TODO 记录操作, 终端, Ip, 用户名, 时间
            log.info("注册成功, 用户名($name), time(${DateUtil.now()})")
            return ResponseBean(name)
        }
    }

}
