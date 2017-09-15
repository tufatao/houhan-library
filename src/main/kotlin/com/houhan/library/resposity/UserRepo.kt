package com.houhan.library.resposity

import com.houhan.library.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:04
 * @version V0.1
 */
@Repository
interface UserRepo : JpaRepository<User, Long> {
//    fun findByIdCardNum(@Param("idCardNum") idCardNum: String): User
}
