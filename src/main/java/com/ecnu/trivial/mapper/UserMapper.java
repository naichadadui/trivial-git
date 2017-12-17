package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user (user_id, email, ",
        "password, name, ",
        "score)",
        "values (#{userId,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{score,jdbcType=INTEGER})"
    })
    int insert(User record);

    int insertSelective(User record);

    @Select({
        "select",
        "user_id, email, password, name, score",
        "from user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set email = #{email,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);


    @Select({
            "select *",
            "from user",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    List<User> selectByEmail(@Param("email")String email);
}