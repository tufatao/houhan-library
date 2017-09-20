package com.houhan.library.api

import com.houhan.library.entity.User
import com.houhan.library.resposity.UserRepo
import com.houhan.library.service.UserService
import com.houhan.library.web.ResultBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
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
    @Autowired
    lateinit var userRepo: UserRepo

    /**
     * 注册
     */
    @PostMapping("/signup")
    fun save(@ModelAttribute @NotNull user: User): ResultBean<User?> {
        val user = userService.save(user)
        return ResultBean(user)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): ResultBean<User?> {
        println("apply-detail")
        val user: User? = userRepo.findOne(id)
        user?.let {

        } ?: log.info("apply-detail: User(id = $id) not found")

        return ResultBean(user)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @RequestParam userId: Long,
            model: Model): ResultBean<Page<User>> {
        println("apply-list")

        val applyPage: Page<User> = userService.list(pageIndex, pageSize)

        return ResultBean(applyPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull user: User, model: Model): String {
        println("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): String {
        println("apply-delete")
        userRepo.delete(id)
        return "redirect:/apply"
    }

}
