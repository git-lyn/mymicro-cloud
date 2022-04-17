package com.lyn.eshop.auth.composite;

import com.lyn.eshop.auth.service.PriorityOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 权限树节点：组合模式
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 18:36
 **/
public class Priority<T> {
    /**
     * id
     */
    private Long id;
    /**
     * 权限编号
     */
    private String code;
    /**
     * 权限URL
     */
    private String url;
    /**
     * 权限备注
     */
    private String priorityComment;
    /**
     * 权限类型
     */
    private Integer priorityType;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 权限的创建时间
     */
    private Date gmtCreate;
    /**
     * 权限的修改时间
     */
    private Date gmtModified;

    /**
     * 子权限节点
     */
    private List<Priority> children = new ArrayList<>();

    @Override
    public String toString() {
        return "PriorityDO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", priorityComment='" + priorityComment + '\'' +
                ", priorityType=" + priorityType +
                ", parentId=" + parentId +
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPriorityComment() {
        return priorityComment;
    }

    public void setPriorityComment(String priorityComment) {
        this.priorityComment = priorityComment;
    }

    public Integer getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(Integer priorityType) {
        this.priorityType = priorityType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    /**
     * 接收一个权限树访问者
     * @param visitor 权限树访问者
     */
    public <T> T execute(PriorityOperation<T> visitor){
        return visitor.doExecute(this);
    }

}

