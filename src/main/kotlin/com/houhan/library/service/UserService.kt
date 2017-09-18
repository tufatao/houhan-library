package com.houhan.library.service

import com.houhan.library.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface UserService {
    fun list(page: Pageable): Page<User>
    fun one(name: String): User?
    fun one(id: Long): User?
    fun pw(name: String): String
    fun save(user: User): User?
    fun delete(id: Long)
}
