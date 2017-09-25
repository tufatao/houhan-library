package com.houhan.library.service.impl

import com.houhan.library.element.AffairStatus
import com.houhan.library.element.ApplyType
import com.houhan.library.entity.ApplyRecord
import com.houhan.library.entity.BorrowRecord
import com.houhan.library.helper.DateUtil
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.ApplyRecordRepo
import com.houhan.library.repository.BookRepo
import com.houhan.library.repository.BorrowRecordRepo
import com.houhan.library.repository.UserRepo
import com.houhan.library.service.ApplyRecordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
class ApplyRecordServiceImpl : ApplyRecordService {
    val log: Logger = LoggerFactory.getLogger(ApplyRecordServiceImpl::class.java)

    @Autowired
    internal lateinit var applyRecordRepo: ApplyRecordRepo
    @Autowired
    internal lateinit var borrowRecordRepo: BorrowRecordRepo
    @Autowired
    internal lateinit var userRepo: UserRepo
    @Autowired
    internal lateinit var bookRepo: BookRepo

    /**
     * 发起申请
     */
    override fun lanchApply(type: Int, applyRemark: String, userId: Long, bookId: Long, borrowId: Long): ApplyRecord {
        val applyRecord: ApplyRecord = ApplyRecord()
        applyRecord.type = type
        applyRecord.applyRemark = applyRemark
        when (type) {
            ApplyType.BUY.code -> {
                applyRecord.user = userRepo.findOne(userId)!!
            }
            ApplyType.BORROW.code -> {
                applyRecord.user = userRepo.findOne(userId)!!
                applyRecord.book = bookRepo.findOne(bookId)!!
            }
            ApplyType.DELAY.code, ApplyType.RETURN.code -> {
                val borrowRecord: BorrowRecord = borrowRecordRepo.findOne(borrowId)
                applyRecord.borrowRecord = borrowRecord
                applyRecord.user = borrowRecord.user
                applyRecord.book = borrowRecord.book
            }
        }
        save(applyRecord)

        return applyRecord
    }

    /**
     * 处理申请
     */
    override fun handleApply(applyId: Long, result: Int, reviewRemark: String, userId: Long, bookId: Long): ApplyRecord {
        val applyRecord: ApplyRecord = one(applyId)!!
        applyRecord.applyRemark = reviewRemark
        applyRecord.status = result

        if (result == AffairStatus.PASSED.code) {
            val type = applyRecord.type
            when (type) {
                ApplyType.BUY.code -> {
                    log.info("人家申请买书啦, 赶紧处理哟!")
                }
                ApplyType.BORROW.code -> {
                    val borrowRecord: BorrowRecord = BorrowRecord()
                    borrowRecord.user = userRepo.findOne(userId)!!
                    borrowRecord.book = bookRepo.findOne(bookId)!!
                    borrowRecordRepo.save(borrowRecord)
                }
                ApplyType.DELAY.code -> {
                    val borrowRecord: BorrowRecord = applyRecord.borrowRecord!!
                    borrowRecord.shouldReturnTime = DateUtil.addMonth(borrowRecord.shouldReturnTime, 1)
                    borrowRecordRepo.save(borrowRecord)
                }
                ApplyType.RETURN.code -> {
                    val borrowRecord: BorrowRecord = applyRecord.borrowRecord!!
                    borrowRecord.status = AffairStatus.DONE.code
                    borrowRecordRepo.save(borrowRecord)
                }
            }
        }
        return applyRecord
    }

    override fun save(applyRecord: ApplyRecord): ApplyRecord {
        return applyRecordRepo.saveAndFlush(applyRecord)
    }

    override fun one(id: Long): ApplyRecord? {
        val applyRecord: ApplyRecord? = applyRecordRepo.findOne(id)
        return applyRecord
    }

    override fun list(pageIndex: Int, pageSize: Int, userId: Long): Page<ApplyRecord> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        return applyRecordRepo.findAll(page)
    }

//    override fun one(name: String): ApplyRecord? {
//        val apply: ApplyRecord? = applyRecordRepo.findByName(name)
//        return apply
//    }

    override fun delete(id: Long) {
        applyRecordRepo.delete(id)
    }

}