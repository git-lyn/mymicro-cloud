package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.domain.RoleDO;
import com.lyn.eshop.auth.domain.RoleDTO;
import com.lyn.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:07
 **/
public interface RoleService {

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDTO> listByPage(RoleQuery query);

    /**
     * 根据id查询角色DO对象
     * @param id 角色 id
     * @return 角色DO对象
     */
    RoleDTO getById(Long id);


    /**
     * 新增角色
     * @param role 角色DO对象
     */
    Boolean save(RoleDTO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     */
    Boolean update(RoleDTO role);

    /**
     * 删除角色
     * @param id 角色id
     */
    Boolean remove(Long id);

}
