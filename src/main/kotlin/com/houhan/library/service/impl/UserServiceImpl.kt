package com.houhan.library.service.impl

import com.houhan.library.entity.User
import com.houhan.library.resposity.UserRepo
import com.houhan.library.service.UserService
import org.springframework.beans.factory.annotation.Autowired
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
    internal var userRepo: UserRepo? = null

    override fun one(id: Long): User? {
        return userRepo?.findOne(id)
    }

    override fun list(): List<User>? {
        return userRepo?.findAll()
    }

    override fun one(name: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pw(name: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updata(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
