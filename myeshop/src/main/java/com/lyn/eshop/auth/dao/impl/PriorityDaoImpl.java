package com.lyn.eshop.auth.dao.impl;

import com.lyn.eshop.auth.dao.PriorityDAO;
import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.auth.mapper.PriorityMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:31
 **/
@Repository
public class PriorityDaoImpl implements PriorityDAO {

    private static final Logger logger = LoggerFactory.getLogger(PriorityDaoImpl.class);

    @Autowired
    private PriorityMapper priorityMapper;

    @Override
    public List<PriorityDO> listRootPriorities() {
        try {
            return priorityMapper.listRootPriorities();
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public List<PriorityDO> listChildPriorities(Long parentId) {
        try {
            return priorityMapper.listChildPriorities(parentId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    public PriorityDO getPriorityById(Long id) {
        try {
            return priorityMapper.getPriorityById(id);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @Override
    public Boolean savePriority(PriorityDO priorityDO) {
        try {
            priorityMapper.savePriority(priorityDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    @Override
    public Boolean updatePriority(PriorityDO priorityDO) {
        try {
            priorityMapper.updatePriority(priorityDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    public Boolean removePriority(Long id){
        try {
            priorityMapper.removePriority(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 查询账号被授权的根菜单
     * @param parameters 查询参数
     * @return 菜单
     */
    public List<PriorityDO> listAuthroziedByAccountId(Map<String, Object> parameters){
        try {
            return priorityMapper.listAuthroziedByAccountId(parameters);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据权限编号判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param code 权限编号
     * @return 是否有授权记录
     */
    public Long countAuthorizedByCode(Long accountId, String code){
        try {
            return priorityMapper.countAuthorizedByCode(accountId, code);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据权限URL判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param url 权限url
     * @return 是否有授权记录
     */
    public Long countAuthorizedByUrl(Long accountId, String url){
        try {
            return priorityMapper.countAuthorizedByUrl(accountId, url);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 根据权限id查询账号id
     * @param priorityId 权限id
     * @return
     */
    public List<Long> listAccountIdsByPriorityId(Long priorityId){
        try {
            return priorityMapper.listAccountIdsByPriorityId(priorityId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }


}
