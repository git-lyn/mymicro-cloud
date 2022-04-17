package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.domain.AccountDO;
import com.lyn.eshop.auth.domain.AccountDTO;
import com.lyn.eshop.auth.domain.AccountQuery;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:49
 **/
public interface AccountService {
    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    List<AccountDTO> listByPage(AccountQuery query);

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    AccountDTO getById(Long id);

    /**
     * 新增账号
     * @param account 账号
     */
    Boolean save(AccountDTO account);

    /**
     * 更新账号
     * @param account 账号
     */
    Boolean update(AccountDTO account);

    /**
     * 更新密码
     * @param account 账号
     */
    Boolean updatePassword(AccountDTO account);

    /**
     * 删除账号
     * @param id 账号id
     */
    Boolean remove(Long id);
}
