package com.lyn.eshop.auth.domain;

import com.lyn.eshop.common.util.AbstractObject;

import java.util.Date;

/**
 * 账户和权限关联关系DO
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 17:59
 **/
public class AccountPriorityRelationshipDO extends AbstractObject {

    private Long id;
    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 权限id
     */
    private Long priorityId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    @Override
    public String toString() {
        return "AccountPriorityRelationshipDO{" +
                "gmtCreate=" + gmtCreate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
