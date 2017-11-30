package com.houhan.library.repository

import com.houhan.library.entity.Book
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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
 * @time 2017/11/28 22:45.
 */
@Transactional
@Rollback
@RunWith(SpringRunner::class)
@SpringBootTest
class BookRepoTest {
    @Autowired
    internal lateinit var bookRepo: BookRepo
    lateinit var name: String

    @Before
    fun prepare() {
        name = "Kotlin"
    }

    /**
     * 入库测试 自动回滚
     */
    @Test
    fun save() {
        val book = Book()
        book.name = name
        val temp: Book = bookRepo!!.save(book)
        Assert.isTrue(name.equals(temp.name))
    }

    /**
     * query one test
     */
    @Test
    fun one() {
        var temp: Book? = bookRepo.findByName(name)
        Assert.notNull(temp)
        Assert.isTrue(name.equals(temp!!.name))
    }

}
