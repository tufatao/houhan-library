package com.houhan.library.api

import com.houhan.library.entity.Book
import com.houhan.library.repository.BookRepo
import com.houhan.library.service.BookService
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
@RequestMapping("/api/book")
@RestController
class BookApi {
    val log: Logger = LoggerFactory.getLogger(BookApi::class.java)

    @Autowired
    lateinit var bookService: BookService
    @Autowired
    lateinit var bookRepo: BookRepo

    @PostMapping()
    fun save(@ModelAttribute @NotNull book: Book): ResponseBean<Book?> {
        println("book-save")
        val book = bookService.save(book)
        return ResponseBean(book)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): ResponseBean<Book?> {
        println("apply-detail")
        val book: Book? = bookRepo.findOne(id)
        book?.let {

        } ?: log.info("apply-detail: Book(id = $id) not found")

        return ResponseBean(book)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            model: Model): ResponseBean<Page<Book>> {
        println("apply-list")

        val applyPage: Page<Book> = bookService.list(pageIndex, pageSize)

        return ResponseBean(applyPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull book: Book, model: Model): String {
        println("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): String {
        println("apply-delete")
        bookRepo.delete(id)
        return "redirect:/apply"
    }

}
