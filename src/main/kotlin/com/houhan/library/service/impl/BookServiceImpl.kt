package com.houhan.library.service.impl

import com.houhan.library.entity.Book
import com.houhan.library.resposity.BookRepo
import com.houhan.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:12.
 */
@Service
class BookServiceImpl : BookService {
    @Autowired
    internal lateinit var bookRepo: BookRepo

    override fun save(book: Book): Book {
        return bookRepo.saveAndFlush(book)
    }

    override fun one(id: Long): Book? {
        val book: Book? = bookRepo.findOne(id)
        return book
    }

    override fun list(): List<Book> {
        return bookRepo.findAll() ?: ArrayList()
    }

    override fun one(name: String): Book? {
        val book: Book? = bookRepo.findByName(name)
        return book
    }

    override fun delete(id: Long) {
        bookRepo.delete(id)
    }

}