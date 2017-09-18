package com.houhan.library.control

import com.houhan.library.element.AffairStatus
import com.houhan.library.element.ApplyType
import com.houhan.library.entity.ApplyRecord
import com.houhan.library.entity.BorrowRecord
import com.houhan.library.helper.DateUtil
import com.houhan.library.service.ApplyRecordService
import com.houhan.library.service.BookService
import com.houhan.library.service.BorrowRecordService
import com.houhan.library.service.UserService
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
    @Autowired
    lateinit var borrowRecordService: BorrowRecordService
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var bookService: BookService

    /**
     * 处理申请
     */
    @GetMapping("/{applyId}/{result}/{applyRemark}/{userId}/{bookId}")
    fun applyHandle(@PathVariable @NotNull applyId: Long,
                    @PathVariable @NotNull result: Int,
                    @PathVariable @NotNull applyRemark: String,
                    @PathVariable @NotNull userId: Long,
                    @PathVariable @NotNull bookId: Long,
                    model: Model): String {
        val applyRecord: ApplyRecord = applyRecordService.one(applyId)!!
        applyRecord.applyRemark = applyRemark
        applyRecord.status = result

        if (result == AffairStatus.PASSED.code) {
            val type = applyRecord.type
            when (type) {
                ApplyType.BUY.code -> {
                    log.info("人家申请买书啦, 赶紧处理哟!")
                }
                ApplyType.BORROW.code -> {
                    val borrowRecord: BorrowRecord = BorrowRecord()
                    borrowRecord.user = userService.one(userId)!!
                    borrowRecord.book = bookService.one(bookId)!!
                    borrowRecordService.save(borrowRecord)
                }
                ApplyType.DELAY.code -> {
                    val borrowRecord: BorrowRecord = applyRecord.borrowRecord!!
                    borrowRecord.shouldReturnTime = DateUtil.addMonth(borrowRecord.shouldReturnTime, 1)
                    borrowRecordService.save(borrowRecord)
                }
                ApplyType.RETURN.code -> {
                    val borrowRecord: BorrowRecord = applyRecord.borrowRecord!!
                    borrowRecord.status = AffairStatus.DONE.code
                    borrowRecordService.save(borrowRecord)
                }
            }
        }
        model.addAttribute("applyRecord", applyRecord)

        return "redirect:/apply"
    }

    /**
     * 发起借阅申请
     */
    @GetMapping("/{userId}/{bookId}")
    fun borrow(@PathVariable @NotNull userId: Long,
               @PathVariable @NotNull bookId: Long,
               model: Model): String {
        val applyRecord: ApplyRecord = ApplyRecord()
        applyRecord.user = userService.one(userId)!!
        applyRecord.book = bookService.one(bookId)!!
        applyRecordService.save(applyRecord)
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
    fun list(model: Model): String {
        println("apply-list")
        val applyRecordList: List<ApplyRecord> = applyRecordService.list()
        model.addAttribute("applyRecordList", applyRecordList)
        return "/apply/applyList"
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull applyRecord: ApplyRecord, model: Model): String {
        println("apply-save")
        val applyRecord: ApplyRecord? = applyRecordService.save(applyRecord)
        applyRecord?.let {
            model.addAttribute("apply", applyRecord)
            return "redirect:/apply"
        } ?: log.info("apply-save: ApplyRecord not found")
        return ""
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