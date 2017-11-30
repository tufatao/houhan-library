package com.houhan.library.api

import com.houhan.library.element.ApplyQueryUnit
import com.houhan.library.entity.ApplyRecord
import com.houhan.library.repository.ApplyRecordRepo
import com.houhan.library.service.ApplyRecordService
import com.houhan.library.support.resp.ResponseBean
import com.houhan.library.support.resp.ResponseBeanUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/19 16:36.
 */
@RequestMapping("/api/apply")
@RestController
class ApplyRecordApi {
    val log: Logger = LoggerFactory.getLogger(ApplyRecordApi::class.java)

    @Autowired
    lateinit var applyRecordService: ApplyRecordService
    @Autowired
    lateinit var applyRecordRepo: ApplyRecordRepo

    /**
     * 处理申请
     */
    @PostMapping("/handle")
    fun handleApply(@RequestParam @NotNull applyId: Long,
                    @RequestParam @NotNull result: Int,
                    @RequestParam @NotNull reviewRemark: String,
                    @RequestParam userId: Long,
                    @RequestParam bookId: Long): ResponseBean<ApplyRecord> {
        val applyRecord = applyRecordService.handleApply(applyId, result, reviewRemark, userId, bookId)

        return ResponseBeanUtil.success(applyRecord)
    }

    /**
     * 发起申请
     */
    @PostMapping("/launch")
    fun launchApply(@RequestParam @NotNull type: Int,
                    @RequestParam @NotNull applyRemark: String,
                    @RequestParam @NotNull userId: Long,
                    @RequestParam bookId: Long,
                    @RequestParam borrowId: Long): ResponseBean<Long> {
        val applyRecord = applyRecordService.lanchApply(type, applyRemark, userId, bookId, borrowId)
        return ResponseBeanUtil.success(applyRecord.id)
//        return ResponseBean(applyRecord.id)
    }

    /**
     * 通过id获取ApplyRecord
     */
    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long): ResponseBean<ApplyRecord?> {
        log.info("apply-detail")
        val applyRecord: ApplyRecord? = applyRecordRepo.findOne(id)
        applyRecord?.let {

        } ?: log.info("apply-detail: ApplyRecord(id = $id) not found")

        return ResponseBeanUtil.success(applyRecord)
    }

    /**
     * 分页查询
     */
    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute applyQueryUnit: ApplyQueryUnit): ResponseBean<Page<ApplyRecord>> {
        log.info("apply-list")

        val applyPage: Page<ApplyRecord> = applyRecordService.list(pageIndex, pageSize, applyQueryUnit)

        return ResponseBean(applyPage)
    }

    @PutMapping()
    fun update(@RequestParam @NotNull applyRecord: ApplyRecord): String {
        log.info("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam id: Long): String {
        log.info("apply-delete")
        applyRecordRepo.delete(id)
        return "redirect:/apply"
    }

}
