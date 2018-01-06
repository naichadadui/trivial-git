package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.Questions;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionsMapper {
    @Delete({
        "delete from questions",
        "where question_id = #{questionId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer questionId);

    @Insert({
        "insert into questions (question_id, type, ",
        "content, true_ans, ",
        "false_ans1, false_ans2, ",
        "false_ans3)",
        "values (#{questionId,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{content,jdbcType=VARCHAR}, #{trueAns,jdbcType=VARCHAR}, ",
        "#{falseAns1,jdbcType=VARCHAR}, #{falseAns2,jdbcType=VARCHAR}, ",
        "#{falseAns3,jdbcType=VARCHAR})"
    })
    int insert(Questions record);

    int insertSelective(Questions record);

    @Select({
        "select",
        "question_id, type, content, true_ans, false_ans1, false_ans2, false_ans3",
        "from questions",
        "where question_id = #{questionId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Questions selectByPrimaryKey(Integer questionId);

    int updateByPrimaryKeySelective(Questions record);

    @Update({
        "update questions",
        "set type = #{type,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "true_ans = #{trueAns,jdbcType=VARCHAR},",
          "false_ans1 = #{falseAns1,jdbcType=VARCHAR},",
          "false_ans2 = #{falseAns2,jdbcType=VARCHAR},",
          "false_ans3 = #{falseAns3,jdbcType=VARCHAR}",
        "where question_id = #{questionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Questions record);

    @Select({
            "select *",
            "from questions",
            "where type = #{type}",
            "order by rand() limit 0,50"

    })
    @ResultMap("BaseResultMap")
    /*从数据库中按type随机选取50道题目
    * 这个sql语句效率不高 后面最好改进一下*/
    List<Questions> selectQuestionsByType(Integer type);

    @Select({
            "select *",
            "from questions",
            "order by rand() limit 0,50"

    })
    @ResultMap("BaseResultMap")
    /*从数据库中按type随机选取50道题目
    * 这个sql语句效率不高 后面最好改进一下*/
    List<Questions> selectFiftyQuestions();

    @Select({
            "select true_ans ",
            "from questions ",
            "where question_id = #{questionId}"
    })
    String selectTrueAnswerByQuestionId(int questionId);

    @Select({"<script>",
            "select * ",
            "from questions ",
            "where content like CONCAT('%',#{searchContent},'%') ",
            "<if test = \"searchType == 0\">and type=#{searchType}</if>",
            "</script>"
    })
    List<Questions> selectQuestionsBySearchKeyByPage(@Param("searchContent") String content,@Param("searchType") int type, RowBounds rowBounds);

    @Select({"<script>",
            "select count(distinct question_id) ",
            "from questions",
            "where content like CONCAT('%',#{searchContent},'%') ",
            "<if test = \"searchType == 0\">and type=#{searchType}</if>",
            "</script>"
    })
    int countQuestions(@Param("searchContent") String content,@Param("searchType") int type);
}