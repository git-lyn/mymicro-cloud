package com.lyn.eshop.auth.domain;

/**
 * 账号查询条件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 17:30
 **/
public class AccountQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;
    /**
     * 每页查询记录数
     */
    private Integer size;
    /**
     * 用户名
     */
    private String username;
    /**
     * 员工姓名
     */
    private String name;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
