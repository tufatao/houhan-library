package com.houhan.library.service

import com.houhan.library.entity.Department
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface DepartmentService {
    fun list(): List<Department>
    fun one(name: String): Department?
    fun one(id: Int): Department?
    @Transactional
    fun save(department: Department): Department?

    //    fun delete(department: Department)
    @Transactional
    fun delete(id: Int)
}