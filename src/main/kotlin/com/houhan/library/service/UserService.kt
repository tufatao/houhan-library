package com.houhan.library.service

import com.houhan.library.entity.User

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface UserService {
    fun list(): List<User>?
    fun one(name: String): User
    fun one(id: Long): User?
    fun pw(name: String): String
    fun updata(user: User)
    fun delete(user: User)
}
