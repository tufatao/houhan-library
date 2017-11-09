package com.houhan.library.api

import com.houhan.library.entity.Category
import com.houhan.library.repository.CategoryRepo
import com.houhan.library.service.CategoryService
import com.houhan.library.web.ResponseBean
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
@RequestMapping("/api/category")
@RestController
class CategoryApi {
    val log: Logger = LoggerFactory.getLogger(CategoryApi::class.java)

    @Autowired
    lateinit var categoryService: CategoryService
    @Autowired
    lateinit var categoryRepo: CategoryRepo

    @PostMapping()
    fun save(@ModelAttribute @NotNull category: Category): ResponseBean<Int?> {
        log.info("category-save")
        try {
            val category = categoryService.save(category)
            return ResponseBean(category!!.id)
        } catch (e: RuntimeException) {
            log.info(e.message)
            return ResponseBean(e)
        }
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int): ResponseBean<Category?> {
        log.info("apply-detail")
        val category: Category? = categoryRepo.findOne(id)
        category?.let {

        } ?: log.info("apply-detail: Category(id = $id) not found")

        return ResponseBean(category)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10): ResponseBean<Page<Category>> {
        log.info("apply-list")

        val applyPage: Page<Category> = categoryService.list(pageIndex, pageSize)

        return ResponseBean(applyPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull category: Category): String {
        log.info("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Int): String {
        log.info("apply-delete")
        categoryRepo.delete(id)
        return "redirect:/apply"
    }

}
