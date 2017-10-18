package com.houhan.library.api

import com.houhan.library.element.BorrowQueryUnit
import com.houhan.library.entity.BorrowRecord
import com.houhan.library.repository.BorrowRecordRepo
import com.houhan.library.service.BorrowRecordService
import com.houhan.library.web.ResponseBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}
 * @author tufatao
 * @version V 0.1
 * @time 2017/9/19 16:36.
 */
@RequestMapping("/api/borrow")
@RestController
class BorrowRecordApi {
    val log: Logger = LoggerFactory.getLogger(BorrowRecordApi::class.java)

    @Autowired
    lateinit var borrowRecordService: BorrowRecordService
    @Autowired
    lateinit var borrowRecordRepo: BorrowRecordRepo

    @PostMapping()
    fun save(@ModelAttribute @NotNull borrowRecord: BorrowRecord): ResponseBean<BorrowRecord?> {
        println("borrowRecord-save")
        val borrowRecord = borrowRecordService.save(borrowRecord)
        return ResponseBean(borrowRecord)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Long, model: Model): ResponseBean<BorrowRecord?> {
        println("borrow-detail")
        val borrowRecord: BorrowRecord? = borrowRecordRepo.findOne(id)
        borrowRecord?.let {

        } ?: log.info("borrow-detail: BorrowRecord(id = $id) not found")

        return ResponseBean(borrowRecord)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            @ModelAttribute borrowQueryUnit: BorrowQueryUnit,
            model: Model): ResponseBean<Page<BorrowRecord>> {
        println("borrow-list")

        val applyPage: Page<BorrowRecord> = borrowRecordService.list(pageIndex, pageSize, borrowQueryUnit)

        return ResponseBean(applyPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull borrowRecord: BorrowRecord, model: Model): String {
        println("borrow-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Long): String {
        println("borrow-delete")
        borrowRecordRepo.delete(id)
        return "redirect:/apply"
    }

}
