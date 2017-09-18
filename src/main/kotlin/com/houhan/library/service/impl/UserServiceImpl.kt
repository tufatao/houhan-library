package com.houhan.library.service.impl

import com.houhan.library.entity.User
import com.houhan.library.resposity.UserRepo
import com.houhan.library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:12.
 */
@Service
class UserServiceImpl : UserService {
    @Autowired
    internal lateinit var userRepo: UserRepo

    override fun save(user: User): User {
        return userRepo.saveAndFlush(user)
    }

    override fun one(id: Long): User? {
        val user: User? = userRepo.findOne(id)
        return user
    }

    override fun list(page: Pageable): Page<User> {
//        return userRepo.findAll() ?: ArrayList()
        val userPage: Page<User> = userRepo.findAll(page)
        return userPage
    }

    override fun one(name: String): User? {
        val user: User? = userRepo.findByName(name)
        return user
    }

    override fun delete(id: Long) {
        userRepo.delete(id)
    }

    override fun pw(name: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
