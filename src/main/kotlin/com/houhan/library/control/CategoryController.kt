package com.houhan.library.control

import com.houhan.library.entity.Category
import com.houhan.library.service.CategoryService
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
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/category")
@Controller
class CategoryController {
    val log: Logger = LoggerFactory.getLogger(CategoryController::class.java)
    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int, model: Model): String {
        var result = ""
        log.info("category-detail")
        val category: Category? = categoryService.one(id)
        category?.let {
            model.addAttribute("category", category)
            result = "/category/categorylist"
        } ?: log.info("category-detail: Category(id = $id) not found")
        return result
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            model: Model): String {
        log.info("category-list")
        val categoryList: Page<Category> = categoryService.list(pageIndex, pageSize)
        model.addAttribute("categoryList", categoryList)
        return "/category/categorylist"
    }

    @PostMapping()
    fun save(@RequestParam @NotNull category: Category, model: Model): String {
        log.info("category-save")
        val category: Category? = categoryService.save(category)
        category?.let {
            model.addAttribute("category", category)
            return "redirect:/category"
        } ?: log.info("category-save: Category not found")
        return ""
    }

    @PutMapping()
    fun update(@RequestParam @NotNull category: Category, model: Model): String {
        log.info("category-update")
        return "redirect:/category"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Int): String {
        log.info("category-delete")
        categoryService.delete(id)
        return "redirect:/category"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        log.info("category-toAdd")
        return "/category/categoryadd"
    }
}
