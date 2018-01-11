package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.AdminLogMapper;
import com.ecnu.trivial.mapper.QuestionsMapper;
import com.ecnu.trivial.model.AdminLog;
import com.ecnu.trivial.model.QuestionType;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.QuestionService;
import com.ecnu.trivial.vo.QuestionsVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl extends BaseServiceImpl implements QuestionService{
    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private AdminLogMapper adminLogMapper;

    @Override
    public List<QuestionsVo> getQuestionsBySearchKeyByPage(String content, int type, int pageNumber, int pageSize) {
        List<Questions> questions = questionsMapper.selectQuestionsBySearchKeyByPage(content,type,new RowBounds((pageNumber-1)*pageSize,pageSize));
        List<QuestionsVo> questionsVos = questions.stream().map(this::parse).collect(Collectors.toList());
        return questionsVos;
    }

    @Override
    public int getMaxPageNumberBySearchKey(String content,int type,int pageSize){
        int pageNum = 0;
        int questionCount = questionsMapper.countQuestions(content,type);
        pageNum = questionCount/pageSize;
        if(questionCount%pageSize!=0)
            pageNum+=1;
        return pageNum;
    }

    @Override
    public int deleteQuestions(int[] questionIdArray,int adminId) {
        int questionCount = 0;
        for(int i = 0;i<questionIdArray.length;i++) {
            AdminLog adminLog = new AdminLog(adminId,new Date(),questionIdArray[i]);
            adminLogMapper.insertSelective(adminLog);
            questionCount = questionsMapper.deleteByPrimaryKey(questionIdArray[i]);
            questionCount++;
        }
        return questionCount;
    }

//    @Override
//    public int addNewQuestion(Questions questions)
//    {
//        return questionsMapper.insertSelective(questions);
//    }

    private QuestionsVo parse(Questions questions) {
        QuestionsVo result = new QuestionsVo();
        BeanUtils.copyProperties(questions, result);
        result.setTypeStr(QuestionType.valueOf(questions.getType()).getTypeStr());
        return result;
    }
}
