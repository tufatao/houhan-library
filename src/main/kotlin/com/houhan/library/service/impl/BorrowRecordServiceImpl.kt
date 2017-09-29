package com.houhan.library.service.impl

import com.houhan.library.element.BorrowQueryUnit
import com.houhan.library.entity.Book
import com.houhan.library.entity.BorrowRecord
import com.houhan.library.entity.Department
import com.houhan.library.entity.User
import com.houhan.library.helper.PageHelper
import com.houhan.library.repository.BorrowRecordRepo
import com.houhan.library.service.BorrowRecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import javax.persistence.criteria.Predicate

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

    override fun list(pageIndex: Int, pageSize: Int, borrowQueryUnit: BorrowQueryUnit): Page<BorrowRecord> {
        val page: Pageable = PageHelper.page(pageIndex, pageSize)
        val speci: Specification<BorrowRecord> = Specification<BorrowRecord> { root, query, cb ->
            val list = ArrayList<Predicate>()

            if (!StringUtils.isEmpty(borrowQueryUnit.bookName)) {
                list.add(cb.like(root.get<BorrowRecord>("book").get<Book>("name").`as`(String::class.java), "%" + borrowQueryUnit.bookName + "%"))
            }

            if (!StringUtils.isEmpty(borrowQueryUnit.userName)) {
                list.add(cb.equal(root.get<BorrowRecord>("user").get<User>("name").`as`(String::class.java), borrowQueryUnit.userName))
            }

            if (!StringUtils.isEmpty(borrowQueryUnit.deptName)) {
                list.add(cb.equal(root.get<BorrowRecord>("user").get<User>("department").get<Department>("name").`as`(String::class.java), borrowQueryUnit.deptName))
            }

            if (!StringUtils.isEmpty(borrowQueryUnit.status)) {
                list.add(cb.equal(root.get<BorrowRecord>("status").`as`(Int::class.java), borrowQueryUnit.status))
            }
            cb.and(*list.toTypedArray())
        }
        return borrowRecordRepo!!.findAll(speci, page)
    }

//    override fun one(name: String): BorrowRecord? {
//        val borrowRecord: BorrowRecord? = borrowRecordRepo.findByName(name)
//        return borrowRecord
//    }

    override fun delete(id: Long) {
        borrowRecordRepo.delete(id)
    }

}