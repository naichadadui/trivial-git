package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.model.UserGameHistory;
import com.ecnu.trivial.vo.UserVo;
import org.springframework.beans.BeanUtils;

import javax.websocket.EncodeException;
import java.util.List;
import java.util.Random;

public class GameRunner {
    private static boolean notAWinner;

    public static void main(String[] args) {
        Game aGame = new Game();

        User chet = new User(1,"111","111","chet",0);
        User pat = new User(2,"222","222","pat",0);
        User sue = new User(3,"333","333","sue",0);

        try {
            aGame.addNewPlayer(parse(chet));
            aGame.addNewPlayer(parse(pat));
            aGame.addNewPlayer(parse(sue));
        } catch (EncodeException e) {
            e.printStackTrace();
        }


        Random rand = new Random();

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                // TODO-later: The name of the variable notAWinner should be isGameStillInProgress
                notAWinner = aGame.answeredWrong();
            } else {
                notAWinner = aGame.answeredCorrect();
            }


        } while (notAWinner);

    }

    private static UserVo parse(User user) {
        UserVo result = new UserVo();
        BeanUtils.copyProperties(user, result);
        double winRate = 0;
        result.setWinRate(winRate);
        return result;
    }
}
