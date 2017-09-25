package com.houhan.library.api

import com.houhan.library.entity.Role
import com.houhan.library.repository.RoleRepo
import com.houhan.library.service.RoleService
import com.houhan.library.web.ResultBean
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
@RequestMapping("/api/role")
@RestController
class RoleApi {
    val log: Logger = LoggerFactory.getLogger(RoleApi::class.java)

    @Autowired
    lateinit var roleService: RoleService
    @Autowired
    lateinit var roleRepo: RoleRepo

    @PostMapping()
    fun save(@ModelAttribute @NotNull role: Role): ResultBean<Role?> {
        println("role-save")
        val role = roleService.save(role)
        return ResultBean(role)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int, model: Model): ResultBean<Role?> {
        println("apply-detail")
        val role: Role? = roleRepo.findOne(id)
        role?.let {

        } ?: log.info("apply-detail: Role(id = $id) not found")

        return ResultBean(role)
    }

    @PostMapping("/query")
    fun list(
            @RequestParam pageIndex: Int = 1,
            @RequestParam pageSize: Int = 10,
            model: Model): ResultBean<Page<Role>> {
        println("apply-list")

        val applyPage: Page<Role> = roleService.list(pageIndex, pageSize)

        return ResultBean(applyPage)
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull role: Role, model: Model): String {
        println("apply-update")
        return "redirect:/apply"
    }

    @DeleteMapping()
    fun delete(@RequestParam @NotNull id: Int): String {
        println("apply-delete")
        roleRepo.delete(id)
        return "redirect:/apply"
    }

}
