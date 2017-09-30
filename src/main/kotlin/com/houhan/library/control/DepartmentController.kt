package com.houhan.library.control

import com.houhan.library.entity.Department
import com.houhan.library.service.DepartmentService
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
@RequestMapping("/department")
@Controller
class DepartmentController {
    val log: Logger = LoggerFactory.getLogger(DepartmentController::class.java)
    @Autowired
    lateinit var departmentService: DepartmentService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int, model: Model): String {
        var result = ""
        println("department-detail")
        val department: Department? = departmentService.one(id)
        department?.let {
            model.addAttribute("department", department)
            result = "/department/departmentlist"
        } ?: log.info("department-detail: Department(id = $id) not found")
        return result
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            model: Model): String {
        println("department-list")
        val deptList: Page<Department> = departmentService.list(pageIndex, pageSize)
        model.addAttribute("deptList", deptList)
        return "/department/departmentlist"
    }

    @PostMapping()
    fun save(@RequestParam @NotNull department: Department, model: Model): String {
        println("department-save")
        val dept: Department? = departmentService.save(department)
        dept?.let {
            model.addAttribute("department", dept)
            return "redirect:/department"
        } ?: log.info("department-save: Department not found")
        return ""
    }

    @PutMapping()
    fun update(@RequestParam @NotNull department: Department, model: Model): String {
        println("department-update")
        return "redirect:/department"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Int): String {
        println("department-delete")
        departmentService.delete(id)
        return "redirect:/department"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("department-toAdd")
        return "/department/departmentadd"
    }
}
