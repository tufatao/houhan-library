package com.houhan.library.api

import com.houhan.library.entity.Department
import com.houhan.library.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    internal var departmentService: DepartmentService? = null

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int): Department? {
        println("department-detail")
        val department: Department? = departmentService?.one(id)
        return department
    }

    @GetMapping()
    fun list(@RequestParam status: Int): List<Department>? {
        println("department-list")
        val deptList: List<Department>? = departmentService?.list()
        return deptList
    }

    @PostMapping()
    fun save(@ModelAttribute department: Department): String {
        println("department-save")
        departmentService?.save(department)
        return "/department"
    }

    @PutMapping()
    fun update(@PathVariable id: Int): String {
        println("department-update")
        return ""
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Int): String {
        println("department-delete")
        return ""
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("departmentadd")
        return "/department/departmentadd"
    }
}