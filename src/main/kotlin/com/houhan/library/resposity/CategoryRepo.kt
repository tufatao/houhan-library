package com.houhan.library.resposity

import com.houhan.library.entity.Category
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
interface CategoryRepo : JpaRepository<Category, Int> {
    fun findByName(@Param("name") name: String): Category
}