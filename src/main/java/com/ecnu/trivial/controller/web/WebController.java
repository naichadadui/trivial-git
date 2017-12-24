package com.ecnu.trivial.controller.web;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.webSocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model) {
        model.put("module", MODULE_INDEX);
        return MODULE_INDEX;
    }

    @RequestMapping(value = "/homepage")
    public String homepage(Map<String, Object> model) {
        model.put("module", MODULE_HOMEPAGE);
        return MODULE_HOMEPAGE;
    }

    @RequestMapping(value = "/work")
    public String work(Map<String, Object> model) {
        Map<Integer,Game> rooms = WebSocketServer.getRooms();
        int roomsNumber = rooms.size();
        int onLinePlayerNumber = WebSocketServer.getOnlineCount();
        model.put("module", MODULE_WORK);
        model.put("rooms", WebSocketServer.getRooms());
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
