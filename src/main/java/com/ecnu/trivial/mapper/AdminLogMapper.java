package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.Admin;
import com.ecnu.trivial.model.AdminLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AdminLogMapper {
    @Delete({
        "delete from admin_log",
        "where log_id = #{logId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer logId);

    @Insert({
        "insert into admin_log (log_id, admin_id, submit_time, action_type)",
        "values (#{logId,jdbcType=INTEGER}, #{adminId,jdbcType=INTEGER}, ",
        "#{submitTime,jdbcType=TIMESTAMP}, #{actionType,jdbcType=INTEGER})"
    })
    int insert(AdminLog record);

    int insertSelective(Admin record);

    @Select({
        "select",
        "log_id, admin_id, submit_time, action_type",
        "from admin_log",
        "where log_id = #{logId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    AdminLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Admin record);

    @Update({
        "update admin_log",
        "set admin_id = #{adminId,jdbcType=INTEGER},",
          "submit_time = #{submitTime,jdbcType=TIMESTAMP},",
          "action_type = #{actionType,jdbcType=INTEGER}",
        "where log_id = #{logId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AdminLog record);

    @Select({"<script>",
            "select * ",
            "from admin_log",
            "<if test = \"adminId != 0\">where admin_id=#{adminId}</if>",
            "</script>"
    })
    @ResultMap("BaseResultMap")
    List<AdminLog> selectAdminLogsByPage(@Param("adminId")int adminId, RowBounds rowBounds);

    @Select({"<script>",
            "select count(distinct log_id) ",
            "from admin_log",
            "<if test = \"adminId != 0\">where admin_id=#{adminId}</if>",
            "</script>"
    })
    int countAdminLogs(@Param("adminId") int adminId);
}