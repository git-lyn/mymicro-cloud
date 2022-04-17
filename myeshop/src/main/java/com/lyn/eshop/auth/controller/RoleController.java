package com.lyn.eshop.auth.controller;

import com.lyn.eshop.auth.domain.*;
import com.lyn.eshop.auth.service.RoleService;
import com.lyn.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:53
 **/
@RestController
@RequestMapping("/auth/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色VO集合
     */
    @GetMapping("/")
    public List<RoleVO> listByPage(RoleQuery query) {
        try {
            List<RoleDTO> roles = roleService.listByPage(query);
            List<RoleVO> resultRoles = ObjectUtils.convertList(roles, RoleVO.class);
            return resultRoles;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<RoleVO>();
        }
    }

    /**
     * 根据id查询角色
     * @param id 角色id
     * @return 角色
     */
    @GetMapping("/{id}")
    public RoleVO getById(@PathVariable("id") Long id) {
        try {
            RoleDTO role = roleService.getById(id);
            RoleVO resultRole = role.clone(RoleVO.class);

            List<RolePriorityRelationshipDTO> relations = role.getRolePriorityRelations();
            List<RolePriorityRelationshipVO> resultRelations = ObjectUtils.convertList(
                    relations, RolePriorityRelationshipVO.class);

            resultRole.setRolePriorityRelations(resultRelations);

            return resultRole;
        } catch (Exception e) {
            logger.error("error", e);
            return new RoleVO();
        }
    }

    /**
     * 新增角色
     * @param role 角色
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody RoleVO role) {
        try {
            RoleDTO targetRole = role.clone(RoleDTO.class);

            List<RolePriorityRelationshipDTO> targetRelations = ObjectUtils.convertList(
                    role.getRolePriorityRelations(), RolePriorityRelationshipDTO.class);
            targetRole.setRolePriorityRelations(targetRelations);

            roleService.save(targetRole);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新角色
     * @param role 角色
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody RoleVO role) {
        try {
            RoleDTO targetRole = role.clone(RoleDTO.class);
            List<RolePriorityRelationshipDTO> targetRelations = ObjectUtils.convertList(
                    role.getRolePriorityRelations(), RolePriorityRelationshipDTO.class);
            targetRole.setRolePriorityRelations(targetRelations);

            roleService.update(targetRole);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return 角色
     */
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable("id") Long id) {
        try {
            return roleService.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }



}
