package com.houhan.library.control

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:10
 * @version V0.1
 */
@RequestMapping("/borrowRecord")
@RestController
class BorrowRecordController{

    @PostMapping()
    fun save(@RequestParam status : Int): ModelAndView {
        println("bookRecord-add")
        return ModelAndView()
    }
    @PutMapping()
    fun update(@RequestParam status : Int): ModelAndView {
        println("bookRecord-update")
        return ModelAndView()
    }
    @GetMapping()
    fun detail(@RequestParam status : Int): ModelAndView {
        println("bookRecord-detail")
        return ModelAndView()
    }
    @GetMapping()
    fun list(@RequestParam status : Int): ModelAndView {
        println("bookRecord-list")
        return ModelAndView()
    }

}