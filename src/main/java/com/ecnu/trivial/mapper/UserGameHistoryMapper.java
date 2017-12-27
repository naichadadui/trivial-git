package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.model.UserGameHistory;
import com.ecnu.trivial.model.UserGameHistoryKey;
import com.ecnu.trivial.vo.UserGameHistoryVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserGameHistoryMapper {
    @Delete({
        "delete from user_game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(UserGameHistoryKey key);

    @Insert({
        "insert into user_game_history (game_id, user_id, ",
        "score)",
        "values (#{gameId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{score,jdbcType=INTEGER})"
    })
    int insert(UserGameHistory record);

    int insertSelective(UserGameHistory record);

    @Select({
        "select",
        "game_id, user_id, score",
        "from user_game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserGameHistory selectByPrimaryKey(UserGameHistoryKey key);

    int updateByPrimaryKeySelective(UserGameHistory record);

    @Update({
        "update user_game_history",
        "set score = #{score,jdbcType=INTEGER}",
        "where game_id = #{gameId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserGameHistory record);

    @Select({
            "select *",
            "from user_game_history natural join game_history",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    List<UserGameHistory> selectUserGameHistoryByUserId(int userId);
}