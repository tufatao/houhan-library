package com.houhan.library.service.impl

import com.houhan.library.element.BookQueryUnit
import com.houhan.library.entity.Book
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.BookRepo
import com.houhan.library.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.criteria.Predicate

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

    override fun list(pageIndex: Int, pageSize: Int, bookQueryUnit: BookQueryUnit): Page<Book> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        val book: Book = Book()
        book.name = bookQueryUnit.name
        book.author = bookQueryUnit.author
//        book.category.name = bookQueryUnit.catName
        book.press = bookQueryUnit.press
        book.keyword = bookQueryUnit.keyword
        val speci: Specification<Book> = Specification<Book> { root, query, cb ->
            val list = ArrayList<Predicate>()
            if (!StringUtils.isEmpty(bookQueryUnit.name)) {
                list.add(cb.like(root.get<Book>("name").`as`(String::class.java), "%" + book.name + "%"))
            }

            if (!StringUtils.isEmpty(bookQueryUnit.author)) {
                list.add(cb.like(root.get<Book>("author").`as`(String::class.java), "%" + book.author + "%"))
            }

//            if (!StringUtils.isEmpty(bookQueryUnit.catName)) {
//                list.add(cb.equal(root.get<Any>("cat").`as`(String::class.java), bookQueryUnit.catName))
//            }
            //                if (model.getDepartment() != null && model.getDepartment().getCode() != null) {
            //                    list.add(cb.equal(root.get("department").as(DepartmentModel.class), model.getDepartment()));
            //                }

            cb.and(*list.toTypedArray())
        }
        return bookRepo!!.findAll(speci, page)
    }

    override fun one(name: String): Book? {
        val book: Book? = bookRepo.findByName(name)
        return book
    }

    override fun delete(id: Long) {
        bookRepo.delete(id)
    }

}