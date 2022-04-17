package com.lyn.eshop.auth.service;

import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.auth.domain.PriorityDTO;

import java.util.List;
import java.util.Map;

/**
 * 权限管理service组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:32
 **/
public interface PriorityService {

    List<PriorityDTO> listRootPriorities();

    List<PriorityDTO> listChildPriorities(Long parentId);

    PriorityDTO getPriorityById(Long id);

    Boolean savePriority(PriorityDTO priorityDTO);

    Boolean updatePriority(PriorityDTO priorityDTO);

    Boolean removePriority(Long id);

    /**
     * 判断账号是否存在对指定编号的权限的授权记录
     * @param accountId 账号id
     * @param code 权限编号
     * @return 是否存在授权记录
     * @throws Exception
     */
    Boolean existAuthorizedByCode(Long accountId, String code);

    /**
     * 判断账号是否存在对指定url的权限的授权记录
     * @param accountId 账号id
     * @param url 权限url
     * @return 是否存在授权记录
     * @throws Exception
     */
    Boolean existAuthorizedByUrl(Long accountId, String url);


    /**
     * 查询账号被授权的根菜单
     * @param parameters 查询参数
     * @return 菜单
     */
    List<PriorityDTO> listAuthroziedByAccountId(Map<String, Object> parameters);

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
