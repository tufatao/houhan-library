package com.houhan.library.api

import com.houhan.library.entity.User
import com.houhan.library.helper.DateUtil
import com.houhan.library.service.UserService
import com.houhan.library.web.ResultBean
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
    fun login(@RequestParam name: String, @RequestParam pw: String, response: HttpServletResponse): ResultBean<Map<String, Any>> {

        val countName = userService.countByName(name)
        val resultBean: ResultBean<Map<String, Any>> = ResultBean()
        resultBean.code = ResultBean.FAIL
        if (countName < 1) {
            resultBean.msg = "登录, 用户名($name)不存在!"
            return resultBean
        } else {
            val user = userService.loginCheck(name, pw)
            if (null != user) {
//                TODO 记录操作, 终端, Ip, 用户名, 时间
                log.info("登录, 用户名($name), time(${DateUtil.curTime()})")
//                response.addCookie(Cookie("USER_NAME", name))
//                response.addCookie(Cookie("USER_ID", "$user.id"))
                val logParam = mapOf("userName" to name, "roleName" to user.role.name, "userId" to user.id)
                return ResultBean(logParam)
            } else {
                resultBean.msg = "登录, 用户($name)密码错误!"
                return resultBean
            }
        }
    }

    @PostMapping("/signup")
    fun signup(@ModelAttribute @NotNull user: User): ResultBean<String> {
        val name = user.name
        val countName = userService.countByName(user.name)
        val countEmail = userService.countByName(user.email)
        val countMobile = userService.countByName(user.mobile)
        val resultBean: ResultBean<String> = ResultBean()
        resultBean.code = ResultBean.FAIL
        if (countName > 0) {
            resultBean.msg = "用户名($name)已存在!"
            return resultBean
        } else if (countEmail > 0) {
            resultBean.msg = "email已存在!"
            return resultBean
        } else if (countMobile > 0) {
            resultBean.msg = "手机号已存在!"
            return resultBean
        } else {
            userService.save(user)
//                TODO 记录操作, 终端, Ip, 用户名, 时间
            log.info("注册成功, 用户名($name), time(${DateUtil.curTime()})")
            return ResultBean(name)
        }
    }

}
