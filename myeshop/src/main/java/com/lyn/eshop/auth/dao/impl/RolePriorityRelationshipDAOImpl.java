package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.lyn.eshop.auth.domain.RolePriorityRelationshipDO;
import com.lyn.eshop.auth.mapper.RolePriorityRelationshipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 19:04
 **/
@Repository
public class RolePriorityRelationshipDAOImpl implements RolePriorityRelationshipDAO {

    private static final Logger logger = LoggerFactory.getLogger(RolePriorityRelationshipDAOImpl.class);

    @Autowired
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;



    @Override
    public Long countByPriorityId(Long priorityId) {
        try {
            Long count = rolePriorityRelationshipMapper.countByPriorityId(priorityId);
            return count;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean save(RolePriorityRelationshipDO rolePriorityRelationshipDO)  {
        try {
            rolePriorityRelationshipMapper.save(rolePriorityRelationshipDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    @Override
    public List<RolePriorityRelationshipDO> listByRoleId(Long roleId) {
        try {
            return rolePriorityRelationshipMapper.listByRoleId(roleId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean removeByRoleId(Long roleId) {
        try {
            rolePriorityRelationshipMapper.removeByRoleId(roleId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
