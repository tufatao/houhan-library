package com.houhan.library.control

import com.houhan.library.entity.Role
import com.houhan.library.service.RoleService
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
 * @date 2017/9/3 16:09
 * @version V0.1
 */
@RequestMapping("/role")
@Controller
class RoleController {
    val log: Logger = LoggerFactory.getLogger(RoleController::class.java)
    @Autowired
    lateinit var roleService: RoleService

    @GetMapping("/{id}")
    fun detail(@PathVariable @NotNull id: Int, model: Model): String {
        var result = ""
        println("role-detail")
        val role: Role? = roleService.one(id)
        role?.let {
            model.addAttribute("role", role)
            result = "/role/rolelist"
        } ?: log.info("role-detail: Role(id = $id) not found")
        return result
    }

    @GetMapping()
    fun list(model: Model): String {
        println("role-list")
        val roleList: List<Role> = roleService.list()
        model.addAttribute("roleList", roleList)
        return "/role/rolelist"
    }

    @PostMapping()
    fun save(@ModelAttribute @NotNull role: Role, model: Model): String {
        println("role-save")
        val dept: Role? = roleService.save(role)
        dept?.let {
            model.addAttribute("role", dept)
            return "redirect:/role"
        } ?: log.info("role-save: Role not found")
        return ""
    }

    @PutMapping()
    fun update(@ModelAttribute @NotNull role: Role, model: Model): String {
        println("role-update")
        return "redirect:/role"
    }

    @DeleteMapping()
    fun delete(@PathVariable id: Int): String {
        println("role-delete")
        roleService.delete(id)
        return "redirect:/role"
    }

    @GetMapping("/toadd")
    fun toAdd(): String {
        println("role-toAdd")
        return "/role/roleadd"
    }
}