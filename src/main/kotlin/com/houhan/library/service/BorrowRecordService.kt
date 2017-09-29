package com.houhan.library.service

import com.houhan.library.element.BorrowQueryUnit
import com.houhan.library.entity.BorrowRecord
import org.springframework.data.domain.Page
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface BorrowRecordService {
    fun list(pageIndex: Int, pageSize: Int, borrowQueryUnit: BorrowQueryUnit): Page<BorrowRecord>
    //    fun one(name: String): BorrowRecord?
    fun one(id: Long): BorrowRecord?

    @Transactional
    fun save(borrowRecord: BorrowRecord): BorrowRecord?

    //    fun delete(borrowRecord: BorrowRecord)
    @Transactional
    fun delete(id: Long)
}