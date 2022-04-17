package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.AccountRoleRelationshipDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:45
 **/
public interface AccountRoleRelationshipDAO {

    Long countByRoleId(Long roleId);

    List<AccountRoleRelationshipDO> listByAccountId(Long accountId);

    Boolean save(AccountRoleRelationshipDO relation);

    Boolean removeByAccountId(Long accountId);
}
