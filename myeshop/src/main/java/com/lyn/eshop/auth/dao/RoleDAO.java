package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.RoleDO;
import com.lyn.eshop.auth.domain.RoleQuery;

import java.util.List;

/**
 * 角色DAO组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 10:51
 **/
public interface RoleDAO {

    /**
     * 分页查询角色
     * @param query 查询条件
     * @return 角色DO对象集合
     */
    List<RoleDO> listByPage(RoleQuery query);

    /**
     * 根据id查询角色DO对象
     * @param id 角色 id
     * @return 角色DO对象
     */
    RoleDO getById(Long id);


    /**
     * 新增角色
     * @param role 角色DO对象
     */
    Boolean save(RoleDO role);

    /**
     * 更新角色
     * @param role 角色DO对象
     */
    Boolean update(RoleDO role);

    /**
     * 删除角色
     * @param id 角色id
     */
    Boolean remove(Long id);

}
