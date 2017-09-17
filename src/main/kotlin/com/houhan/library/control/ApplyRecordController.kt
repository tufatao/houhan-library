package com.houhan.library.control

import com.houhan.library.entity.ApplyRecord
import com.houhan.library.service.ApplyRecordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): String {
        var result = ""
        println("applyRecord-detail")
        val applyRecord: ApplyRecord? = applyRecordService.one(id)
        applyRecord?.let {
            model.addAttribute("applyRecord", applyRecord)
            result = "/applyRecord/applyRecordlist"
        } ?: log.info("applyRecord-detail: ApplyRecord(id = $id) not found")
        return result
    }

    @GetMapping()
    fun list(model: Model): String {
        println("applyRecord-list")
        val applyRecordList: List<ApplyRecord> = applyRecordService.list()
        model.addAttribute("applyRecordList", applyRecordList)
        return "/applyRecord/applyRecordlist"
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull applyRecord: ApplyRecord, model: Model): String {
        println("applyRecord-save")
        val applyRecord: ApplyRecord? = applyRecordService.save(applyRecord)
        applyRecord?.let {
            model.addAttribute("applyRecord", applyRecord)
            return "redirect:/applyRecord"
        } ?: log.info("applyRecord-save: ApplyRecord not found")
        return ""
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull applyRecord: ApplyRecord, model: Model): String {
        println("applyRecord-update")
        return "redirect:/applyRecord"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Long): String {
        println("applyRecord-delete")
        applyRecordService.delete(id)
        return "redirect:/applyRecord"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("applyRecord-toAdd")
        return "/applyRecord/applyRecordadd"
    }
}