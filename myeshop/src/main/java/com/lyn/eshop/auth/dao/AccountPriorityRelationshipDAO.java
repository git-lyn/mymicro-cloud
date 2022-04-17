package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.AccountPriorityRelationshipDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账号和权限 关联关系的 DAO组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 19:00
 **/
public interface AccountPriorityRelationshipDAO {

    Long countByPriorityId(Long priorityId);

    /**
     * 根据账号id查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    List<AccountPriorityRelationshipDO> listByAccountId(Long accountId);

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号id
     */
    Boolean removeByAccountId(Long accountId);
}
