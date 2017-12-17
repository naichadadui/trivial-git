package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.GameHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GameHistoryMapper {
    @Delete({
        "delete from game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer gameId);

    @Insert({
        "insert into game_history (game_id, start_time, ",
        "end_time)",
        "values (#{gameId,jdbcType=INTEGER}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP})"
    })
    int insert(GameHistory record);

    int insertSelective(GameHistory record);

    @Select({
        "select",
        "game_id, start_time, end_time",
        "from game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    GameHistory selectByPrimaryKey(Integer gameId);

    int updateByPrimaryKeySelective(GameHistory record);

    @Update({
        "update game_history",
        "set start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP}",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GameHistory record);
}