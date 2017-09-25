package com.houhan.library.service.impl

import com.houhan.library.entity.Category
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.CategoryRepo
import com.houhan.library.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:12.
 */
@Service
class CategoryServiceImpl : CategoryService {
    @Autowired
    internal lateinit var categoryRepo: CategoryRepo

    override fun save(category: Category): Category {
        return categoryRepo.saveAndFlush(category)
    }

    override fun one(id: Int): Category? {
        val category: Category? = categoryRepo.findOne(id)
        return category
    }

    override fun list(pageIndex: Int, pageSize: Int): Page<Category> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        return categoryRepo.findAll(page)
    }

    override fun one(name: String): Category? {
        val category: Category? = categoryRepo.findByName(name)
        return category
    }

    override fun delete(id: Int) {
        categoryRepo.delete(id)
    }

}