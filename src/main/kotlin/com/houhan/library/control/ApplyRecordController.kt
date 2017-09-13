package com.houhan.library.control

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:11
 * @version V0.1
 */
@RequestMapping("/apply")
@RestController
class ApplyRecordController{

    @PostMapping()
    fun save(@RequestParam status : Integer): ModelAndView {
        println("applyRecord-save")
        return ModelAndView()
    }
    @PutMapping()
    fun update(@RequestParam status : Integer): ModelAndView {
        println("applyRecord-update")
        return ModelAndView()
    }
    @GetMapping()
    fun detail(@RequestParam status : Integer): ModelAndView {
        println("applyRecord-detail")
        return ModelAndView()
    }
    @GetMapping()
    fun list(@RequestParam status : Integer): ModelAndView {
        println("applyRecord-list")
        return ModelAndView()
    }
}