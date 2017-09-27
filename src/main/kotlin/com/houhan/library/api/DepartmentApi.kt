package com.houhan.library.api

import com.houhan.library.entity.Department
import com.houhan.library.service.DepartmentService
import com.houhan.library.web.ResponseBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

/**
 * @describe {}<br/>
 * @auther tufatao
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/api/department")
@RestController
class DepartmentApi {
    val log: Logger = LoggerFactory.getLogger(DepartmentApi::class.java)

    @Autowired
    lateinit var departmentService: DepartmentService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int): ResponseBean<Department?> {
        println("department-detail")
        val department: Department? = departmentService.one(id)
        return ResponseBean(department)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            model: Model): ResponseBean<Page<Department>> {
        val deptList: Page<Department> = departmentService.list(pageIndex, pageSize)
        return ResponseBean(deptList)
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull department: Department): ResponseBean<Department?> {
        println("department-save")
        val department = departmentService.save(department)
        return ResponseBean(department)
    }

    @PutMapping()
    fun update(@PathVariable id: Int): String {
        println("department-update")
        return ""
    }

    @DeleteMapping()
    fun delete(@PathVariable @NotNull id: Int): String {
        println("department-delete")
        return ""
    }

}