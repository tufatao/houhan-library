package com.houhan.library.service

import com.houhan.library.entity.ApplyRecord
import org.springframework.data.domain.Page
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface ApplyRecordService {
    fun list(pageIndex: Int, pageSize: Int, userId: Long): Page<ApplyRecord>
    //    fun one(name: String): ApplyRecord?
    fun one(id: Long): ApplyRecord?

    @Transactional
    fun save(applyRecord: ApplyRecord): ApplyRecord?

    //    fun delete(apply: ApplyRecord)
    @Transactional
    fun delete(id: Long)

    fun handleApply(applyId: Long, result: Int, reviewRemark: String, userId: Long, bookId: Long): ApplyRecord
    fun lanchApply(type: Int, applyRemark: String, userId: Long, bookId: Long, borrowId: Long): ApplyRecord
}