package com.houhan.library.service.impl

import com.houhan.library.entity.ApplyRecord
import com.houhan.library.resposity.ApplyRecordRepo
import com.houhan.library.service.ApplyRecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/15 17:12.
 */
@Service
class ApplyRecordServiceImpl : ApplyRecordService {
    @Autowired
    internal lateinit var applyRecordRepo: ApplyRecordRepo

    override fun save(applyRecord: ApplyRecord): ApplyRecord {
        return applyRecordRepo.saveAndFlush(applyRecord)
    }

    override fun one(id: Long): ApplyRecord? {
        val applyRecord: ApplyRecord? = applyRecordRepo.findOne(id)
        return applyRecord
    }

    override fun list(): List<ApplyRecord> {
        return applyRecordRepo.findAll() ?: ArrayList()
    }

//    override fun one(name: String): ApplyRecord? {
//        val applyRecord: ApplyRecord? = applyRecordRepo.findByName(name)
//        return applyRecord
//    }

    override fun delete(id: Long) {
        applyRecordRepo.delete(id)
    }

}