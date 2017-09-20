package com.houhan.library.service

import com.houhan.library.entity.Book
import org.springframework.data.domain.Page
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface BookService {
    fun list(pageIndex: Int, pageSize: Int): Page<Book>
    fun one(name: String): Book?
    fun one(id: Long): Book?
    @Transactional
    fun save(book: Book): Book?

    //    fun delete(book: Book)
    @Transactional
    fun delete(id: Long)
}