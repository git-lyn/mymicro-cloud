package com.lyn.eshop.auth.service.impl;

import com.lyn.eshop.auth.controller.AccountController;
import com.lyn.eshop.auth.dao.AccountDAO;
import com.lyn.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.lyn.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.lyn.eshop.auth.domain.*;
import com.lyn.eshop.auth.service.AccountService;
import com.lyn.eshop.common.util.DateProvider;
import com.lyn.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:50
 **/
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);


    /**
     * 账号管理DAO组件
     */
    @Autowired
    private AccountDAO accountDAO;
    /**
     * 账号和角色关系管理DAO组件
     */
    @Autowired
    private AccountRoleRelationshipDAO roleRelationDAO;
    /**
     * 账号和权限关系管理DAO组件
     */
    @Autowired
    private AccountPriorityRelationshipDAO priorityRelationDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    public List<AccountDTO> listByPage(AccountQuery query){
        try {
            List<AccountDO> accountDOS = accountDAO.listByPage(query);
            List<AccountDTO> accountDTOList = ObjectUtils.convertList(accountDOS, AccountDTO.class);
            return accountDTOList;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    public AccountDTO getById(Long id){
        try {

        AccountDO account = accountDAO.getById(id);
        AccountDTO resultAccount = account.clone(AccountDTO.class);

        List<AccountRoleRelationshipDO> roleRelations =
                roleRelationDAO.listByAccountId(id);
        List<AccountRoleRelationshipDTO> resultRoleRelations =
                ObjectUtils.convertList(roleRelations, AccountRoleRelationshipDTO.class);
        resultAccount.setRoleRelations(resultRoleRelations);

        List<AccountPriorityRelationshipDO> priorityRelations =
                priorityRelationDAO.listByAccountId(id);
        List<AccountPriorityRelationshipDTO> resultPriorityRelations =
                ObjectUtils.convertList(priorityRelations, AccountPriorityRelationshipDTO.class);
        resultAccount.setPriorityRelations(resultPriorityRelations);
        return resultAccount;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 新增账号
     * @param account 账号
     */
    public Boolean save(AccountDTO account){
        try {
            account.setGmtCreate(dateProvider.getCurrentTime());
            account.setGmtModified(dateProvider.getCurrentTime());
            Long accountId = accountDAO.save(account.clone(AccountDO.class));

            account.setId(accountId);
            saveRelations(account.clone(AccountDTO.class));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 新增关联关系
     * @param account 账号
     * @throws Exception
     */
    private void saveRelations(AccountDTO account) throws Exception {
        for(AccountRoleRelationshipDTO roleRelation : account.getRoleRelations()) {
            roleRelation.setAccountId(account.getId());
            roleRelation.setGmtCreate(dateProvider.getCurrentTime());
            roleRelation.setGmtModified(dateProvider.getCurrentTime());
            roleRelationDAO.save(roleRelation.clone(AccountRoleRelationshipDO.class));
        }

        for(AccountPriorityRelationshipDTO priorityRelation : account.getPriorityRelations()) {
            priorityRelation.setAccountId(account.getId());
            priorityRelation.setGmtCreate(dateProvider.getCurrentTime());
            priorityRelation.setGmtModified(dateProvider.getCurrentTime());
            priorityRelationDAO.save(priorityRelation.clone(AccountPriorityRelationshipDO.class));
        }
    }

    /**
     * 更新账号
     * @param account 账号
     */
    public Boolean update(AccountDTO account){
        try {
            account.setGmtModified(dateProvider.getCurrentTime());
            accountDAO.update(account.clone(AccountDO.class));

            roleRelationDAO.removeByAccountId(account.getId());
            priorityRelationDAO.removeByAccountId(account.getId());
            saveRelations(account.clone(AccountDTO.class));
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
    public Boolean updatePassword(AccountDTO account){
        try {
            account.setGmtModified(dateProvider.getCurrentTime());
            accountDAO.updatePassword(account.clone(AccountDO.class));
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
   public Boolean remove(Long id){
       try {
           roleRelationDAO.removeByAccountId(id);
           priorityRelationDAO.removeByAccountId(id);
           accountDAO.remove(id);
       } catch (Exception e) {
           logger.error("error", e);
           return false;
       }
       return true;
   }

}
