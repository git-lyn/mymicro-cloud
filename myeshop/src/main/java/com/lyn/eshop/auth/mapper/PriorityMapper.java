package com.lyn.eshop.auth.mapper;

import com.lyn.eshop.auth.domain.PriorityDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 权限管理模块mapper组件
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:19
 **/
@Mapper
public interface PriorityMapper {

    /**
     * 查询根权限
     * @return
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority where parent_id IS NULL")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PriorityDO> listRootPriorities();

    /**
     * 查询子权限
     * @return
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority where parent_id = #{parentId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PriorityDO> listChildPriorities(@Param("parentId") Long parentId);

    /**
     * 根据id查询权限
     * @return
     */
    @Select("select" +
            "id," +
            "code," +
            "url," +
            "priority_comment," +
            "priority_type," +
            "parent_id," +
            "gmt_create," +
            "gmt_modified" +
            "from auth_priority where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    PriorityDO getPriorityById(@Param("id") Long id);

    @Insert("INSERT INTO auth_priority(id, code,url，priorityComment, priorityType, parentId, gmtCreate, gmtModified)" +
            "VALUES(#{id}, #{code},#{url}，#{priorityComment}, #{priorityType}, #{parentId}, #{gmtCreate}, #{gmtModified})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void savePriority(PriorityDO priorityDO);




    /**
     * 更新权限
     * @param priorityDO 权限DO对象
     */
    @Update("UPDATE auth_priority SET "
            + "code=#{code}, "
            + "url=#{url}, "
            + "priority_comment=#{priorityComment}, "
            + "priority_type=#{priorityType}, "
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updatePriority(PriorityDO priorityDO);

    /**
     * 查询账号被授权的根菜单
     * @param parameters 查询参数
     * @return 菜单
     */
    @Select("<script>"

            + "SELECT "
            + "id,"
            + "code,"
            + "url,"
            + "priority_comment,"
            + "priority_type,"
            + "parent_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM "
            + "("
            + "SELECT "
            + "p.id,"
            + "p.code,"
            + "p.url,"
            + "p.priority_comment,"
            + "p.priority_type,"
            + "p.parent_id,"
            + "p.gmt_create,"
            + "p.gmt_modified "
            + "FROM auth_account_role_relationship acr "
            + "JOIN auth_role_priority_relationship rpr ON acr.role_id=rpr.role_id "
            + "JOIN auth_priority p ON rpr.priority_id=p.id "
            + "WHERE acr.account_id=#{accountId} "

            + "<if test='parentId == null'>"
            + "AND p.parent_id IS NULL "
            + "AND p.priority_type=1 "
            + "</if>"

            + "<if test='parentId != null'>"
            + "AND p.parent_id=#{parentId} "
            + "</if>"

            + "UNION ALL "

            + "SELECT "
            + "p.id,"
            + "p.code,"
            + "p.url,"
            + "p.priority_comment,"
            + "p.priority_type,"
            + "p.parent_id,"
            + "p.gmt_create,"
            + "p.gmt_modified "
            + "FROM auth_account_priority_relationship apr "
            + "JOIN auth_priority p ON apr.priority_id=p.id "
            + "WHERE apr.account_id=#{accountId} "

            + "<if test='parentId == null'>"
            + "AND p.parent_id IS NULL "
            + "AND p.priority_type=1 "
            + "</if>"

            + "<if test='parentId != null'>"
            + "AND p.parent_id=#{parentId} "
            + "</if>"
            + ") a "

            + "</script>"
    )
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PriorityDO> listAuthroziedByAccountId(Map<String, Object> parameters);

    /**
     * 根据权限编号判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param code 权限编号
     * @return 是否有授权记录
     */
    @Select("SELECT SUM(cnt) "
            + "FROM "
            + "("
            + "SELECT count(*) cnt "
            + "FROM auth_account_role_relationship arr "
            + "JOIN auth_role_priority_relationship rpr ON arr.role_id=rpr.role_id "
            + "JOIN auth_priority p ON rpr.priority_id=p.id "
            + "WHERE arr.account_id=#{accountId} "
            + "AND p.code=#{code} "

            + "UNION ALL "

            + "SELECT count(*) cnt "
            + "FROM auth_account_priority_relationship apr "
            + "JOIN auth_priority p ON apr.priority_id=p.id "
            + "WHERE apr.account_id=#{accountId} "
            + "AND p.code=#{code} "
            + ") a "
    )
    Long countAuthorizedByCode(@Param("accountId") Long accountId,
                               @Param("code") String code);

    /**
     * 根据权限URL判断账号是否对这个权限有授权记录
     * @param accountId 账号id
     * @param url 权限url
     * @return 是否有授权记录
     */
    @Select("SELECT SUM(cnt) "
            + "FROM "
            + "("
            + "SELECT count(*) cnt "
            + "FROM auth_account_role_relationship arr "
            + "JOIN auth_role_priority_relationship rpr ON arr.role_id=rpr.role_id "
            + "JOIN auth_priority p ON rpr.priority_id=p.id "
            + "WHERE arr.account_id=#{accountId} "
            + "AND p.url=#{url} "

            + "UNION ALL "

            + "SELECT count(*) cnt "
            + "FROM auth_account_priority_relationship apr "
            + "JOIN auth_priority p ON apr.priority_id=p.id "
            + "WHERE apr.account_id=#{accountId} "
            + "AND p.url=#{url} "
            + ") a "
    )
    Long countAuthorizedByUrl(@Param("accountId") Long accountId,
                              @Param("url") String url);

    /**
     * 根据权限id查询账号id
     * @param priorityId 权限id
     * @return
     */
    @Select("SELECT account_id "
            + "FROM ("
            + "SELECT arr.account_id "
            + "FROM auth_account_role_relationship arr "
            + "JOIN auth_role_priority_relationship rpr ON arr.role_id=rpr.role_id "
            + "WHERE rpr.priority_id=#{priorityId} "

            + "UNION ALL "

            + "SELECT apr.account_id "
            + "FROM auth_account_priority_relationship apr "
            + "WHERE apr.priority_id=#{priorityId} "
            + ") a ")
    List<Long> listAccountIdsByPriorityId(
            @Param("priorityId") Long priorityId);


    /**
     * 删除权限
     * @param id 权限id
     */
    @Delete("DELETE FROM auth_priority WHERE id=#{id}")
    void removePriority(@Param("id") Long id);

}
