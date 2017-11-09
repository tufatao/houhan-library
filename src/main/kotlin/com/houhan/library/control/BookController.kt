package com.houhan.library.control

import com.houhan.library.element.BookQueryUnit
import com.houhan.library.entity.Book
import com.houhan.library.entity.Category
import com.houhan.library.repository.CategoryRepo
import com.houhan.library.service.BookService
import com.houhan.library.service.CategoryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/book")
@Controller
class BookController {
    val log: Logger = LoggerFactory.getLogger(BookController::class.java)
    @Autowired
    lateinit var categoryService: CategoryService
    @Autowired
    lateinit var categoryRepo: CategoryRepo
    @Autowired
    lateinit var bookService: BookService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): String {
        var result = ""
        log.info("book-detail")
        val book: Book? = bookService.one(id)
        book?.let {
            model.addAttribute("book", book)
            result = "/book/booklist"
        } ?: log.info("book-detail: Book(id = $id) not found")
        return result
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute bookQueryUnit: BookQueryUnit,
            model: Model): String {
        log.info("apply-list")
        val bookPage: Page<Book> = bookService.list(pageIndex, pageSize, bookQueryUnit)
        model.addAttribute("bookPage", bookPage)
        return "/book/booklist"
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull book: Book,
             @RequestParam catId: Int,
             model: Model): String {
        log.info("book-save")
        book.category = categoryService.one(catId)!!
        val book: Book? = bookService.save(book)
        book?.let {
            model.addAttribute("book", book)
            return "redirect:/book"
        } ?: log.info("book-save: Book not found")
        return ""
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull book: Book, model: Model): String {
        log.info("book-update")
        return "redirect:/book"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Long): String {
        log.info("book-delete")
        bookService.delete(id)
        return "redirect:/book"
    }

    @GetMapping("/toadd")
    fun toAdd(model: Model): String {
        log.info("book-toAdd")
        val categoryList: List<Category> = categoryRepo.findAll()
        model.addAttribute("categoryList", categoryList)
        return "/book/bookadd"
    }
}
