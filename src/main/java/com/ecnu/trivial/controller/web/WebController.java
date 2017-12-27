package com.ecnu.trivial.controller.web;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.dto.GameProcess;
import com.ecnu.trivial.dto.Player;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.vo.UserVo;
import com.ecnu.trivial.webSocket.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/trivial")
public class WebController extends BaseController {
    private static final String MODULE_INDEX = "index";
    private static final String MODULE_HOMEPAGE = "homepage";
    private static final String MODULE_WORK = "work";
    private static final String MODULE_ABOUT = "about";
    private static final String MODULE_BLOG = "blog";
    private static final String MODULE_RANKING = "ranking";
    private static final String MODULE_JAPANROOM = "JapanRoom";
    private static final String MODULE_JAPANGAME = "JapanGame";

    @Autowired
    private GameHistoryService gameHistoryService;

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model) {
        model.put("module", MODULE_INDEX);
        return MODULE_INDEX;
    }

    @RequestMapping(value = "/homepage")
    public String homepage(Map<String, Object> model) {
        List<GameHistory> latestGame = new ArrayList<>();
        latestGame = gameHistoryService.getLatestTwoGames();
        if (latestGame != null) {
            if (latestGame.size() >= 1) {
                model.put("endTime1", latestGame.get(0).getEndTime().toString());
                model.put("winner1", latestGame.get(0).getWinnerId().intValue());
            }
            if (latestGame.size() >= 2) {
                model.put("endTime2", latestGame.get(1).getEndTime().toString());
                model.put("winner2",latestGame.get(1).getWinnerId().intValue());
            }
        }
        model.put("module", MODULE_HOMEPAGE);
        return MODULE_HOMEPAGE;
    }

    @RequestMapping(value = "/work")
    public String work(Map<String, Object> model) {
        Map<Integer, Game> rooms = WebSocketServer.getRooms();
        int roomsNumber = rooms.size();

        List<Game> games = new ArrayList<>();
        for (int i = 0; i < roomsNumber; i++)
            games.add(i, rooms.get(i));
        List<Integer> numberOfPlayersInEachRoom = new ArrayList<>();
        for (int i = 0; i < roomsNumber; i++)
            numberOfPlayersInEachRoom.add(i, games.get(i).getPlayers().size());
        int onLinePlayerNumber = WebSocketServer.getOnlineCount();
        model.put("module", MODULE_WORK);
        model.put("numberOfPlayersInEachRoom", numberOfPlayersInEachRoom);
        model.put("onLinePlayerNumber", onLinePlayerNumber);
        return MODULE_WORK;
    }

    @RequestMapping(value = "/about")
    public String about(Map<String, Object> model) {
        model.put("module", MODULE_ABOUT);
        return MODULE_ABOUT;
    }

    @RequestMapping(value = "/blog")
    public String blog(Map<String, Object> model) {
        model.put("module", MODULE_BLOG);
        return MODULE_BLOG;
    }

    @RequestMapping(value = "/ranking")
    public String ranking(Map<String, Object> model) {
        model.put("module", MODULE_RANKING);
        return MODULE_RANKING;
    }

    /*
    * PathVariable:roomId
    * playerList:该局游戏当前玩家列表
    * */
    @RequestMapping(value = "/JapanRoom/{roomId}")
    public String japanRoom(Map<String, Object> model, @PathVariable Integer roomId) {
        Game room = WebSocketServer.getRoom(roomId);
        List<Player> playerList = new ArrayList<>();
        if (room != null)
            playerList = room.getPlayers();
        model.put("module", MODULE_JAPANROOM);
        model.put("playerList", playerList);
        return MODULE_JAPANROOM;
    }

    /*
    * PathVariable:roomId
    * initialProcess:游戏初始状态信息
    * 初始状态下只有roomId,players,currentPlayerId,status四个信息
    * */
    @RequestMapping(value = "/JapanGame/{roomId}")
    public String japanGame(Map<String, Object> model, @PathVariable Integer roomId) {
        Game room = WebSocketServer.getRoom(roomId);
        GameProcess initialProcess = new GameProcess(room);
        model.put("initialProcess", initialProcess);
        model.put("module", MODULE_JAPANGAME);
        return MODULE_JAPANGAME;
    }

    private UserVo parse(User user) {
        UserVo result = new UserVo();
        BeanUtils.copyProperties(user, result);
        return result;
    }
}
