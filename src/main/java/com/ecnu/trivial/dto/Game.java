package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.QuestionVo;
import com.ecnu.trivial.vo.UserVo;
import com.ecnu.trivial.webSocket.WsHandler;
import lombok.NoArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Game {
    public static final int NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER = 6;//获胜条件，金币达到6
    public static final int MAX_NUMBER_OF_BYTES_WRITING_TO_ONE_FILE = 10000000;
    public static final int NUMBER_OF_FILES_TO_USE = 1;
    public static final int NUMBER_OF_FULL_PLAYERS = 4;//游戏中最大玩家数
    private final QuestionMaker questionMaker = new QuestionMaker();//初始化游戏题库
    private List<Player> players = new ArrayList<>();
    private int currentPlayerId = 0;
    private static Logger logger = Logger.getLogger("kata.trivia.Game");
    private static FileHandler fileHandler = null;

    private int status = 0;//status 1：游戏正在进行中
    private int roomId;
    private GameProcess gameProcess = null;
    private WsHandler gameSocket = null;
    private String actionType;
    private int rollNumber;
    private boolean isRight;

    private int gameId;
    private Date startTime;
    private Date endTime;

    public Game(int roomId) {
        this.roomId = roomId;
        this.actionType = "room";
        this.startTime = new Date();
        gameSocket = new WsHandler();
        gameProcess = new GameProcess(this);
        //logToAFile();
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
    public void setReady(Integer userId) throws EncodeException {
        for(Player player:players){
            if (player.getUser().getUserId().equals(userId)) {
                player.setReady(true);
                logger.info(player.getUser().getName() + " was ready");
                sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
                return;
            }
        }
        logger.info(userId + " was not found");
    }

    /*是否全部玩家都准备*/
    public boolean isAllPlayerReady(){
        int count = 0;
        for(Player player:players){
            if (player.isReady())
                count++;
        }
        if(count==players.size()){
            logger.info("All players are ready!");
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
//    public void initialQuestions(List<Questions> popQuestions,List<Questions> scienceQuestions,List<Questions> sportsQuestions,List<Questions> rockQuestions){
//        questionMaker.setPopQuestionList(popQuestions);
//        questionMaker.setScienceQuestionList(scienceQuestions);
//        questionMaker.setSportsQuestionList(sportsQuestions);
//        questionMaker.setRockQuestionList(rockQuestions);
//    }
    public void initialQuestions(List<Questions> totalQuestions){
        questionMaker.setTotalQuestionList(totalQuestions);
    }

    /*
    * 有新玩家加入
    * */
    public void addNewPlayer(UserVo user) throws EncodeException {
        Player player = new Player(user.getName(),user);
        players.add(player);
        currentPlayerId++;
        gameProcess.setPlayers(players);
        logger.info(user.getName() + " was added");
        logger.info("The amount of players in Room "+roomId+ " is " + players.size());
        if(players.size()>1)
            sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));

    }

    /*
    * 有玩家离开房间
    * */
    public synchronized void quit(UserVo user) throws EncodeException {
        List<Player> delList = new ArrayList<>();
        for(Player player : players){
            if(player.getUser().equals(user)) {
                delList.add(player);
            }
        }
        players.removeAll(delList);
        gameProcess.setPlayers(players);
        currentPlayerId--;
        logger.info(user.getName() + " quit");
        logger.info("The amount of players in Room "+roomId+ " is " + players.size());
        if(players.size()>=1)
            sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        else
            gameSocket.removeRoom(this);

    }

    public void prepareToGame() throws EncodeException {
        this.actionType = "room to game";
        this.status = 1;
        this.currentPlayerId = 0;

        /*修改gameProcess属性*/
        gameProcess.setCurrentPlayerId(0);
        gameProcess.setStatus(status);
        gameProcess.setActionType(actionType);

        System.out.println(JSONObject.fromObject(gameProcess));
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        //gameProcess.setFirstRound(false);
    }

    /*
    * 游戏开始
    * 设置游戏状态为1
    * 当前玩家编号为0
    * 预先设置玩家抛的骰子点数以及要回答的问题
    * 并且同步设置gameProcess相应属性
    * 向所有玩家发送信息actionType:"startGame"
    * */
    public void startGame(Date startTime) throws EncodeException {
        this.actionType = "startGame";
        this.status = 1;
        this.currentPlayerId = 0;
        this.startTime = startTime;

        /*修改gameProcess属性*/
        gameProcess.setCurrentPlayerId(0);
        gameProcess.setStatus(status);
        gameProcess.setActionType(actionType);

        this.rollNumber = dice();
        gameProcess.setRollNumber(rollNumber);

        prepareNextQuestion();

        gameProcess.setPlayers(players);
        System.out.println(JSONObject.fromObject(gameProcess));
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        //gameProcess.setFirstRound(false);
    }

    /*移动当前玩家的位置，
    * currentPlayerId修改，
    * 预先计算下一个玩家的骰子点数以及要回答的问题
    * */
    public void prepareNextDiceAndNextQuestion() throws EncodeException {
        nextPlayer();
        this.rollNumber = dice();
        this.actionType = "startGame";
        gameProcess.setRollNumber(rollNumber);
        gameProcess.setPlayers(players);

        prepareNextQuestion();

        gameProcess.setActionType(actionType);
        System.out.println(JSONObject.fromObject(gameProcess));
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
    }

    public void prepareNextQuestion(){
        Questions curQuestion = questionMaker.getFirstQuestion();
        QuestionVo curQues;
        curQues = parse(curQuestion);
        gameProcess.setCurrentQuestion(curQues);
    }

    /*
    * 当前玩家抛骰子
    */
    public int dice() {
        Random rand = new Random();
        return (rand.nextInt(5)+1);
    }

    public void getOutOfPenaltyBox(){
        players.get(currentPlayerId).getOutOfPenaltyBox();
    }

    public void sentIntoPenaltyBox(){
        players.get(currentPlayerId).sentToPenaltyBox();
    }

    public void roll() throws EncodeException {
        logger.info(players.get(currentPlayerId) + " is the current player");
        logger.info("They have rolled a " + this.rollNumber);

        //如果玩家不在禁闭室内，则发送问题玩家位置以及骰子点数等信息给房间内所有玩家
        //(这里的位置是抛骰子之后位置，要根据骰子点数以及是否在禁闭室内判断)
        if (!players.get(currentPlayerId).isInPenaltyBox()) {
            this.actionType = "sendQuestion";
            gameProcess.setActionType(actionType);
            currentPlayerMovesToNewPlace();
            System.out.println(JSONObject.fromObject(gameProcess).toString());
            sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
            return;
        }

        //如果玩家在禁闭室内，判断骰子点数是否能让玩家出禁闭室
        //如果能出，则actionType:goOutPrison 修改玩家isInPenaltyBox属性,将玩家随机放置在地图某一点
        //如果不能，则actionType:stayPrison 修改玩家isInPenaltyBox属性,将玩家放置在地图监狱点
        //发送玩家当前位置和骰子点数给该房间全部玩家
        boolean isRollingNumberForGettingOutOfPenaltyBox = (this.rollNumber%2==1);
        if (isRollingNumberForGettingOutOfPenaltyBox) {
            getOutOfPenaltyBox();
            logger.info(players.get(currentPlayerId) + " is getting out of the penalty box");
            this.actionType = "goOutPrison";
            gameProcess.setActionType(actionType);
            sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        }
        else {
            sentIntoPenaltyBox();
            logger.info(players.get(currentPlayerId) + " is not getting out of the penalty box");
            this.actionType = "stayPrison";
            gameProcess.setActionType(actionType);
            sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        }
    }

    /*
    * 当前玩家开始行动
    * 如果玩家不在禁闭室内，则玩家前进骰子点数的步数并且回答问题
    * 如果玩家在禁闭室内，判断抛的点数能否使玩家出禁闭室
    * 如果玩家能出禁闭室，则出禁闭室走相应步数并回答问题
    * 如果抛出的点数不能使他出狱，则不出禁闭室
    * */
//    public void roll(int rollingNumber) {
//        logger.info(players.get(currentPlayerId) + " is the current player");
//        logger.info("They have rolled a " + rollingNumber);
//
//        if (!players.get(currentPlayerId).isInPenaltyBox()) {
//            currentPlayerMovesToNewPlaceAndAnswersAQuestion();
//            return;
//        }
//
//        boolean isRollingNumberForGettingOutOfPenaltyBox = rollingNumber != 4;
//        if (isRollingNumberForGettingOutOfPenaltyBox) {
//            players.get(currentPlayerId).getOutOfPenaltyBox();
//            logger.info(players.get(currentPlayerId) + " is getting out of the penalty box");
//            currentPlayerMovesToNewPlaceAndAnswersAQuestion();
//            return;
//        }
//        logger.info(players.get(currentPlayerId) + " is not getting out of the penalty box");
//        players.get(currentPlayerId).sentToPenaltyBox();
//    }

    /*
    * 当前玩家前进相应步数
    */
    private void currentPlayerMovesToNewPlace() {
        players.get(currentPlayerId).moveForwardSteps(rollNumber);

        logger.info(players.get(currentPlayerId)
                + "'s new location is "
                + players.get(currentPlayerId).getPlace());
        //logger.info("The category is " + players.get(currentPlayerId).getCurrentCategory());
        //answerQuestion();
    }

    /*
    * 判断玩家回答是否正确
    * */
    public boolean answerQuestion(String answer,String right) throws EncodeException {
        return answer.equals(right);
    }


    /*
    * 如果当前玩家在禁闭室内，则轮到下一个玩家
    * 否则玩家获得一枚金币轮到下一个玩家
    * 返回游戏是否结束
    * */
    public boolean answeredCorrect() throws EncodeException {
//        if (players.get(currentPlayerId).isInPenaltyBox()) {
//            nextPlayer();
//            boolean theGameIsStillInProgress = true;
//            return theGameIsStillInProgress;
//        }
        return currentPlayerGetsAGoldCoinAndSelectNextPlayer();
    }

    /*
    * 玩家回答问题正确，获得一枚金币，判断游戏是否结束
    * 下一位玩家进行游戏
    * 返回游戏是否结束
    * */
    private boolean currentPlayerGetsAGoldCoinAndSelectNextPlayer() throws EncodeException {
        this.actionType = "checkAnswer";
        gameProcess.setActionType(actionType);
        this.isRight = true;
        gameProcess.setRight(isRight);
        logger.info("Answer was correct!!!!");
        players.get(currentPlayerId).winAGoldCoin();

        logger.info(players.get(currentPlayerId)
                + " now has "
                + players.get(currentPlayerId).getSumOfGoldCoins()
                + " Gold Coins.");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("actionType","checkAnswer");
//        jsonObject.put("result","您答对了！获得一枚金币！");
//        sendJSONMessageToUser(jsonObject);
        System.out.println(JSONObject.fromObject(gameProcess).toString());
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        boolean isGameStillInProgress = isGameStillInProgress();
        //nextPlayer();

        return isGameStillInProgress;
    }

    /*
    * 轮到下一个玩家行动
    * 如果玩家id到达最大，则下一个玩家id为0
    * 循环进行游戏
    * */
    private void nextPlayer() {
        currentPlayerId++;
        if (currentPlayerId == players.size())
            currentPlayerId = 0;
        gameProcess.setCurrentPlayerId(currentPlayerId);
    }

    /*
    * 玩家回答问题错误，玩家被送进禁闭室
    * 下一位玩家进行游戏
    * 游戏继续
    * */
    public boolean answeredWrong() throws EncodeException {
        logger.info("Question was incorrectly answered");
        logger.info(players.get(currentPlayerId) + " was sent to the penalty box");
        this.actionType = "checkAnswer";
        gameProcess.setActionType(actionType);
        this.isRight = false;
        gameProcess.setRight(isRight);
        sentIntoPenaltyBox();
        logger.info(players.get(currentPlayerId).getPlayerName() + "'s new location is "+players.get(currentPlayerId).getPlace());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("actionType","question");
//        jsonObject.put("result","您答错了！被送进了监狱！");
//        sendJSONMessageToUser(jsonObject);
        System.out.println(JSONObject.fromObject(gameProcess).toString());
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        //nextPlayer();
        return true;
    }

    /*
    * 是否有玩家达到获胜条件
    * 即游戏是否还能继续
    * */
    public boolean isGameStillInProgress() {
        return !(players.get(currentPlayerId).countGoldCoins() == NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER);
    }

    /*
    * 游戏结束
    * 结算每个玩家所得的金币数*/
    public void endGame() throws EncodeException {
        logger.info("游戏结束，结算各玩家所得的金币数");
        status = 0;
        gameProcess.setStatus(status);
        actionType = "gameOver";
        gameProcess.setActionType(actionType);
        Player winner = null;
        for (Player player : players) {
            logger.info(player.toString() + " : " + player.countGoldCoins() + "枚金币");
            if (player.countGoldCoins() == NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER) {
                winner = player;
            }
        }
        gameProcess.setWinner(winner);
        sendJSONMessageToAllUsers(JSONObject.fromObject(gameProcess));
        gameSocket.removeRoom(this);
    }

    /*
    * 给该房间内的所有玩家发送游戏进程以更新游戏进程(JSON格式)
    * */
    private int sendJSONMessageToAllUsers(JSONObject msg) throws EncodeException {
        int result = 0;
        for (Player player : players) {
            //gameSocket.sendJSONMessageToUser(player.getUser().getUserId(), msg);
            if (!gameSocket.sendJSONMessageToUser(player.getUser().getUserId(), msg)) {
                result++;
            }
        }
        return result;
    }

    private int sendJSONMessageToUser(JSONObject msg) throws EncodeException {
        int result = 0;
        if (!gameSocket.sendJSONMessageToUser(players.get(currentPlayerId).getUser().getUserId(), msg)) {
            result++;
        }
        return result;
    }

    private QuestionVo parse(Questions questions) {
        QuestionVo result = new QuestionVo();
        BeanUtils.copyProperties(questions, result);
        List<String> optionList = new ArrayList<>();
        optionList.add(questions.getTrueAns());
        optionList.add(questions.getFalseAns1());
        optionList.add(questions.getFalseAns2());
        optionList.add(questions.getFalseAns3());
        Collections.shuffle(optionList);
        String[] array =new String[optionList.size()];
        result.setOptions(optionList.toArray(array));
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getRollNumber() {
        return rollNumber;
    }
}
