package com.houhan.library.control

import com.houhan.library.entity.ApplyRecord
import com.houhan.library.service.ApplyRecordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
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
    @PostMapping("/handle")
    fun handleApply(@RequestParam @NotNull applyId: Long,
                    @RequestParam @NotNull result: Int,
                    @RequestParam @NotNull reviewRemark: String,
                    @RequestParam userId: Long,
                    @RequestParam bookId: Long,
                    model: Model): String {
        val applyRecord = applyRecordService.handleApply(applyId, result, reviewRemark, userId, bookId)
        model.addAttribute("applyRecord", applyRecord)

        return "redirect:/apply"
    }

    /**
     * 发起申请
     */
    @GetMapping("/launch")
    fun launchApply(@RequestParam @NotNull type: Int,
                    @RequestParam @NotNull applyRemark: String,
                    @RequestParam @NotNull userId: Long,
                    @RequestParam bookId: Long,
                    @RequestParam borrowId: Long,
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

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @RequestParam userId: Long,
            model: Model): String {
        println("user-list")

        val applyPage: Page<ApplyRecord> = applyRecordService.list(pageIndex, pageSize, userId)
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