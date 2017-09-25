package com.houhan.library.service.impl

import com.houhan.library.entity.BorrowRecord
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.BorrowRecordRepo
import com.houhan.library.service.BorrowRecordService
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
class BorrowRecordServiceImpl : BorrowRecordService {
    @Autowired
    internal lateinit var borrowRecordRepo: BorrowRecordRepo

    override fun save(borrowRecord: BorrowRecord): BorrowRecord {
        return borrowRecordRepo.saveAndFlush(borrowRecord)
    }

    override fun one(id: Long): BorrowRecord? {
        val borrowRecord: BorrowRecord? = borrowRecordRepo.findOne(id)
        return borrowRecord
    }

    override fun list(pageIndex: Int, pageSize: Int, userId: Long): Page<BorrowRecord> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        return borrowRecordRepo.findAll(page)
    }

//    override fun one(name: String): BorrowRecord? {
//        val borrowRecord: BorrowRecord? = borrowRecordRepo.findByName(name)
//        return borrowRecord
//    }

    override fun delete(id: Long) {
        borrowRecordRepo.delete(id)
    }

}