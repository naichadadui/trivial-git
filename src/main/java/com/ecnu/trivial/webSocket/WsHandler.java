package com.ecnu.trivial.webSocket;

import com.ecnu.trivial.configuration.WebSocketConfig;
import com.ecnu.trivial.controller.api.GameController;
import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.service.GameService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WsHandler extends TextWebSocketHandler {
    //日志记录
    private Logger logger = LoggerFactory.getLogger(WsHandler.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static Map<Integer, Game> rooms = new HashMap<Integer, Game>();

    //记录每个用户终端的连接
    private static Map<Integer, WebSocketSession> userSocket = new HashMap<Integer, WebSocketSession>();

    //需要session来对用户发送数据, 获取连接特征userId
    private WebSocketSession session;

    private int userId;
    private int roomId;
    private int receiveMessage = 0;

    @Autowired
    GameService gameService;

    @Autowired
    GameController gameController;

    /**
     * 当网络连接建立时调用该方法
     * */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        super.afterConnectionEstablished(session);
        logger.info("连接到服务器");
        this.session = session;
        String uri = session.getUri().getPath();
        String[] path = uri.split("/");
        this.roomId = Integer.parseInt(path[2]);
        this.userId = Integer.parseInt(path[3]);
        addOnlineCount();           //在线数加1
        if (userSocket.containsKey(this.userId)) {
            logger.info("当前用户Id:"+userId+"已在其他终端登录");
            this.afterConnectionClosed(session,CloseStatus.NORMAL);
        }
        else {
            userSocket.put(this.userId, session);
            logger.info("有新连接加入！当前在线人数为" + userSocket.size());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        super.afterConnectionClosed(session, status);
        if (userSocket.get(this.userId) != null) {
            gameService.leaveRoom(this.userId,this.roomId);
            userSocket.remove(this.userId);
            this.userId = 0;
            this.roomId = 0;
            subOnlineCount();
            logger.info("有一连接关闭！当前在线人数为" + userSocket.size());
        }
    }


    /**
     * 接受用户发送的消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        System.out.println("uri:"+session.getUri()+"    message:"+message.getPayload());
        String msg = message.getPayload();
        if (msg.equals("enter"))
            gameService.enterRoom(userId, roomId);
        if (msg.equals("ready"))
            gameService.ready(userId, roomId);
        if (msg.equals("start"))
            gameService.prepareToGame(roomId);
        if (msg.equals("gameReady")) {
            receiveMessage++;
            Game room = getRoom(roomId);
            if(room!=null)
                if (receiveMessage == room.getPlayers().size()) {
                    gameService.start(roomId);
                    receiveMessage = 0;
                }
        }
        if (msg.equals("clickDice"))
            gameService.roll(roomId);
        if (msg.contains("answer")) {
            String answer = msg.split(":")[1];
            gameService.answerQuestions(roomId, answer);
        }
        if (msg.equals("nextTurn")) {
            receiveMessage++;
            Game room = getRoom(roomId);
            if(room!=null)
                if (receiveMessage == room.getPlayers().size()) {
                    gameService.nextTurn(roomId);
                    receiveMessage = 0;
                }
        }
    }

    /**
     * 给特定用户发送消息
     */
    public static boolean sendMessageToUser(Integer userId, String message) {
        if (userSocket.containsKey(userId)) {
            WebSocketSession WS = userSocket.get(userId);
            TextMessage msg = new TextMessage(message);
            try {
                WS.sendMessage(msg);
                System.out.println(" 给用户Id为" + userId + "的所有终端发送消息："+ msg.getPayload());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("给用户" + userId + "发送消息失败");
                return false;
            }
            return true;
        }
        System.out.println("发送错误：当前连接不包含Id为："+userId+"的用户");
        return false;
    }

    /**
     * 给特定用户发送消息（JSON格式）
     */
    public static boolean sendJSONMessageToUser(Integer userId, JSONObject message) throws EncodeException {
        if (userSocket.containsKey(userId)) {
            WebSocketSession WS = userSocket.get(userId);
            TextMessage msg = new TextMessage(message.toString());
            try {
                WS.sendMessage(msg);
                System.out.println("给用户"+userId+"发送消息");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("给用户" + userId + "发送消息失败");
                return false;
            }
            return true;
        }
        System.out.println("发送错误：当前连接不包含Id为："+userId+"的用户");
        return false;
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
    {
        super.handleMessage(session, message);
    }

    /**
     * 当网络发生错误时调用该方法
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
    {
        super.handleTransportError(session, exception);
    }


    public static void addRoom(Game game){
        rooms.put(game.getRoomId(),game);
    }

    public static void removeRoom(Game game){
        rooms.remove(game.getRoomId(),game);
    }

    public static Game getRoom(Integer roomId){
        if(rooms.get(roomId)!=null)
            return rooms.get(roomId);
        else
            return null;
    }

    public static void setRoom(Game room){
        rooms.replace(room.getRoomId(),room);
    }

    public static Map<Integer, Game> getRooms() {
        return rooms;
    }

    public static void setRooms(Map<Integer, Game> rooms) {
        WsHandler.rooms = rooms;
    }

    public static Map<Integer, WebSocketSession> getUserSocket() {
        return userSocket;
    }

    public static void setUserSocket(Map<Integer, WebSocketSession> userSocket) {
        WsHandler.userSocket = userSocket;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static synchronized void addOnlineCount() {
        WsHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WsHandler.onlineCount--;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

}
