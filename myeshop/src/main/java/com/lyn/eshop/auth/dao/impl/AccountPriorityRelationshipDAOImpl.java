package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.lyn.eshop.auth.domain.AccountPriorityRelationshipDO;
import com.lyn.eshop.auth.mapper.AccountPriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 19:01
 **/
@Repository
public class AccountPriorityRelationshipDAOImpl implements AccountPriorityRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountPriorityRelationshipDAOImpl.class);

    @Autowired
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    @Override
    public Long countByPriorityId(Long priorityId) {
        try {
            Long count = accountPriorityRelationshipMapper.countByPriorityId(priorityId);
            return count;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据账号id查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    public List<AccountPriorityRelationshipDO> listByAccountId(Long accountId){
        try {
            return accountPriorityRelationshipMapper.listByAccountId(accountId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    public Boolean save(AccountPriorityRelationshipDO accountPriorityRelationshipDO){
        try {
            accountPriorityRelationshipMapper.save(accountPriorityRelationshipDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号id
     */
    public  Boolean removeByAccountId(Long accountId){
        try {
            accountPriorityRelationshipMapper.removeByAccountId(accountId);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

}
