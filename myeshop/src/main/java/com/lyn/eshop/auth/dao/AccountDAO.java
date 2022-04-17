package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.AccountDO;
import com.lyn.eshop.auth.domain.AccountQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:32
 **/
public interface AccountDAO {

    /**
     * 分页查询账号
     * @param query 账号查询条件
     * @return 账号
     */
    List<AccountDO> listByPage(AccountQuery query);

    /**
     * 根据id查询账号
     * @param id 账号id
     * @return 账号
     */
    AccountDO getById(Long id);

    /**
     * 新增账号
     * @param account 账号
     */
    Long save(AccountDO account);

    /**
     * 更新账号
     * @param account 账号
     */
    Boolean update(AccountDO account);

    /**
     * 更新密码
     * @param account 账号
     */
    Boolean updatePassword(AccountDO account);

    /**
     * 删除账号
     * @param id 账号id
     */
    Boolean remove(Long id);
}
