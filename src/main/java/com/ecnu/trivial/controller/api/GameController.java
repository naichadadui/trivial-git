package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value="/api/game")
public class GameController extends APIBaseController{
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    /*用户选择一个房间进入
    * enterResult：表示进入房间是否成功  1：成功 0：失败
    * returnMessage:对应的提示消息
    * */
    @RequestMapping(value="/enterRoom", method = RequestMethod.POST)
    public Map EnterRoom(@RequestParam("roomId")int roomId){
        Map<String,Object> result = new HashMap<>();
        int enterResult = 0;
        String returnMessage = "";
        boolean isEnterRoomSuccess = false;
        User user = userService.getCurrentUser(getCurrentUserID());
        isEnterRoomSuccess = gameService.enterRoom(user,roomId);
        if (isEnterRoomSuccess) {
            enterResult = 1;
            returnMessage = "Enter room successfully.";
        }
        else {
            enterResult = 0;
            returnMessage = "Fail to enter room.";
        }
        result.put("enterResult",enterResult);
        result.put("returnMessage",returnMessage);
        return result;
    }

    /*玩家点击ready，表示准许游戏开始
    * */
    @RequestMapping(value="/ready", method = RequestMethod.POST)
    public void ready(@RequestParam("roomId")int roomId){
        //Map<String,Object> result = new HashMap<>();
        User user = userService.getCurrentUser(getCurrentUserID());
        gameService.ready(user, roomId);
    }

    @RequestMapping(value = "/dice",method = RequestMethod.POST)
    public void dice(@RequestParam("roomId")int roomId){
        //User user = userService.getCurrentUser(getCurrentUserID());
        gameService.dice(roomId);
    }

    /*玩家点击闪烁的小马
    * 弹出框中显示问题内容
    * 这里还存在问题：随机显示选项顺序*/
    @RequestMapping(value = "/showQuestion",method = RequestMethod.POST)
    public Map showQuestion(@RequestParam("roomId")int roomId){
        Map<String,Object> result = new HashMap<>();
        //User user = userService.getCurrentUser(getCurrentUserID());
        Questions curQuestion = gameService.showQuestion(roomId);
        result.put("question",curQuestion);
        return result;
    }

    /*玩家选择答案回答问题
    * 返回答案是否正确
    * isCorrect:答案是否正确 1：正确 0：错误*/
    @RequestMapping(value = "/answerQuestion",method = RequestMethod.POST)
    public Map answerQuestion(@RequestParam("roomId")int roomId,@RequestParam("answer")String answer){
        Map<String,Object> result = new HashMap<>();
        //User user = userService.getCurrentUser(getCurrentUserID());
        int isCorrect = gameService.answerQuestions(roomId , answer);
        result.put("isCorrect",isCorrect);
        return result;
    }


}
