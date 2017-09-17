package com.houhan.library.service

import com.houhan.library.entity.BorrowRecord
import javax.transaction.Transactional

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:02.
 */
interface BorrowRecordService {
    fun list(): List<BorrowRecord>
    //    fun one(name: String): BorrowRecord?
    fun one(id: Long): BorrowRecord?

    @Transactional
    fun save(borrowRecord: BorrowRecord): BorrowRecord?

    //    fun delete(borrowRecord: BorrowRecord)
    @Transactional
    fun delete(id: Long)
}