package com.houhan.library.service.impl

import com.houhan.library.entity.Department
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.DepartmentRepo
import com.houhan.library.service.DepartmentService
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
class DepartmentServiceImpl : DepartmentService {
    @Autowired
    internal lateinit var departmentRepo: DepartmentRepo

    override fun save(department: Department): Department {
        return departmentRepo.saveAndFlush(department)
    }

    override fun one(id: Int): Department? {
        val department: Department? = departmentRepo.findOne(id)
        return department
    }

    override fun list(pageIndex: Int, pageSize: Int): Page<Department> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        return departmentRepo.findAll(page)
    }

    override fun one(name: String): Department? {
        val department: Department? = departmentRepo.findByName(name)
        return department
    }

    override fun delete(id: Int) {
        departmentRepo.delete(id)
    }

}