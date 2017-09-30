package com.houhan.library.control

import com.houhan.library.element.BorrowQueryUnit
import com.houhan.library.entity.BorrowRecord
import com.houhan.library.service.BorrowRecordService
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
 * @date 2017/9/3 16:10
 * @version V0.1
 */
@RequestMapping("/borrow")
@Controller
class BorrowRecordController {
    val log: Logger = LoggerFactory.getLogger(BorrowRecordController::class.java)
    @Autowired
    lateinit var borrowRecordService: BorrowRecordService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): String {
        var result = ""
        println("borrowRecord-detail")
        val borrowRecord: BorrowRecord? = borrowRecordService.one(id)
        borrowRecord?.let {
            model.addAttribute("borrowRecord", borrowRecord)
            result = "/borrowRecord/borrowRecordlist"
        } ?: log.info("borrowRecord-detail: BorrowRecord(id = $id) not found")
        return result
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @RequestParam borrowQueryUnit: BorrowQueryUnit,
            model: Model): String {
        println("apply-list")

        val borrowPage: Page<BorrowRecord> = borrowRecordService.list(pageIndex, pageSize, borrowQueryUnit)
        model.addAttribute("borrowPage", borrowPage)
        return "/borrowRecord/borrowRecordlist"
    }

    @PostMapping()
    fun save(@RequestParam @NotNull borrowRecord: BorrowRecord, model: Model): String {
        println("borrowRecord-save")
        val borrowRecord: BorrowRecord? = borrowRecordService.save(borrowRecord)
        borrowRecord?.let {
            model.addAttribute("borrowRecord", borrowRecord)
            return "redirect:/borrowRecord"
        } ?: log.info("borrowRecord-save: BorrowRecord not found")
        return ""
    }

    @PutMapping()
    fun update(@RequestParam @NotNull borrowRecord: BorrowRecord, model: Model): String {
        println("borrowRecord-update")
        return "redirect:/borrowRecord"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Long): String {
        println("borrowRecord-delete")
        borrowRecordService.delete(id)
        return "redirect:/borrowRecord"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("borrowRecord-toAdd")
        return "/borrowRecord/borrowRecordadd"
    }
}
