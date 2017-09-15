package com.houhan.library.resposity

import com.houhan.library.entity.UserBorrow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:04
 * @version V0.1
 */
@Repository
interface UserBorrowRepo : JpaRepository<UserBorrow, Long> {
//    fun findByIdCardNum(@Param("idCardNum") idCardNum: String): User
}
