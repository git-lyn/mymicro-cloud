package com.lyn.eshop.auth.dao;

import com.lyn.eshop.auth.domain.PriorityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 权限管理模块的DAO组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:29
 **/
public interface PriorityDAO {

    List<PriorityDO> listRootPriorities();

    List<PriorityDO> listChildPriorities(Long parentId);

    PriorityDO getPriorityById(Long id);

    Boolean savePriority(PriorityDO priorityDO);

    Boolean updatePriority(PriorityDO priorityDO);

    Boolean removePriority(Long id);

    /**
     * 查询账号被授权的根菜单
     * @param parameters 查询参数
     * @return 菜单
     */
    List<PriorityDO> listAuthroziedByAccountId(Map<String, Object> parameters);

    /**
     * 根据权限编号判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param code 权限编号
     * @return 是否有授权记录
     */
    Long countAuthorizedByCode(Long accountId, String code);

    /**
     * 根据权限URL判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param url 权限url
     * @return 是否有授权记录
     */
    Long countAuthorizedByUrl(Long accountId, String url);

    /**
     * 根据权限id查询账号id
     * @param priorityId 权限id
     * @return
     */
    List<Long> listAccountIdsByPriorityId(Long priorityId);


}
