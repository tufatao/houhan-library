package com.houhan.library.service

import com.houhan.library.element.BookQueryUnit
import com.houhan.library.entity.Book
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/28 23:13.
 */
@Transactional
@Rollback
@RunWith(SpringRunner::class)
@SpringBootTest
class BookServiceTest {
    @Autowired
    internal lateinit var bookService: BookService
    lateinit var name: String

    @Before
    fun prepare() {
        name = "Kotlin"
    }

    /**
     * 入库测试 自动回滚
     */
    fun save() {
        val book = Book()
        book.name = name
        val temp: Book? = bookService!!.save(book)
        Assert.notNull(temp)
        Assert.isTrue(name.equals(temp!!.name))
    }

    /**
     * 分页列表测试
     */
    fun pageList() {
        val bookPage: Page<Book> = bookService.list(1, 6, BookQueryUnit())
        Assert.notNull(bookPage)
        Assert.notEmpty(bookPage.content)
    }

    /**
     * query one test
     */
    @Test
    fun one() {
        var temp: Book? = bookService.one(name)
        Assert.notNull(temp)
        Assert.isTrue(name.equals(temp!!.name))
    }
}
