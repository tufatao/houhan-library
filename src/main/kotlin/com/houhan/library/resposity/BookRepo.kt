package com.houhan.library.resposity

import com.houhan.library.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:05
 * @version V0.1
 */
@Repository
interface BookRepo : JpaRepository<Book, Long> {
//    fun findByIdCardNum(@Param("idCardNum") idCardNum: String): User
}
