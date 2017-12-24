package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.mapper.QuestionsMapper;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.webSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private QuestionsMapper questionsMapper;

    /*玩家选择一个房间加入，建立webSocket连接
    * 如果该房间还没有玩家，则将该房间加入webSocket的roomMap
    * 如果该房间有玩家
    * 判断如果该房间未在游戏中，并且人数没满，则进入房间成功
    * 否则进入房间失败
    * webSocket服务端向客户端发送游戏进程以更新页面
    * */
    public boolean enterRoom(User user, int roomId){
        Game room = WebSocketServer.getRoom(roomId);
        boolean result = false;
        if(room==null){
            Game game = new Game(roomId);
            WebSocketServer.addRoom(game);
        }
        if (!room.isFullPlayer() && !room.isGameStart()){
            room.addNewPlayer(user);
            result = true;
        }
        //WebSocketServer.sendMessageToUser(user.getUserId(),room.getGameProcess().toString());
        return result;
    }

    /*玩家准备，准许游戏开始
    * 如果该房间玩家人数大于等于2且全部准备
    * 则游戏初始化题目然后游戏开始
    * 初始化的题目从数据库中按type随机选取50道题目
    * */
    @Override
    public void ready(User user,int roomId) {
        Game room = WebSocketServer.getRoom(roomId);
        room.setReady(user.getUserId());
        if (room.getPlayers().size()>=2 && room.isAllPlayerReady()){
            List<Questions> popQuestions = questionsMapper.selectQuestionsByType(0);
            List<Questions> scienceQuestions = questionsMapper.selectQuestionsByType(1);
            List<Questions> sportsQuestions = questionsMapper.selectQuestionsByType(2);
            List<Questions> rockQuestions = questionsMapper.selectQuestionsByType(3);
            room.initialQuestions(popQuestions,scienceQuestions,sportsQuestions,rockQuestions);
            room.startGame();
        }
    }

    /*当前轮到的玩家可以抛骰子
    * 根据骰子点数进行相应的行动
    * 这里也可以是前端js写随机函数，然后将结果作为参数传过来
    * */
    @Override
    public void dice(int tableId) {
        Game room = WebSocketServer.getRoom(tableId);
        int rollNumber = room.dice();//抛的骰子点数
        room.roll(rollNumber);
    }

    @Override
    public Questions showQuestion(int roomId){
        Game room = WebSocketServer.getRoom(roomId);
        return room.showQuestion();
    }

    /*当前轮到的玩家点击回答问题
    * 判断玩家的回答是否正确  1:正确 0：错误
    * webSocket服务器给客户端发送游戏进程信息以更新前端显示
    * */
    @Override
    public int answerQuestions(int roomId, String answer) {
        Game room = WebSocketServer.getRoom(roomId);
        int isCorrect = room.answerQuestion(answer);
        if(isCorrect==1)
            room.answeredCorrect();
        else
            room.answeredWrong();
        WebSocketServer.sendMessageToUser(room.getCurrentPlayerId(),room.getGameProcess().toString());
        return isCorrect;
    }


}
