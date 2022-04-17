package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.composite.Priority;
import com.lyn.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.lyn.eshop.auth.dao.PriorityDAO;
import com.lyn.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.common.util.BeanCopierUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限树节点的关联检查访问者
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 18:42
 **/
@Component
@Scope("prototype")
public class RelatedCheckPriorityOperation implements PriorityOperation<Boolean> {

    /**
     * 关联检查结果
     */
    private Boolean relateCheckResult = false;

    /**
     * 权限管理模块的DAO组件
     */
    private PriorityDAO priorityDAO;

    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    public RelatedCheckPriorityOperation(
            PriorityDAO priorityDAO,
            AccountPriorityRelationshipDAO accountPriorityRelationshipDAO,
            RolePriorityRelationshipDAO rolePriorityRelationshipDAO) {
        this.priorityDAO = priorityDAO;
        this.accountPriorityRelationshipDAO = accountPriorityRelationshipDAO;
        this.rolePriorityRelationshipDAO = rolePriorityRelationshipDAO;
    }

    /**
     * 访问权限树节点
     * @param node
     */
    @Override
    public Boolean doExecute(Priority node) {
        List<PriorityDO> priorityDOS = priorityDAO.listChildPriorities(node.getId());
        if (priorityDOS != null && priorityDOS.size() > 0) {
            for (PriorityDO priorityDO : priorityDOS) {
                Priority priorityNode = new Priority();
                BeanCopierUtils.copyProperties(priorityDO, priorityNode);
                priorityNode.execute(this);
            }
        }
        if (relateCheck(node)) {
            relateCheckResult = true;
        }
        return relateCheckResult;
    }

    /**
     * 检查权限有没有被任何一个账号 或者  角色 关联
     * @param priorityNode
     * @return 是否被任何一个角色或者账号关联， 返回true 就关联了
     */
    public Boolean relateCheck(Priority priorityNode) {
        Long roleRelatedCount = rolePriorityRelationshipDAO.countByPriorityId(priorityNode.getId());
        if(roleRelatedCount != null && roleRelatedCount > 0)
            return true;
        Long accountRelatedCount = accountPriorityRelationshipDAO.countByPriorityId(priorityNode.getId());

        if(accountRelatedCount != null && accountRelatedCount > 0)
            return true;
        return false;
    }

    public Boolean getRelateCheckResult() {
        return relateCheckResult;
    }
}
