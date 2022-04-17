package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.lyn.eshop.auth.domain.AccountRoleRelationshipDO;
import com.lyn.eshop.auth.mapper.AccountRoleRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:46
 **/
@Component
public class AccountRoleRelationshipDAOImpl implements AccountRoleRelationshipDAO {
    private static final Logger logger = LoggerFactory.getLogger(AccountRoleRelationshipDAOImpl.class);

    @Autowired
    private AccountRoleRelationshipMapper accountRoleRelationshipMapper;

    @Override
    public Long countByRoleId(Long roleId) {
        try {
            return accountRoleRelationshipMapper.countByRoleId(roleId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public List<AccountRoleRelationshipDO> listByAccountId(Long accountId) {
        try {
            return accountRoleRelationshipMapper.listByAccountId(accountId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean save(AccountRoleRelationshipDO relation) {
        try {
            accountRoleRelationshipMapper.save(relation);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean removeByAccountId(Long accountId) {
        try {
            accountRoleRelationshipMapper.removeByAccountId(accountId);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }
}
