package com.ecnu.trivial.controller.web;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.webSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private static final String MODULE_RANKING="ranking";
    private static final String MODULE_JAPANROOM="JapanRoom";
    private static final String MODULE_JAPANGAME="JapanGame";

    @Autowired
    private GameHistoryService gameHistoryService;

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model) {
        model.put("module", MODULE_INDEX);
        return MODULE_INDEX;
    }

    @RequestMapping(value = "/homepage")
    public String homepage(Map<String, Object> model) {
        List<GameHistory> latestGame = new ArrayList<>();
        latestGame = gameHistoryService.getLatestTwoGames();
        model.put("latestGame1",latestGame.get(0));
        model.put("latestGame2",latestGame.get(1));
        model.put("module", MODULE_HOMEPAGE);
        return MODULE_HOMEPAGE;
    }

    @RequestMapping(value = "/work")
    public String work(Map<String, Object> model) {
        Map<Integer,Game> rooms = WebSocketServer.getRooms();
        int roomsNumber = rooms.size();

        List<Game> games = new ArrayList<>();
        for(int i = 0;i<roomsNumber;i++)
            games.add(i,rooms.get(i));
        List<Integer> numberOfPlayersInEachRoom = new ArrayList<>();
        for(int i = 0;i<roomsNumber;i++)
            numberOfPlayersInEachRoom.add(i,games.get(i).getPlayers().size());
        int onLinePlayerNumber = WebSocketServer.getOnlineCount();
        model.put("module", MODULE_WORK);
        model.put("numberOfPlayersInEachRoom", numberOfPlayersInEachRoom);
        model.put("onLinePlayerNumber",onLinePlayerNumber);
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

    @RequestMapping(value="/ranking")
    public String ranking(Map<String,Object> model){
        model.put("module",MODULE_RANKING);
        return MODULE_RANKING;
    }

    @RequestMapping(value="/JapanRoom")
    public String japanRoom(Map<String,Object> model){
        model.put("module",MODULE_JAPANROOM);
        model.put("rooms", WebSocketServer.getRooms());
        return MODULE_JAPANROOM;
    }

    @RequestMapping(value="/JapanGame")
    public String japanGame(Map<String,Object> model){
        model.put("module",MODULE_JAPANGAME);
        return MODULE_JAPANGAME;
    }
}
