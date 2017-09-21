package com.houhan.library.interceptor

import org.springframework.util.StringUtils
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/21 16:36.
 */
class AuthorizationInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val cookieArray = request.cookies
        if (cookieArray.size > 0) {
            val username = request.getAttribute("USER_NAME")
            if (!StringUtils.isEmpty(username)) {
                return true
            }
        }
        return false
    }
}
