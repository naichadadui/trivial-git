package com.ecnu.trivial.webSocket;

import com.ecnu.trivial.dto.Game;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/webSocket/{roomId}/{userId}")
@Component
public class WebSocketServer {
    //日志记录
    //private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static Map<Integer, Game> rooms = new HashMap<Integer, Game>();

    //记录每个用户终端的连接
    private static Map<Integer, WebSocketServer> userSocket = new HashMap<Integer, WebSocketServer>();

    //需要session来对用户发送数据, 获取连接特征userId
    private Session session;
    private Integer userId;
    private Integer roomId;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(/*@Param("tableId") Integer tableId,@Param("userId")Integer userId,Session session*/) {
        this.session = session;
        this.userId = userId;
        this.roomId = roomId;
        addOnlineCount();           //在线数加1
        if (userSocket.containsKey(this.userId)) {
            System.out.println("当前用户id:"+this.userId+"已有其他终端登录");
            this.onClose();
        } else {
            userSocket.put(this.userId, this);
        }
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
        Game table = rooms.get(this.roomId);
        //有一个人掉线了，游戏就得结束
//        if (table != null) {
//            if (table.isGameStart()) {
//                table.endGame();
//                logger.debug("桌号{}游戏强制结束", this.tableId);
//            } else {
//                table.remove(this.userId);
//                logger.debug("用户{}离开桌号{}", this.userId, this.tableId);
//                if (table.getPlayers().size() == 0) {
//                    removeTable(table.getTableId());
//                }
//            }
//
//        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        if (session == null)
            System.out.println("session null");
        System.out.println("来自客户端"+this.userId+"的消息:" + message);

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
     * 发生错误时调用
     * @OnError
     */
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }

    /**
     * sendMessageToUser
     * 发送消息给对应用户
     */
    public static boolean sendMessageToUser(Integer userId, String message) {
        if (userSocket.containsKey(userId)) {
            System.out.println(" 给用户id为" + userId + "的所有终端发送消息："+ message);
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
        System.out.println("发送错误：当前连接不包含id为："+userId+"的用户");
        return false;
    }

    public static void addRoom(Game game){
        rooms.put(game.getRoomId(),game);
    }

    public static void removeRoom(Game game){
        rooms.remove(game.getRoomId(),game);
    }

    public static Game getRoom(Integer roomId){
        return rooms.get(roomId);
    }

    public static Map<Integer, Game> getRooms() {
        return rooms;
    }

    public static void setRooms(Map<Integer, Game> rooms) {
        WebSocketServer.rooms = rooms;
    }

    public static Map<Integer, WebSocketServer> getUserSocket() {
        return userSocket;
    }

    public static void setUserSocket(Map<Integer, WebSocketServer> userSocket) {
        WebSocketServer.userSocket = userSocket;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
