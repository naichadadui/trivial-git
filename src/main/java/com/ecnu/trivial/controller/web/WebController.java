package com.ecnu.trivial.controller.web;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.dto.GameProcess;
import com.ecnu.trivial.dto.Player;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.model.UserGameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.service.UserGameHistoryService;
import com.ecnu.trivial.service.UserService;
import com.ecnu.trivial.vo.GameHistoryVo;
import com.ecnu.trivial.vo.UserGameHistoryVo;
import com.ecnu.trivial.vo.UserVo;
import com.ecnu.trivial.webSocket.WsHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/trivial")
public class WebController extends BaseController {
    private static final String MODULE_HOMEPAGE = "homepage";
    private static final String MODULE_WORK = "work";
    private static final String MODULE_BLOG = "blog";
    private static final String MODULE_RANKING = "ranking";
    private static final String MODULE_JAPANROOM = "JapanRoom";
    private static final String MODULE_JAPANGAME = "JapanGame";
    private static final String MODULE_ADMINUSER = "adminUser";

    @Autowired
    private GameHistoryService gameHistoryService;

    @Autowired
    private UserGameHistoryService userGameHistoryService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/homepage")
    public String homepage(Map<String, Object> model) {
        List<GameHistory> latestGame = gameHistoryService.getLatestTwoGames();
        List<GameHistoryVo> latestGameVos = latestGame.stream().map(this::parse).collect(Collectors.toList());
        model.put("latestTwoGames",latestGameVos);
        if (latestGameVos != null) {
            if (latestGameVos.size() >= 1)
                model.put("latestGame",latestGameVos.get(0));
            if (latestGameVos.size() >= 2)
                model.put("latestGame2",latestGameVos.get(1));
        }
        model.put("module", MODULE_HOMEPAGE);
        return MODULE_HOMEPAGE;
    }

    @RequestMapping(value = "/work")
    public String work(Map<String, Object> model) {
        Map<Integer, Game> rooms = WsHandler.getRooms();
        List<Game> games = new ArrayList<>();
        games.addAll(rooms.values());
        List<GameProcess> gameProcesses = new ArrayList<>();
        for (int i = 0; i < games.size(); i++) {
            GameProcess gameProcess = games.get(i).getGameProcess();
            gameProcesses.add(gameProcess);
        }
        JSONArray jsonArray = JSONArray.fromObject(gameProcesses);
        int onLinePlayerNumber = WsHandler.getOnlineCount();
        model.put("module", MODULE_WORK);
        model.put("rooms",jsonArray.toString());
        model.put("numberOfPlayersInEachRoom", 1);
        model.put("onLinePlayerNumber", onLinePlayerNumber);
        return MODULE_WORK;
    }


    @RequestMapping(value = "/blog")
    public String blog(Map<String, Object> model) {
        int userId = getCurrentUserID();
        List<UserGameHistory> userGameHistories = userGameHistoryService.getUserGameHistoryByUserId(userId);
        List<UserGameHistoryVo> gameHistoryVos = userGameHistories.stream().map(this::parse).collect(Collectors.toList());
        User user = userGameHistoryService.getCurrentUser(getCurrentUserID());
        UserVo userVo = parse(user);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int i = 1;
        for(UserGameHistoryVo userGameHistoryVo:gameHistoryVos){
            jsonObject.put("id",i);
            jsonObject.put("start",userGameHistoryVo.getGameHistoryVo().getStartTimeStr());
            jsonObject.put("end",userGameHistoryVo.getGameHistoryVo().getEndTimeStr());
            jsonObject.put("score",userGameHistoryVo.getScore());
            jsonArray.add(jsonObject);
            i++;
        }
        model.put("user",userVo);
        model.put("userGameHistory",jsonArray.toString());
        model.put("module", MODULE_BLOG);
        return MODULE_BLOG;
    }

    @RequestMapping(value = "/ranking")
    public String ranking(Map<String, Object> model) {
        List<User> userRankingList = userService.getAllUsersOrderByScore();
        List<UserVo> userRankingVos = userRankingList.stream().map(this::parse).collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int i = 1;
        for(UserVo user:userRankingVos){
            jsonObject.put("id",i);
            jsonObject.put("name",user.getName());
            jsonObject.put("score",user.getScore());
            jsonObject.put("winRate",user.getWinRate());
            jsonArray.add(jsonObject);
            i++;
        }
        model.put("rankingData",jsonArray.toString());
        model.put("numberOne",userRankingVos.get(0));
        model.put("numberTwo",userRankingVos.get(1));
        model.put("numberThree",userRankingVos.get(2));
        model.put("module", MODULE_RANKING);
        return MODULE_RANKING;
    }



    /*
    * PathVariable:roomId
    * playerList:该局游戏当前玩家列表
    * */
    @RequestMapping(value = "/JapanRoom/{roomId}")
    public String japanRoom(Map<String, Object> model, @PathVariable Integer roomId) {
        Game room = WsHandler.getRoom(roomId);
        List<Player> playerList = new ArrayList<>();
        if (room != null)
            playerList = room.getPlayers();
        model.put("module", MODULE_JAPANROOM);
        model.put("playerList", playerList);
        model.put("roomId",roomId);
        return MODULE_JAPANROOM;
    }

    /*
    * PathVariable:roomId
    * initialProcess:游戏初始状态信息
    * 初始状态下只有roomId,players,currentPlayerId,status四个信息
    * */
    @RequestMapping(value = "/JapanGame/{roomId}")
    public String japanGame(Map<String, Object> model, @PathVariable Integer roomId) {
        Game room = WsHandler.getRoom(roomId);
        GameProcess initialProcess = new GameProcess(room);
        model.put("initialProcess", initialProcess);
        model.put("module", MODULE_JAPANGAME);
        return MODULE_JAPANGAME;
    }

    @RequestMapping(value = "/adminUser")
    public String adminUser(Map<String, Object> model) {
        model.put("module", MODULE_ADMINUSER);
        return MODULE_ADMINUSER;
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

    private GameHistoryVo parse(GameHistory gameHistory){
        GameHistoryVo result = new GameHistoryVo();
        BeanUtils.copyProperties(gameHistory, result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setStartTimeStr(sdf.format(gameHistory.getStartTime()));
        result.setEndTimeStr(sdf.format(gameHistory.getEndTime()));
        if(gameHistory.getWinnerId()!=null) {
            User winner = gameHistoryService.getCurrentUser(gameHistory.getWinnerId());
            result.setWinnerName(winner.getName());
        }
        else
            result.setWinnerName("");
        return result;
    }

    private UserGameHistoryVo parse(UserGameHistory userGameHistory){
        UserGameHistoryVo result = new UserGameHistoryVo();
        BeanUtils.copyProperties(userGameHistory, result);
        GameHistory gameHistory = gameHistoryService.selectByPrimaryKey(userGameHistory.getGameId());
        GameHistoryVo gameHistoryVo = parse(gameHistory);
        result.setGameHistoryVo(gameHistoryVo);
        return result;
    }
}
