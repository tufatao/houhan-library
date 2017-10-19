package com.houhan.library.api

import com.houhan.library.element.BookQueryUnit
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
    fun save(@ModelAttribute @NotNull book: Book): ResponseBean<Long?> {
        println("book-save")
        try {
            val book = bookService.save(book)
            return ResponseBean(book!!.id)
        } catch (e: RuntimeException) {
            log.info(e.message)
            return ResponseBean(e)
        }
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): ResponseBean<Book?> {
        println("book-detail")
        val book: Book? = bookRepo.findOne(id)
        book?.let {

        } ?: log.info("book-detail: Book(id = $id) not found")

        return ResponseBean(book)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute bookQueryUnit: BookQueryUnit,
            model: Model): ResponseBean<Page<Book>> {
        println("book-list")
        val bookPage: Page<Book> = bookService.list(pageIndex, pageSize, bookQueryUnit)

        return ResponseBean(bookPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull book: Book, model: Model): String {
        println("book-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): String {
        println("book-delete")
        bookRepo.delete(id)
        return "redirect:/apply"
    }

}
