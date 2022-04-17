package com.lyn.eshop.auth.mapper;

import com.lyn.eshop.auth.domain.AccountPriorityRelationshipDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 账户和权限关联关系的mapper类
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-09 18:05
 **/
@Mapper
public interface AccountPriorityRelationshipMapper {


    /**
     * 根据权限id查询账户关联关联的数量
     * @param priorityId
     * @return
     */
    @Select("SELECT COUNT(*) FROM auth_account_priority_relationship " +
            "WHERE priority_id = #{priorityId}")
    Long countByPriorityId(@Param("priorityId") Long priorityId);

    /**
     * 根据账号id查询账号和权限的关联关系
     * @param accountId 账号id
     * @return 账号和权限的关联关系
     */
    @Select("SELECT "
            + "id,"
            + "account_id,"
            + "priority_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_account_priority_relationship "
            + "WHERE account_id=#{accountId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "priority_id", property = "priorityId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<AccountPriorityRelationshipDO> listByAccountId(
            @Param("accountId") Long accountId);

    /**
     * 新增账号和权限的关联关系
     * @param accountPriorityRelationshipDO
     */
    @Insert("INSERT INTO auth_account_priority_relationship("
            + "account_id,"
            + "priority_id,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{accountId},"
            + "#{priorityId},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(AccountPriorityRelationshipDO accountPriorityRelationshipDO);

    /**
     * 根据账号id删除账号和权限的关联关系
     * @param accountId 账号id
     */
    @Delete("DELETE FROM auth_account_priority_relationship WHERE account_id=#{accountId}")
    void removeByAccountId(@Param("accountId") Long accountId);

}
