package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.RolePriorityRelationshipDO;

import java.util.List;

/**
 * 角色和账号 关联关系 DAO组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 19:03
 **/
public interface RolePriorityRelationshipDAO {

    /**
     * 新增角色和权限的关联关系
     * @param rolePriorityRelationshipDO
     * @throws Exception
     */
    Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO) throws Exception;

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     * @throws Exception
     */
    Long countByPriorityId(Long priorityId) ;

    /**
     * 根据角色id查询角色和权限的关系
     * @param roleId 角色id
     * @return 角色权限关系DO对象集合
     * @throws Exception
     */
    List<RolePriorityRelationshipDO> listByRoleId(Long roleId) throws Exception;

    /**
     * 根据角色id删除角色权限关联关系
     * @param roleId 角色id
     * @throws Exception
     */
    Boolean removeByRoleId(Long roleId) throws Exception;
}
