package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.Map;
import com.ecnu.trivial.model.MapKey;
import com.ecnu.trivial.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MapMapper {
    @Delete({
        "delete from map",
        "where map_id = #{mapId,jdbcType=INTEGER}",
          "and point_id = #{pointId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(MapKey key);

    @Insert({
        "insert into map (map_id, point_id, ",
        "top, left, state)",
        "values (#{mapId,jdbcType=INTEGER}, #{pointId,jdbcType=INTEGER}, ",
        "#{top,jdbcType=INTEGER}, #{left,jdbcType=INTEGER}, #{state,jdbcType=TINYINT})"
    })
    int insert(Map record);

    int insertSelective(Map record);

    @Select({
        "select",
        "map_id, point_id, top, left, state",
        "from map",
        "where map_id = #{mapId,jdbcType=INTEGER}",
          "and point_id = #{pointId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Map selectByPrimaryKey(MapKey key);

    int updateByPrimaryKeySelective(Map record);

    @Update({
        "update map",
        "set top = #{top,jdbcType=INTEGER},",
          "left = #{left,jdbcType=INTEGER},",
          "state = #{state,jdbcType=TINYINT}",
        "where map_id = #{mapId,jdbcType=INTEGER}",
          "and point_id = #{pointId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Map record);

}