package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.GameHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface GameHistoryMapper {
    @Delete({
        "delete from game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer gameId);

    @Insert({
        "insert into game_history (game_id, start_time, ",
        "end_time, winner_id)",
        "values (#{gameId,jdbcType=INTEGER}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{winnerId,jdbcType=INTEGER})"
    })
    int insert(GameHistory record);

    int insertSelective(GameHistory record);

    @Select({
        "select",
        "game_id, start_time, end_time, winner_id",
        "from game_history",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    GameHistory selectByPrimaryKey(Integer gameId);

    int updateByPrimaryKeySelective(GameHistory record);

    @Update({
        "update game_history",
        "set start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
            "winner_id = #{winnerId,jdbcType=INTEGER}",
        "where game_id = #{gameId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GameHistory record);

    @Select({
            "select * ",
            "from game_history ",
            "order by game_id desc ",
            "limit 0,2 "
    })
    @ResultMap("BaseResultMap")
    List<GameHistory> getLatestTwoGames();

    @Select({
            "select game_history.* ",
            "from game_history join `user`",
            "where `user`.user_id = game_history.winner_id and `user`.`name` like CONCAT('%',#{winnerName},'%') and game_history.start_time like CONCAT('%',#{startTime},'%') and game_history.end_time like CONCAT('%',#{endTime},'%') ",
    })
    List<GameHistory> selectGameHistoryBySearchKeyByPage(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("winnerName") String winnerName, RowBounds rowBounds);

    @Select({
            "select count(distinct game_id) ",
            "from game_history natural join `user`",
            "where `user`.`name` like CONCAT('%',#{winnerName},'%') and game_history.start_time like CONCAT('%',#{startTime},'%') and game_history.end_time like CONCAT('%',#{endTime},'%') ",
    })
    int countGameHistory(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("winnerName") String winnerName);
}