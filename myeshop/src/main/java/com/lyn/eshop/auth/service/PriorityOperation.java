package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.composite.Priority;

/**
 * 权限操作接口
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 18:35
 **/
public interface PriorityOperation<T> {

    /**
     * 访问权限树节点；执行这个操作
     * @param priority
     */
    <T> T doExecute(Priority priority);
}
