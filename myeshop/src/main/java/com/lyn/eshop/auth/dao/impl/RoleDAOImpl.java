package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.RoleDAO;
import com.lyn.eshop.auth.domain.RoleDO;
import com.lyn.eshop.auth.domain.RoleQuery;
import com.lyn.eshop.auth.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-14 10:54
 **/
@Component
public class RoleDAOImpl implements RoleDAO {

    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDO> listByPage(RoleQuery query) {
        try {
            return roleMapper.listByPage(query);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public RoleDO getById(Long id) {
        try {
           return roleMapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean save(RoleDO role) {
        try {
            roleMapper.save(role);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean update(RoleDO role) {
        try {
            roleMapper.update(role);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean remove(Long id) {
        try {
            roleMapper.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }
}
