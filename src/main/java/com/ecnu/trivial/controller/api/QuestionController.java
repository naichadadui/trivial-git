package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.QuestionService;
import com.ecnu.trivial.vo.QuestionsVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecnu.trivial.controller.api.APIBaseController.PAGE_SIZE;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /*
    * 当用户输入搜索关键词并搜索时，
    * 返回第一页的搜索结果searchQuestions
    * 并且返回总共的页码数maxPageNumber
    * */
    @RequestMapping(value="/searchQuestionsBySearchKey", method = RequestMethod.POST)
    public Map searchQuestionsBySearchKey(@RequestParam("searchContent")String searchContent, @RequestParam("searchType")int searchType){
        Map<String,Object> result = new HashMap<>();
        List<QuestionsVo> searchQuestions = questionService.getQuestionsBySearchKeyByPage(searchContent,searchType,1,PAGE_SIZE);
        int maxPageNumber = questionService.getMaxPageNumberBySearchKey(searchContent,searchType,PAGE_SIZE);
        result.put("maxPageNumber", maxPageNumber);
        result.put("searchQuestions",questionsToJSONArray(searchQuestions).toString());
        System.out.println(questionsToJSONArray(searchQuestions).toString());
        return result;
    }

    /*
    * 当用户跳转页面时
    * 返回该页的搜索结果searchQuestions
    * */
    @RequestMapping(value="/searchQuestionsBySearchKeyByPage", method = RequestMethod.POST)
    public Map searchUserBySearchKey(@RequestParam("searchContent")String searchContent, @RequestParam("searchType")int searchType, @RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<QuestionsVo> searchQuestions = questionService.getQuestionsBySearchKeyByPage(searchContent,searchType,pageNumber,PAGE_SIZE);
        result.put("searchQuestions",questionsToJSONArray(searchQuestions).toString());
        System.out.println(questionsToJSONArray(searchQuestions).toString());
        return result;
    }

    @RequestMapping(value="/deleteQuestionsById", method = RequestMethod.POST)
    public Map deleteQuestionsById(@RequestParam("questionIdArray")int[] questionIdArray){
        Map<String,Object> result = new HashMap<>();
        int delete = 0;
        delete = questionService.deleteQuestions(questionIdArray);
        result.put("deleteResult",delete);
        return result;
    }

    @RequestMapping(value="/addQuestions", method = RequestMethod.POST)
    public Map addQuestions(@RequestParam("content")String content,@RequestParam("trueAns")String trueAns,@RequestParam("wrongAns1")String wrongAns1,@RequestParam("wrongAns2")String wrongAns2,@RequestParam("wrongAns3")String wrongAns3,@RequestParam("type")int type){
        Map<String,Object> result = new HashMap<>();
        Questions question = new Questions(type,content,trueAns,wrongAns1,wrongAns2,wrongAns3);
        int addResult = questionService.addNewQuestion(question);
        result.put("addResult",addResult);
        return result;
    }

    private JSONArray questionsToJSONArray(List<QuestionsVo> questions){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int i = 1;
        for(QuestionsVo question:questions){
            jsonObject.put("id",question.getQuestionId());
            jsonObject.put("content",question.getContent());
            jsonObject.put("trueAns",question.getTrueAns());
            jsonArray.add(jsonObject);
            i++;
        }
        return jsonArray;
    }

}
