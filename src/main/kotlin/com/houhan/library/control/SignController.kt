package com.houhan.library.control

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:24
 * @version V0.1
 */
@RequestMapping("/sign")
@RestController
class SignController{

    @GetMapping("/signup")
    fun signup(@RequestParam status : Int): ModelAndView {
        println("sign-signup")
        return ModelAndView()
    }

    @GetMapping("/findpw")
    fun findPw(@RequestParam status : Int): ModelAndView {
        println("sign-findPw")
        return ModelAndView()
    }

    @GetMapping("/signin")
    fun signin(@RequestParam status : Int): ModelAndView {
        println("sign-signin")
        return ModelAndView()
    }
}