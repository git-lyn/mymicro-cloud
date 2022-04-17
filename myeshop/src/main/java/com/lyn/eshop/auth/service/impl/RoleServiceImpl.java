package com.lyn.eshop.auth.service.impl;

import com.lyn.eshop.auth.dao.AccountRoleRelationshipDAO;
import com.lyn.eshop.auth.dao.RoleDAO;
import com.lyn.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.lyn.eshop.auth.domain.*;
import com.lyn.eshop.auth.service.RoleService;
import com.lyn.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 11:20
 **/
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    @Autowired
    private AccountRoleRelationshipDAO accountRoleRelationshipDAO;


    @Override
    public List<RoleDTO> listByPage(RoleQuery query) {
        try {
            List<RoleDO> roleDOS = roleDAO.listByPage(query);
            return ObjectUtils.convertList(roleDOS, RoleDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 获取角色和权限的信息
     * @param id 角色 id
     * @return
     */
    @Override
    public RoleDTO getById(Long id) {
        try {

            RoleDO roleDO = roleDAO.getById(id);
            RoleDTO roleDTO = roleDO.clone(RoleDTO.class);

            List<RolePriorityRelationshipDO> rolePriorityRelationshipDOList = rolePriorityRelationshipDAO.listByRoleId(id);
            List<RolePriorityRelationshipDTO> priorityRelationshipDTOList = ObjectUtils.convertList(rolePriorityRelationshipDOList, RolePriorityRelationshipDTO.class);
            roleDTO.setRolePriorityRelations(priorityRelationshipDTOList);
            return roleDTO;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean save(RoleDTO role) {
        try {
            roleDAO.save( role.clone(RoleDO.class));

            for (RolePriorityRelationshipDTO rolePriorityRelation : role.getRolePriorityRelations()) {
                rolePriorityRelationshipDAO.save(rolePriorityRelation.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean update(RoleDTO role) {
        try {
            roleDAO.update(role.clone(RoleDO.class));
            rolePriorityRelationshipDAO.removeByRoleId(role.getId());
            for (RolePriorityRelationshipDTO rolePriorityRelation : role.getRolePriorityRelations()) {
                rolePriorityRelationshipDAO.save(rolePriorityRelation.clone(RolePriorityRelationshipDO.class));
            }
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        try {
            Long count = accountRoleRelationshipDAO.countByRoleId(id);
            if(count > 0)
                return false;
            roleDAO.remove(id);
            rolePriorityRelationshipDAO.removeByRoleId(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }
}
