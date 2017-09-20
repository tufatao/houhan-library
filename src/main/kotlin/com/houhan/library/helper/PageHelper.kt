package com.houhan.library.helper

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/20 13:52.
 */
object PageHelper {

    fun page(pageIndex: Int = 1, pageSize: Int = 10): Pageable {
        var pageSizeTemp = pageSize
        if (pageSizeTemp > 30) {
            pageSizeTemp = 10
        }
        return PageRequest(pageIndex - 1, pageSizeTemp)
    }
}

