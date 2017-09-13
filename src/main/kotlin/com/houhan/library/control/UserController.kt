package com.houhan.library.control

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
class UserController {

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int): ModelAndView {
        println("user-detail")
        return ModelAndView()
    }

    @GetMapping()
    fun list(@RequestParam status: Int): ModelAndView {
        println("user-list")
        return ModelAndView()
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