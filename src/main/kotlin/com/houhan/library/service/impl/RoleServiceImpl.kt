package com.houhan.library.service.impl

import com.houhan.library.entity.Role
import com.houhan.library.helper.PageHelper
import com.houhan.library.resposity.RoleRepo
import com.houhan.library.service.RoleService
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
class RoleServiceImpl : RoleService {
    @Autowired
    internal lateinit var roleRepo: RoleRepo

    override fun save(role: Role): Role {
        return roleRepo.saveAndFlush(role)
    }

    override fun one(id: Int): Role? {
        val role: Role? = roleRepo.findOne(id)
        return role
    }

    override fun list(pageIndex: Int, pageSize: Int): Page<Role> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        return roleRepo.findAll(page)
    }

    override fun one(name: String): Role? {
        val role: Role? = roleRepo.findByName(name)
        return role
    }

    override fun delete(id: Int) {
        roleRepo.delete(id)
    }

}