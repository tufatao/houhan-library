package com.houhan.library.api

import com.houhan.library.element.UserQueryUnit
import com.houhan.library.entity.User
import com.houhan.library.repository.UserRepo
import com.houhan.library.service.UserService
import com.houhan.library.web.ResponseBean
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
@RequestMapping("/api/user")
@RestController
class UserApi {
    val log: Logger = LoggerFactory.getLogger(UserApi::class.java)

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var userRepo: UserRepo

    @PostMapping("/update")
    fun update(@ModelAttribute @NotNull user: User): ResponseBean<Unit> {
        userRepo.save(user)
        return ResponseBean()
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): ResponseBean<User?> {
        println("user-detail")
        val user: User? = userRepo.findOne(id)
        user?.let {

        } ?: log.info("apply-detail: User(id = $id) not exist!")

        return ResponseBean(user)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute userQueryUnit: UserQueryUnit,
            model: Model): ResponseBean<Page<User>> {
        val applyPage: Page<User> = userService.list(pageIndex, pageSize, userQueryUnit)
        return ResponseBean(applyPage)
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): ResponseBean<Unit> {
        userRepo.delete(id)
        return ResponseBean()
    }

}
