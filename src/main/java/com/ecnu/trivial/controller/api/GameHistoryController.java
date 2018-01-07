package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.controller.web.BaseController;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.vo.GameHistoryVo;
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
@RequestMapping("/api/gameRecord")
public class GameHistoryController extends BaseController {
    @Autowired
    private GameHistoryService gameHistoryService;
    /*
    * 当用户输入搜索关键词并搜索时，
    * 返回第一页的搜索结果searchGameHistory
    * 并且返回总共的页码数maxPageNumber
    * */
    @RequestMapping(value="/searchGameRecordBySearchKey", method = RequestMethod.POST)
    public Map searchGameRecordBySearchKey(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("winnerName")String winnerName){
        Map<String,Object> result = new HashMap<>();
        List<GameHistoryVo> gameHistoryVos = gameHistoryService.getGameHistoryBySearchKeyByPage(startTime,endTime,winnerName,1,PAGE_SIZE);
        int maxPageNumber = gameHistoryService.getMaxPageNumberBySearchKey(startTime,endTime,winnerName,PAGE_SIZE);
        result.put("maxPageNumber", maxPageNumber);
        result.put("searchGameHistory", gameHistoryVosToJSONArray(gameHistoryVos).toString());
        System.out.println(gameHistoryVosToJSONArray(gameHistoryVos).toString());
        return result;
    }

    /*
    * 当用户跳转页面时
    * 返回该页的搜索结果searchAdminLogs
    * */
    @RequestMapping(value="/admin/getAdminLogBySearchKeyByPageNumber", method = RequestMethod.POST)
    public Map getAdminLogByPageNumber(@RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,@RequestParam("winnerName")String winnerName,@RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<GameHistoryVo> gameHistoryVos = gameHistoryService.getGameHistoryBySearchKeyByPage(startTime,endTime,winnerName,1,PAGE_SIZE);
        result.put("searchGameHistory", gameHistoryVosToJSONArray(gameHistoryVos).toString());
        System.out.println(gameHistoryVosToJSONArray(gameHistoryVos).toString());
        return result;
    }

    private JSONArray gameHistoryVosToJSONArray(List<GameHistoryVo> gameHistoryVos){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int i = 1;
        for(GameHistoryVo gameHistoryVo:gameHistoryVos){
            jsonObject.put("id",gameHistoryVo.getGameId());
            jsonObject.put("startTime",gameHistoryVo.getStartTimeStr());
            jsonObject.put("endTime",gameHistoryVo.getEndTimeStr());
            jsonObject.put("winner",gameHistoryVo.getWinnerName());
            jsonArray.add(jsonObject);
            i++;
        }
        return jsonArray;
    }
}
