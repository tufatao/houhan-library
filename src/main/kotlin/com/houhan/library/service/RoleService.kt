package com.houhan.library.service

import com.houhan.library.entity.Role

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface RoleService {
    fun list(): List<Role>
    fun one(name: String): Role?
    fun one(id: Int): Role?
    fun save(role: Role): Role?
    fun delete(id: Int)
}