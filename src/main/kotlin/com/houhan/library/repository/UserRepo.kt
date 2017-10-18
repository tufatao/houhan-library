package com.houhan.library.repository

import com.houhan.library.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:04
 * @version V0.1
 */
@Repository
interface UserRepo : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun countByName(@Param("name") name: String): Int
    fun findByNickName(@Param("nickName") nickName: String): User
    fun countByNickName(@Param("nickName") nickName: String): Int
    fun countByEmail(@Param("email") email: String): Int
    fun countByMobile(@Param("mobile") mobile: String): Int
}
