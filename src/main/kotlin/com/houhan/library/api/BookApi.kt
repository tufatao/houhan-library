package com.houhan.library.api

import com.houhan.library.element.BookQueryUnit
import com.houhan.library.entity.Book
import com.houhan.library.helper.JsonUtil
import com.houhan.library.repository.BookRepo
import com.houhan.library.service.BookService
import com.houhan.library.support.resp.ResponseBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/19 16:36.
 */
@RequestMapping("/api/book")
@RestController
class BookApi {
    val log: Logger = LoggerFactory.getLogger(BookApi::class.java)

    @Autowired
    lateinit var bookService: BookService
    @Autowired
    lateinit var bookRepo: BookRepo

    @PostMapping()
    fun save(@RequestParam @NotNull book: String): ResponseBean<Long?> {
        log.info("book-save")
        var bookTemp: Book? = JsonUtil.json2Obj(book, Book::class.java)
        bookTemp?.let {
            val book = bookService.save(bookTemp)
            return ResponseBean(book!!.id)
        }
        return ResponseBean(-1)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long): ResponseBean<Book?> {
        log.info("book-detail")
        val book: Book? = bookRepo.findOne(id)
        book?.let {

        } ?: log.info("book-detail: Book(id = $id) not found")

        return ResponseBean(book)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute bookQueryUnit: BookQueryUnit): ResponseBean<Page<Book>> {
        log.info("book-list")
        val bookPage: Page<Book> = bookService.list(pageIndex, pageSize, bookQueryUnit)

        return ResponseBean(bookPage)
    }

    @PutMapping()
    fun update(@RequestParam @NotNull book: String): String {
        log.info("book-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): String {
        log.info("book-delete")
        bookRepo.delete(id)
        return "redirect:/apply"
    }

}
