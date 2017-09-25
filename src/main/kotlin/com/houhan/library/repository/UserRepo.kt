package com.houhan.library.repository

import com.houhan.library.entity.User
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
interface UserRepo : JpaRepository<User, Long> {
    fun findByName(@Param("name") name: String): User
    fun countByName(@Param("name") name: String): Int
}
