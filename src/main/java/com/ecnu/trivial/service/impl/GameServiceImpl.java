package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.mapper.QuestionsMapper;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.model.UserGameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.service.UserGameHistoryService;
import com.ecnu.trivial.vo.UserGameHistoryVo;
import com.ecnu.trivial.vo.UserVo;
import com.ecnu.trivial.webSocket.WsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl extends BaseServiceImpl implements GameService {
    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private UserGameHistoryService userGameHistoryService;


    /*玩家选择一个房间加入，建立webSocket连接
    * 如果该房间还没有玩家，则将该房间加入webSocket的roomMap,并将该玩家设置成房主，房主自动准备
    * 如果该房间有玩家
    * 判断如果该房间未在游戏中，并且人数没满，则进入房间成功
    * 否则进入房间失败
    * webSocket服务端向客户端发送游戏进程以更新页面
    * */
    public boolean enterRoom(int userId, int roomId){
        Game room = WsHandler.getRoom(roomId);
        User user = getCurrentUser(userId);
        UserVo userVo = parse(user);
        boolean result = false;
        if(room==null){
            Game game = new Game(roomId);
            WsHandler.addRoom(game);
            room = WsHandler.getRoom(roomId);
        }
        if (room!=null&&!room.isFullPlayer() && !room.isGameStart()){
            try {
                room.addNewPlayer(userVo);
                if(room.getPlayers().size()==1)
                    ready(userId,roomId);
            } catch (EncodeException e) {
                e.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    /*玩家选择离开房间
    * 如果游戏还未开始，则退出房间成功，webSocket连接关闭
    * 若游戏已经开始则不能退出（或者设置惩罚之类的）
    * webSocket服务端向客户端发送游戏进程以更新页面
    * return result: 0 退出成功，-1该房间不存在，1游戏正在进行中
    * */
    public synchronized int leaveRoom(int userId, int roomId){
        Game room = WsHandler.getRoom(roomId);
        User user = getCurrentUser(userId);
        UserVo userVo = parse(user);
        int result = 0;
        if(room==null){
            result = -1;
            System.out.println("Room doesn't exist.");
        }
        else if(!room.isGameStart()){
            try {
                room.quit(userVo);
            } catch (EncodeException e) {
                e.printStackTrace();
            }
            result = 0;
        }
        else
            result = 1;
        return result;
    }

    /*
    * 玩家准备，准许游戏开始
    * */
    @Override
    public void ready(int userId,int roomId) {
        Game room = WsHandler.getRoom(roomId);
        if(room!=null)
            try {
                room.setReady(userId);
            } catch (EncodeException e) {
                e.printStackTrace();
            }
    }

    /*
    * 如果该房间玩家人数大于等于2且全部准备
    * 则房主可以选择开始游戏
    * 则游戏初始化题目然后游戏开始
    * 初始化的题目从数据库中按type随机选取50道题目
    * */
    @Override
    public void start(int roomId){
        Game room = WsHandler.getRoom(roomId);
        if(room!=null) {
            if (room.getPlayers().size() >= 2 && room.isAllPlayerReady()) {
                List<Questions> popQuestions = questionsMapper.selectQuestionsByType(0);
                List<Questions> scienceQuestions = questionsMapper.selectQuestionsByType(1);
                List<Questions> sportsQuestions = questionsMapper.selectQuestionsByType(2);
                List<Questions> rockQuestions = questionsMapper.selectQuestionsByType(3);
                room.initialQuestions(popQuestions, scienceQuestions, sportsQuestions, rockQuestions);
                try {
                    room.startGame();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*当前轮到的玩家可以抛骰子
    * 根据骰子点数进行相应的行动
    * 这里也可以是前端js写随机函数，然后将结果作为参数传过来
    * */
    @Override
    public void dice(int tableId) {
        Game room = WsHandler.getRoom(tableId);
        int rollNumber = room.dice();//抛的骰子点数
        room.roll(rollNumber);
    }

    @Override
    public Questions showQuestion(int roomId){
        Game room = WsHandler.getRoom(roomId);
        return room.showQuestion();
    }

    /*当前轮到的玩家点击回答问题
    * 判断玩家的回答是否正确  1:正确 0：错误
    * webSocket服务器给客户端发送游戏进程信息以更新前端显示
    * */
    @Override
    public int answerQuestions(int roomId, String answer) {
        Game room = WsHandler.getRoom(roomId);
        int isCorrect = room.answerQuestion(answer);
        if(isCorrect==1)
            room.answeredCorrect();
        else
            room.answeredWrong();
        WsHandler.sendMessageToUser(room.getCurrentPlayerId(),room.getGameProcess().toString());
        return isCorrect;
    }

    private UserVo parse(User user) {
        UserVo result = new UserVo();
        BeanUtils.copyProperties(user, result);
        List<UserGameHistory> userGameHistories = userGameHistoryService.getUserGameHistoryByUserId(user.getUserId());
        double win = 0;
        double winRate = 0;
        for(UserGameHistory userGameHistory:userGameHistories){
            if(userGameHistory.getScore()==6)
                win++;
        }
        if(userGameHistories.size()!=0)
            winRate = win/userGameHistories.size()*100;
        winRate = (double)Math.round(winRate*100)/100;
        result.setWinRate(winRate);
        return result;
    }


}
