package com.houhan.library.api

import com.houhan.library.entity.Department
import com.houhan.library.helper.JsonUtil
import com.houhan.library.service.DepartmentService
import com.houhan.library.web.ResponseBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
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
        log.info("department-detail")
        val department: Department? = departmentService.one(id)
        return ResponseBean(department)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10): ResponseBean<Page<Department>> {
        val deptList: Page<Department> = departmentService.list(pageIndex, pageSize)
        return ResponseBean(deptList)
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull department: String): ResponseBean<Int?> {
        log.info("department-save")
        var departmentTemp: Department? = JsonUtil.json2Obj(department, Department::class.java)
        try {
            departmentTemp?.let {
                val department = departmentService.save(departmentTemp)
                return ResponseBean(department!!.id)
            }
//            todo 待优化
            return ResponseBean(-1)
        } catch (e: RuntimeException) {
            log.info(e.message)
            return ResponseBean(e)
        }
    }

    @PutMapping()
    fun update(@PathVariable id: Int): String {
        log.info("department-update")
        return ""
    }

    @DeleteMapping()
    fun delete(@PathVariable @NotNull id: Int): String {
        log.info("department-delete")
        return ""
    }

}
