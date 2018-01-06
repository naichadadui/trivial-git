package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.QuestionService;
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

    @RequestMapping(value="/searchQuestionsBySearchKeyByPage", method = RequestMethod.POST)
    public Map searchUserBySearchKey(@RequestParam("searchContent")String searchContent, @RequestParam("searchType")String searchType, @RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<Questions> searchQuestions = questionService.getQuestionsBySearchKeyByPage(searchContent,searchType,pageNumber,PAGE_SIZE);
        result.put("searchUsers",searchQuestions);
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
    public Map addQuestions(){
        Map<String,Object> result = new HashMap<>();
        //List<Questions> searchQuestions = questionService.getQuestionsBySearchKeyByPage(searchContent,searchType,pageNumber,PAGE_SIZE);
        //result.put("searchUsers",searchQuestions);
        return result;
    }

}
