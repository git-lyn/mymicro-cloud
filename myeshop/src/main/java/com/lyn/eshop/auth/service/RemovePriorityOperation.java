package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.composite.Priority;
import com.lyn.eshop.auth.dao.PriorityDAO;
import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.common.util.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * 删除节点树
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 19:16
 **/
public class RemovePriorityOperation implements PriorityOperation<Boolean> {

    private PriorityDAO priorityDAO;

    @Autowired
    public RemovePriorityOperation(PriorityDAO priorityDAO) {
        this.priorityDAO = priorityDAO;
    }


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

        remotePriority(node);

        return true;
    }

    /**
     * 删除权限
     * @param node
     */
    private void remotePriority(Priority node) {
        priorityDAO.removePriority(node.getId());
    }
}
