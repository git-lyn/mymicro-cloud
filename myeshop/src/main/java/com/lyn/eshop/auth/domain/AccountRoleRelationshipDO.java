package com.lyn.eshop.auth.domain;

import com.lyn.eshop.common.util.AbstractObject;

import java.util.Date;

/**
 * 账号角色关系DO
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:42
 **/
public class AccountRoleRelationshipDO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 角色id
     */
    private Long roleId;
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
        return "AccountRoleRelationshipDO{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", roleId=" + roleId +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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