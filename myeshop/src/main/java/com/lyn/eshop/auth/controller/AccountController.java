package com.lyn.eshop.auth.controller;

import com.lyn.eshop.auth.domain.AccountDTO;
import com.lyn.eshop.auth.domain.AccountQuery;
import com.lyn.eshop.auth.domain.AccountVO;
import com.lyn.eshop.auth.service.AccountService;
import com.lyn.eshop.common.util.CloneDirection;
import com.lyn.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:53
 **/
@RestController
@RequestMapping("/auth/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    /**
     * 账号管理service组件
     */
    @Autowired
    private AccountService accountService;

    /**
     * 分页查询账号
     * @param query 查询条件
     * @return 账号
     */
    @GetMapping("/")
    public List<AccountVO> listByPage(AccountQuery query) {
        try {
            List<AccountDTO> accounts = accountService.listByPage(query);
            List<AccountVO> resultAccounts = ObjectUtils.convertList(accounts,
                    AccountVO.class, CloneDirection.OPPOSITE);
            return resultAccounts;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<AccountVO>();
        }
    }

    /**
     * 根据id查询账号
     * @param id 角色id
     * @return 角色
     */
    @GetMapping("/{id}")
    public AccountVO getById(@PathVariable("id") Long id) {
        try {
            AccountDTO account = accountService.getById(id);
            AccountVO resultAccount = account.clone(AccountVO.class,
                    CloneDirection.OPPOSITE);
            return resultAccount;
        } catch (Exception e) {
            logger.error("error", e);
            return new AccountVO();
        }
    }

    /**
     * 新增账号
     * @param account 账号
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean save(@RequestBody AccountVO account) {
        try {
            AccountDTO targetAccount = account.clone(AccountDTO.class,
                    CloneDirection.FORWARD);
            accountService.save(targetAccount);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新账号
     * @param account 账号
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody AccountVO account) {
        try {
            AccountDTO targetAccount = account.clone(AccountDTO.class,
                    CloneDirection.FORWARD);
            accountService.update(targetAccount);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除账号
     * @param id 账号
     * @return 处理结果
     */
    @DeleteMapping("/{id}")
    public Boolean update(@PathVariable("id") Long id) {
        try {
            accountService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 修改密码
     * @param account 账号
     * @return 处理结果
     */
    @PutMapping("/password/{id}")
    public Boolean updatePassword(@RequestBody AccountVO account) {
        try {
            accountService.updatePassword(account.clone(AccountDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

}
