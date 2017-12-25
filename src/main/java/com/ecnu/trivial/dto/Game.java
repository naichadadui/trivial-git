package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.webSocket.WebSocketServer;
import lombok.NoArgsConstructor;
import net.sf.json.JSONObject;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@NoArgsConstructor
public class Game {
    public static final int NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER = 6;//获胜条件，金币达到6
    public static final int MAX_NUMBER_OF_BYTES_WRITING_TO_ONE_FILE = 10000000;
    public static final int NUMBER_OF_FILES_TO_USE = 1;
    public static final int NUMBER_OF_FULL_PLAYERS = 4;//游戏中最大玩家数
    private final QuestionMaker questionMaker = new QuestionMaker();//初始化游戏题库
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayerId = 0;
    private static Logger logger = Logger.getLogger("kata.trivia.Game");
    private static FileHandler fileHandler = null;

    private int status = 0;//status 1：游戏正在进行中
    private int roomId;
    private GameProcess gameProcess = null;
    private WebSocketServer gameSocket = null;

    public Game(int roomId) {
        this.roomId = roomId;
        gameSocket = new WebSocketServer();
        gameSocket.addRoom(this);
        gameProcess = new GameProcess(this);
        logToAFile();
    }

    private void logToAFile() {
        try {
            fileHandler = new FileHandler("%h/Game-logging.log"
                    , MAX_NUMBER_OF_BYTES_WRITING_TO_ONE_FILE
                    , NUMBER_OF_FILES_TO_USE, true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
    }

    /*玩家准备*/
    public void setReady(Integer userId){
        for(Player player:players){
            if (player.getUser().getUserId().equals(userId)) {
                player.setReady(true);
                System.out.println(player.getUser().getName() + " was ready");
                sendMessageToAllUsers(gameProcess.toString());
                return;
            }
        }
        System.out.println(userId + " was not found");
    }

    /*是否全部玩家都准备*/
    public boolean isAllPlayerReady(){
        int count = 0;
        for(Player player:players){
            if (player.isReady())
                count++;
        }
        if(count==players.size()){
            System.out.println("All players are ready!");
            return true;
        }
        return false;
    }

    /*该房间是否已满员*/
    public boolean isFullPlayer(){
        return currentPlayerId == NUMBER_OF_FULL_PLAYERS;
    }

    /*游戏是否已经开始*/
    public boolean isGameStart(){
        return status==1;
    }

    /*在游戏开始前初始化问题列表
    * 每类题目50题
    * */
    public void initialQuestions(List<Questions> popQuestions,List<Questions> scienceQuestions,List<Questions> sportsQuestions,List<Questions> rockQuestions){
        questionMaker.setPopQuestionList(popQuestions);
        questionMaker.setScienceQuestionList(scienceQuestions);
        questionMaker.setSportsQuestionList(sportsQuestions);
        questionMaker.setRockQuestionList(rockQuestions);
    }

    /*
    * 有新玩家加入
    * */
    public void addNewPlayer(User user) throws EncodeException {
        Player player = new Player(user.getName(),user);
        players.add(player);
        gameProcess.setPlayers(players);
        logger.info(user.getName() + " was added");
        logger.info("The total amount of players is " + players.size());
        //sendMessageToAllUsers(gameProcess.toString());
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));

    }

    /*
    * 游戏开始
    * 设置游戏状态为1
    * 当前玩家编号为0
    * 并且同步设置gameProcess相应属性
    * */
    public void startGame() throws EncodeException {
        this.status = 1;
        this.currentPlayerId = 0;
        gameProcess.setCurrentPlayerId(players.get(currentPlayerId).getUser().getUserId());
        gameProcess.setStatus(status);
        //sendMessageToAllUsers(gameProcess.toString());
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        //gameProcess.setFirstRound(false);
    }

    /*
    * 当前玩家抛骰子
    */
    public int dice() {
        Random rand = new Random();
        return (rand.nextInt(5)+1);
    }

    /*
    * 当前玩家开始行动
    * 如果玩家不在禁闭室内，则玩家前进骰子点数的步数并且回答问题
    * 如果玩家在禁闭室内，判断抛的点数能否使玩家出禁闭室
    * 如果玩家能出禁闭室，则出禁闭室走相应步数并回答问题
    * 如果抛出的点数不能使他出狱，则不出禁闭室
    * */
    public void roll(int rollingNumber) {
        logger.info(players.get(currentPlayerId) + " is the current player");
        logger.info("They have rolled a " + rollingNumber);

        if (!players.get(currentPlayerId).isInPenaltyBox()) {
            currentPlayerMovesToNewPlaceAndAnswersAQuestion(rollingNumber);
            return;
        }

        boolean isRollingNumberForGettingOutOfPenaltyBox = rollingNumber != 4;
        if (isRollingNumberForGettingOutOfPenaltyBox) {
            players.get(currentPlayerId).getOutOfPenaltyBox();
            logger.info(players.get(currentPlayerId) + " is getting out of the penalty box");
            currentPlayerMovesToNewPlaceAndAnswersAQuestion(rollingNumber);
            return;
        }
        logger.info(players.get(currentPlayerId) + " is not getting out of the penalty box");
        players.get(currentPlayerId).sentToPenaltyBox();
    }

    /*
    * 当前玩家前进相应步数
    * （并且回答问题）先不回答问题
    */
    private void currentPlayerMovesToNewPlaceAndAnswersAQuestion(int rollingNumber) {
        players.get(currentPlayerId).moveForwardSteps(rollingNumber);

        logger.info(players.get(currentPlayerId)
                + "'s new location is "
                + players.get(currentPlayerId).getPlace());
        logger.info("The category is " + players.get(currentPlayerId).getCurrentCategory());
        //answerQuestion();
    }

    /*
    * 当前玩家回答问题，并将题目从题目列表中移除
    * 修改：返回回答正确与否,添加参数answer,修改可见性为public
    * */
    public int answerQuestion(String answer) {
        Questions questions = new Questions();
        if (players.get(currentPlayerId).getCurrentCategory() == "Pop")
            questions = questionMaker.getPopQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Science")
            questions = questionMaker.getScienceQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Sports")
            questions = questionMaker.getSportsQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Rock")
            questions = questionMaker.getRockQuestionList().get(0);
        if(questions.getTrueAns().equals(answer))
            return 1;
        else
            return 0;
    }
//    private void answerQuestion() {
//        if (players.get(currentPlayerId).getCurrentCategory() == "Pop")
//            logger.info(questionMaker.removeFirstPopQuestion());
//        if (players.get(currentPlayerId).getCurrentCategory() == "Science")
//            logger.info(questionMaker.removeFirstScienceQuestion());
//        if (players.get(currentPlayerId).getCurrentCategory() == "Sports")
//            logger.info(questionMaker.removeFirstSportsQuestion());
//        if (players.get(currentPlayerId).getCurrentCategory() == "Rock")
//            logger.info(questionMaker.removeFirstRockQuestion());
//    }

    /*
    * 返回当前的问题
    * */
    public Questions showQuestion() {
        Questions curQuestion = new Questions();
        if (players.get(currentPlayerId).getCurrentCategory() == "Pop")
            curQuestion = questionMaker.getPopQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Science")
            curQuestion = questionMaker.getScienceQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Sports")
            curQuestion = questionMaker.getSportsQuestionList().get(0);
        if (players.get(currentPlayerId).getCurrentCategory() == "Rock")
            curQuestion = questionMaker.getRockQuestionList().get(0);
        return curQuestion;
    }

    /*
    * 如果当前玩家在禁闭室内，则轮到下一个玩家
    * 否则玩家获得一枚金币轮到下一个玩家
    * 返回游戏是否结束
    * */
    public boolean answeredCorrect() {
        if (players.get(currentPlayerId).isInPenaltyBox()) {
            nextPlayer();
            boolean theGameIsStillInProgress = true;
            return theGameIsStillInProgress;
        }
        return currentPlayerGetsAGoldCoinAndSelectNextPlayer();
    }

    /*
    * 玩家回答问题正确，获得一枚金币，判断游戏是否结束
    * 下一位玩家进行游戏
    * 返回游戏是否结束
    * */
    private boolean currentPlayerGetsAGoldCoinAndSelectNextPlayer() {
        logger.info("Answer was correct!!!!");
        players.get(currentPlayerId).winAGoldCoin();

        logger.info(players.get(currentPlayerId)
                + " now has "
                + players.get(currentPlayerId).countGoldCoins()
                + " Gold Coins.");

        boolean isGameStillInProgress = isGameStillInProgress();
        nextPlayer();

        return isGameStillInProgress;
    }

    /*
    * 轮到下一个玩家行动
    * 如果玩家id到达最大，则下一个玩家id为0
    * 循环进行游戏
    * */
    private void nextPlayer() {
        currentPlayerId++;
        if (currentPlayerId == players.size()) currentPlayerId = 0;
    }

    /*
    * 玩家回答问题错误，玩家被送进禁闭室
    * 下一位玩家进行游戏
    * 游戏继续
    * */
    public boolean answeredWrong() {
        logger.info("Question was incorrectly answered");
        logger.info(players.get(currentPlayerId) + " was sent to the penalty box");
        players.get(currentPlayerId).sentToPenaltyBox();

        nextPlayer();
        boolean theGameIsStillInProgress = true;
        return theGameIsStillInProgress;
    }

    /*
    * 是否有玩家达到获胜条件
    * 即游戏是否还能继续
    * */
    private boolean isGameStillInProgress() {
        return !(players.get(currentPlayerId).countGoldCoins() == NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER);
    }

    /*
    * 游戏结束
    * 结算每个玩家所得的金币数*/
    public void endGame() {
        logger.info("游戏结束，结算各玩家所得的金币数");
        status = 0;
        gameProcess.setStatus(status);
        Player winner = null;
        for (Player player : players) {
            logger.info(player.toString() + " : " + player.countGoldCoins() + "枚金币");
            if (player.countGoldCoins() == 6) {
                winner = player;
            }
        }
        gameProcess.setWinner(winner);
        sendMessageToAllUsers(gameProcess.toString());
        gameSocket.removeRoom(this);
    }

    /*
    * 给该房间内的所有玩家发送游戏进程以更新游戏进程(String)
    * */
    private int sendMessageToAllUsers(String msg) {
        int result = 0;
        for (Player player : players) {
            if (!gameSocket.sendMessageToUser(player.getUser().getUserId(), msg)) {
                result++;
            }
        }
        return result;
    }

    /*
    * 给该房间内的所有玩家发送游戏进程以更新游戏进程(JSON格式)
    * */
    private int sendJSONMessageToAllUsers(JSONObject msg) throws EncodeException {
        int result = 0;
        for (Player player : players) {
            if (!gameSocket.sendJSONMessageToUser(player.getUser().getUserId(), msg)) {
                result++;
            }
        }
        return result;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(int currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GameProcess getGameProcess() {
        return gameProcess;
    }

    public void setGameProcess(GameProcess gameProcess) {
        this.gameProcess = gameProcess;
    }
}
