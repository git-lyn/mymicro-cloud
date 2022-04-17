package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.composite.Priority;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 查询授权权限的操作
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-16 12:05
 **/
@Component
@Scope("prototype")
public class QueryAuthorizedPriorityOperation implements PriorityOperation<Boolean> {


    @Override
    public <T> T doExecute(Priority priority) {
        return null;
    }
}
