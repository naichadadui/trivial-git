package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/game")
public class GameController extends APIBaseController{
    @Autowired
    private GameService gameService;

    /*用户选择一个房间进入
    * enterResult：表示进入房间是否成功  1：成功 0：失败
    * returnMessage:对应的提示消息
    * */
    @RequestMapping(value="/enterRoom", method = RequestMethod.POST)
    public Map enterRoom(@RequestParam("roomId")String roomId){
        Map<String,Object> result = new HashMap<>();
        int enterResult = 0;
        String returnMessage = "success";
        HttpSession session = request.getSession();
        session.setAttribute("roomId", Integer.parseInt(roomId));
        result.put("enterResult",enterResult);
        result.put("returnMessage",returnMessage);
        return result;
    }

    /*用户退出房间
    * leaveResult：表示退出房间是否成功  0：成功 -1：失败 1：正在游戏中，不能退出
    * returnMessage:对应的提示消息
    * */
    @RequestMapping(value="/leaveRoom", method = RequestMethod.POST)
    public Map leaveRoom(@RequestParam("roomId")String roomId){
        Map<String,Object> result = new HashMap<>();
        int leaveResult = -1;
        String returnMessage = "";
        HttpSession session = request.getSession();
        session.removeAttribute("roomId");
        result.put("leaveResult",leaveResult);
        result.put("returnMessage",returnMessage);
        return result;
    }

//    public void leave() {
//        //HttpSession session = request.getSession();
//        //session.removeAttribute("roomId");
//        gameService.leaveRoom(getCurrentUserID(),getCurrentRoomID());
//        Game game = WsHandler.getRoom(getCurrentRoomID());
//        if(game!=null) {
//            if (game.getPlayers().size() == 0)
//                WsHandler.removeRoom(WsHandler.getRoom(getCurrentRoomID()));
//        }
//    }

    /*玩家点击ready，表示准许游戏开始
    * */
    @RequestMapping(value="/ready", method = RequestMethod.POST)
    public void ready(@RequestParam("roomId")int roomId){
        //Map<String,Object> result = new HashMap<>();
        gameService.ready(getCurrentUserID(), roomId);
    }

//    @RequestMapping(value = "/dice",method = RequestMethod.POST)
//    public void dice(@RequestParam("roomId")int roomId){
//        //User user = userService.getCurrentUser(getCurrentUserID());
//        gameService.dice(roomId);
//    }

    /*玩家点击闪烁的小马
    * 弹出框中显示问题内容
    * 这里还存在问题：随机显示选项顺序*/
//    @RequestMapping(value = "/showQuestion",method = RequestMethod.POST)
//    public Map showQuestion(@RequestParam("roomId")int roomId){
//        Map<String,Object> result = new HashMap<>();
//        //User user = userService.getCurrentUser(getCurrentUserID());
//        Questions curQuestion = gameService.showQuestion(roomId);
//        result.put("question",curQuestion);
//        return result;
//    }

    /*玩家选择答案回答问题
    * 返回答案是否正确
    * isCorrect:答案是否正确 1：正确 0：错误*/
    @RequestMapping(value = "/answerQuestion",method = RequestMethod.POST)
    public Map answerQuestion(@RequestParam("roomId")int roomId,@RequestParam("answer")String answer){
        Map<String,Object> result = new HashMap<>();
        //User user = userService.getCurrentUser(getCurrentUserID());
        int isCorrect = 0;
        try {
            isCorrect = gameService.answerQuestions(roomId , answer);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        result.put("isCorrect",isCorrect);
        return result;
    }


}
