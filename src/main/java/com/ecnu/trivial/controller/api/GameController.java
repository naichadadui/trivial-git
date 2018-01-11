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
}
