package com.houhan.library.service.impl

import com.houhan.library.element.UserQueryUnit
import com.houhan.library.entity.Department
import com.houhan.library.entity.Role
import com.houhan.library.entity.User
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.UserRepo
import com.houhan.library.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.criteria.Predicate

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:12.
 */
@Service
class UserServiceImpl : UserService {
    val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    @Autowired
    internal lateinit var userRepo: UserRepo

    override fun countByName(name: String): Int {
        return userRepo.countByName(name)
    }

    override fun save(user: User): User {
        return userRepo.saveAndFlush(user)
    }

    override fun one(id: Long): User? {
        val user: User? = userRepo.findOne(id)
        return user
    }

    override fun list(pageIndex: Int, pageSize: Int, userQueryUnit: UserQueryUnit): Page<User> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        val speci: Specification<User> = Specification<User> { root, query, cb ->
            val list = ArrayList<Predicate>()
            if (!StringUtils.isEmpty(userQueryUnit.name)) {
                list.add(cb.like(root.get<User>("name").`as`(String::class.java), "%" + userQueryUnit.name + "%"))
            }

            if (!StringUtils.isEmpty(userQueryUnit.keyword)) {
                list.add(cb.like(root.get<User>("keyword").`as`(String::class.java), "%" + userQueryUnit.keyword + "%"))
            }

            if (!StringUtils.isEmpty(userQueryUnit.mobile)) {
                list.add(cb.like(root.get<User>("mobile").`as`(String::class.java), userQueryUnit.mobile + "%"))
            }

            if (!StringUtils.isEmpty(userQueryUnit.deptName)) {
                list.add(cb.equal(root.get<User>("department").get<Department>("name").`as`(String::class.java), userQueryUnit.deptName))
            }

            if (!StringUtils.isEmpty(userQueryUnit.roleName)) {
                list.add(cb.equal(root.get<User>("role").get<Role>("name").`as`(String::class.java), userQueryUnit.roleName))
            }

            if (!StringUtils.isEmpty(userQueryUnit.sex)) {
                list.add(cb.equal(root.get<User>("sex").`as`(Int::class.java), userQueryUnit.sex))
            }

            cb.and(*list.toTypedArray())
        }
        return userRepo!!.findAll(speci, page)
    }

    override fun one(name: String): User? {
        val user: User? = userRepo.findByName(name)
        return user
    }

    override fun delete(id: Long) {
        userRepo.delete(id)
    }

    override fun loginCheck(name: String, pw: String): User? {
        val user = userRepo.findByName(name)
        val pwBack = user.pw
        if (pw == pwBack) {
            return user
        } else {
            return null
        }
    }

}
