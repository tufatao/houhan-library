package com.houhan.library.control

import com.houhan.library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/user")
@RestController
class UserController {

    @Autowired
    internal var userService: UserService? = null

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long): ModelAndView {
        userService?.one(id)
        println("user-detail id = ${id}")
        return ModelAndView("/user/userdetail")
    }

    @GetMapping()
    fun list(): ModelAndView {
        println("user-list")
        userService?.list()
        return ModelAndView("/user/userlist")
    }

    @PostMapping()
    fun save(@PathVariable id: Int): ModelAndView {
        println("user-save")
        return ModelAndView()
    }

    @PutMapping()
    fun update(@PathVariable id: Int): ModelAndView {
        println("user-update")
        return ModelAndView()
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Int): ModelAndView {
        println("user-delete")
        return ModelAndView()
    }
}