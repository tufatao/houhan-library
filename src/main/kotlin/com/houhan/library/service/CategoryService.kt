package com.houhan.library.service

import com.houhan.library.entity.Category
import org.springframework.data.domain.Page
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface CategoryService {
    fun list(pageIndex: Int, pageSize: Int): Page<Category>
    fun one(name: String): Category?
    fun one(id: Int): Category?
    @Transactional
    fun save(category: Category): Category?

    //    fun delete(category: Category)
    @Transactional
    fun delete(id: Int)
}