package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminMapper {
    @Delete({
        "delete from admin",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer adminId);

    @Insert({
        "insert into admin (admin_id, email, ",
        "password, name)",
        "values (#{adminId,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Admin record);

    int insertSelective(Admin record);

    @Select({
        "select",
        "admin_id, email, password, name",
        "from admin",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    @Update({
        "update admin",
        "set email = #{email,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR}",
        "where admin_id = #{adminId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Admin record);

    @Select(value = {
            "select *",
            "from admin",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    List<Admin> selectAdminByEmail(@Param("email")String email);
}