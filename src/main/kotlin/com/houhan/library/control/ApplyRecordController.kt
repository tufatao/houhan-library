package com.houhan.library.control

import com.houhan.library.entity.ApplyRecord
import com.houhan.library.service.ApplyRecordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:11
 * @version V0.1
 */
@RequestMapping("/apply")
@Controller
class ApplyRecordController {
    val log: Logger = LoggerFactory.getLogger(ApplyRecordController::class.java)
    @Autowired
    lateinit var applyRecordService: ApplyRecordService

    /**
     * 处理申请
     */
    @GetMapping("/{applyId}/{result}/{applyRemark}/{userId}/{bookId}")
    fun handleApply(@PathVariable @NotNull applyId: Long,
                    @PathVariable @NotNull result: Int,
                    @PathVariable @NotNull reviewRemark: String,
                    @PathVariable userId: Long,
                    @PathVariable bookId: Long,
                    model: Model): String {
        val applyRecord = applyRecordService.handleApply(applyId, result, reviewRemark, userId, bookId)
        model.addAttribute("applyRecord", applyRecord)

        return "redirect:/apply"
    }

    /**
     * 发起申请
     */
    @GetMapping("/{userId}/{bookId}")
    fun launchApply(@PathVariable @NotNull type: Int,
                    @PathVariable @NotNull applyRemark: String,
                    @PathVariable @NotNull userId: Long,
                    @PathVariable bookId: Long,
                    @PathVariable borrowId: Long,
                    model: Model): String {
        val applyRecord = applyRecordService.lanchApply(type, applyRemark, userId, bookId, borrowId)
        model.addAttribute("applyRecord", applyRecord)

        return "redirect:/apply"
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): String {
        var result = ""
        println("apply-detail")
        val applyRecord: ApplyRecord? = applyRecordService.one(id)
        applyRecord?.let {
            model.addAttribute("apply", applyRecord)
            result = "redirect:/apply"
        } ?: log.info("apply-detail: ApplyRecord(id = $id) not found")
        return result
    }

    @GetMapping()
    fun list(
            @PathVariable pageIndex: Int = 1,
            @PathVariable pageSize: Int = 10,
            model: Model): String {
        println("user-list")
        var pageSizeTemp = pageSize
        if (pageSizeTemp > 30) {
            pageSizeTemp = 10
        }
        val page: Pageable = PageRequest(pageIndex - 1, pageSizeTemp)
        val applyPage: Page<ApplyRecord> = applyRecordService.list(page)
        model.addAttribute("applyPage", applyPage)
        return "/apply/applyList"
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull applyRecord: ApplyRecord, model: Model): String {
        println("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Long): String {
        println("apply-delete")
        applyRecordService.delete(id)
        return "redirect:/apply"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("apply-toAdd")
        return "/apply/applyadd"
    }
}