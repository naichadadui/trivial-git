package com.ecnu.trivial.webSocket;

import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.service.GameService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/webSocket/{roomId}/{userId}",configurator = SpringConfigurator.class)
@Component
public class WebSocketServer{
    //日志记录
    //private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static Map<Integer, Game> rooms = new HashMap<Integer, Game>();

    //记录每个用户终端的连接
    private static Map<String, WebSocketServer> userSocket = new HashMap<String, WebSocketServer>();

    //需要session来对用户发送数据, 获取连接特征userId
    private Session session;
    private String userId;
    private String roomId;

//    @Autowired
//    private GameService gameService;

    private GameService gameService;

    private static ApplicationContext applicationContext;

    @Autowired
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext  = context;
    }

    /**
     * 当网络连接建立时调用该方法
     * */
    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, @PathParam("userId")String userId, Session session) {
        System.out.println("连接到服务器");
        this.session = session;
        this.userId = userId;
        this.roomId = roomId;
        addOnlineCount();           //在线数加1
        if (userSocket.containsKey(this.userId)) {
            System.out.println("当前用户Id:"+this.userId+"已在其他终端登录");
            this.onClose();
        }
        else
            userSocket.put(this.userId, this);
        System.out.println("有新连接加入！当前在线人数为" + userSocket.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //移除当前用户终端登录的webSocket信息,如果该用户下线了，则删除该用户的记录
        if (userSocket.get(this.userId) != null) {
            userSocket.remove(this.userId);
            subOnlineCount();
            System.out.println("有一连接关闭！当前在线人数为" + userSocket.size());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (session == null)
            System.out.println("session null");
        System.out.println("来自客户端"+this.userId+"的消息:" + message);
        if(message.equals("enter")){
            //System.out.println(roomId);
            //if (gameService!=null)

            //applicationContext = ApplicationContextProvider.getApplicationContext();
            gameService = (GameService)applicationContext.getBean(GameService.class);
            //gameService=act.getBean(GameService.class);
            gameService.enterRoom(Integer.parseInt(userId),Integer.parseInt(roomId));
        }
//        if(message.equals("ready")){
//            gameController.ready(Integer.parseInt(roomId));
//        }
//        if(message.equals("dice")){
//            gameController.dice(Integer.parseInt(roomId));
//        }
//        if(message.contains("answer")){
//            gameController.answerQuestion(Integer.parseInt(roomId),message);
//        }
//        else{
//
//        }

        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 当网络发生错误时调用该方法
     */
     @OnError
     public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
     }

    /**
     * 给特定用户发送消息
     */
    public static boolean sendMessageToUser(Integer userId, String message) {
        if (userSocket.containsKey(userId)) {
            System.out.println(" 给用户Id为" + userId + "的所有终端发送消息："+ message);
            WebSocketServer WS = userSocket.get(userId);
            System.out.println("sessionId为:"+ WS.session.getId());
            try {
                WS.session.getBasicRemote().sendText(message);
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
            System.out.println(" 给用户Id为" + userId + "的所有终端发送消息："+ message);
            WebSocketServer WS = userSocket.get(userId);
            System.out.println("sessionId为:"+ WS.session.getId());
            try {
                WS.session.getBasicRemote().sendObject(message);
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

    public static Map<Integer, Game> getRooms() {
        return rooms;
    }

    public static void setRooms(Map<Integer, Game> rooms) {
        WebSocketServer.rooms = rooms;
    }

    public static Map<String, WebSocketServer> getUserSocket() {
        return userSocket;
    }

    public static void setUserSocket(Map<String, WebSocketServer> userSocket) {
        WebSocketServer.userSocket = userSocket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }
}
