package com.houhan.library

import com.houhan.library.interceptor.MyUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @author tufatao
 * *
 * @version V 0.1
 * *
 * @describe {}
 * *
 * @time 2017/9/25 16:08.
 */
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var myUserDetails: MyUserDetails

    @Throws(RuntimeException::class)
    override fun configure(http: HttpSecurity) {
        http
//                .addFilter() //增加filter
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/apply/handle/**").hasAnyRole("宗师", "盟主")
//                .antMatchers("/**").hasRole("USER")
//                .regexMatchers("/.+").hasAnyRole("新秀", "少侠", "大侠", "掌门")
                .antMatchers("/").hasAnyRole("新秀", "少侠", "大侠", "掌门")
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error")
    }

    @Autowired
    @Throws(RuntimeException::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(myUserDetails)
    }

}
