package com.houhan.library.control

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/book")
@RestController
class BookController{
//    val log: Logger = LoggerFactory.getLogger

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id : Int): ModelAndView {
        println("book-detail")
        return ModelAndView()
    }

    @GetMapping()
    fun list(@RequestParam status : Int): ModelAndView {
        println("book-list")
        return ModelAndView()
    }
    @PostMapping()
    fun save(@PathVariable id : Int): ModelAndView {
        println("book-save")
        return ModelAndView()
    }
    @PutMapping()
    fun update(@PathVariable id : Int): ModelAndView {
        println("book-update")
        return ModelAndView()
    }
    @DeleteMapping()
    fun delete(@PathVariable id : Int): ModelAndView {
        println("book-delete")
        return ModelAndView()
    }
}