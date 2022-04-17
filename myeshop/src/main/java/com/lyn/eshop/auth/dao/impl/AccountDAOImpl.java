package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.AccountDAO;
import com.lyn.eshop.auth.domain.AccountDO;
import com.lyn.eshop.auth.domain.AccountQuery;
import com.lyn.eshop.auth.mapper.AccountMapper;
import com.lyn.eshop.auth.mapper.AccountRoleRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:34
 **/
@Component
public class AccountDAOImpl implements AccountDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRoleRelationshipMapper accountRoleRelationshipMapper;


    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    @Override
    public List<AccountDO> listByPage(AccountQuery query){
        return accountMapper.listByPage(query);
    }

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    @Override
    public AccountDO getById(Long id){
        return accountMapper.getById(id);
    }

    /**
     * 新增账号
     * @param account 账号
     */
    @Override
    public Long save(AccountDO account){
        try {
            accountMapper.save(account);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return account.getId();
    }

    /**
     * 更新账号
     * @param account 账号
     */
    @Override
    public Boolean update(AccountDO account){
        try {
            accountMapper.update(account);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 更新密码
     * @param account 账号
     */
    @Override
    public Boolean updatePassword(AccountDO account){
        try {
            accountMapper.updatePassword(account);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除账号
     * @param id 账号id
     */
    @Override
    public Boolean remove(Long id){
        try {
            accountMapper.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }


}
