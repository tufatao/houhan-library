package com.houhan.library.interceptor

import com.houhan.library.entity.User
import com.houhan.library.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/11/13 16:09.
 */
@Service
class MyUserDetails : UserDetailsService {
    @Autowired
    lateinit var userRepo: UserRepo

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails? {
        val temp: User = userRepo.findByNickName(s)
        val roleName = temp.role.name
        val author: GrantedAuthority = SimpleGrantedAuthority(roleName)
        var roles = listOf(author)
        val user: org.springframework.security.core.userdetails.User = org.springframework.security.core.userdetails.User(temp.nickName, temp.pw, roles)
        return user
    }
}
