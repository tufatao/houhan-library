package com.houhan.library.repository

import com.houhan.library.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:04
 * @version V0.1
 */
@Repository
interface DepartmentRepo : JpaRepository<Department, Int> {
    //    fun findByIdCardNum(@Param("idCardNum") idCardNum: String): User
    fun findByName(@Param("name") name: String): Department?
}
