package com.houhan.library.control

import com.houhan.library.entity.User
import com.houhan.library.repository.UserBorrowRepo
import com.houhan.library.service.DepartmentService
import com.houhan.library.service.RoleService
import com.houhan.library.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/user")
@Controller
class UserController {
    //    val log: Logger = LoggerFactory.getLogger(UserController::class.java)
    val log: Logger = LoggerFactory.getLogger(Thread.currentThread().javaClass)
//    val log: Logger = LoggerFactory.getLogger(Thread.currentThread().stackTrace::class.java)

    @Autowired
    internal lateinit var userBorrowRepo: UserBorrowRepo
    @Autowired
    internal lateinit var roleService: RoleService
    @Autowired
    internal lateinit var departmentService: DepartmentService
    @Autowired
    internal lateinit var userService: UserService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): String {
        var result = ""
        println("user-detail")
        val user: User? = userService.one(id)
        user?.let {
            model.addAttribute("user", user)
            result = "/user/userlist"
        } ?: log.info("user-detail: User(id = $id) not found")
        return result
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    fun list(
            @PathVariable pageIndex: Int = 1,
            @PathVariable pageSize: Int = 10,
            model: Model): String {
        println("user-list")
        val userPage: Page<User> = userService.list(pageIndex, pageSize)
        model.addAttribute("userPage", userPage)
        return "/user/userlist"
    }

    @PostMapping()
    fun save(
            @Valid user: User,
            //            @ModelAttribute user: User,
            @RequestParam confirm_pw: String,
            @RequestParam deptId: Int,
            model: Model
    ): String {
        println("user-save")
        user.department = departmentService.one(deptId)!!
        user.role = roleService?.one("新秀")!!
        userBorrowRepo?.saveAndFlush(user.userBorrow)
        val user: User? = userService.save(user)
        user?.let {
            model.addAttribute("user", user)
            return "redirect:/user"
        } ?: log.info("user-save: User not found")
        return ""
    }

    @PutMapping()
    fun update(@RequestBody user: User): String {
        println("user-update")
        return "redirect:/user"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Long): String {
        println("user-delete")
        userService.delete(id)
        return "redirect:/user"
    }
}