package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.Questions;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}