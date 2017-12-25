package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.User;

import javax.websocket.EncodeException;
import java.util.Random;

public class GameRunner {
    private static boolean notAWinner;

    public static void main(String[] args) {
        Game aGame = new Game();

        User chet = new User(1,"111","111","chet",0);
        User pat = new User(2,"222","222","pat",0);
        User sue = new User(3,"333","333","sue",0);

        try {
            aGame.addNewPlayer(chet);
            aGame.addNewPlayer(pat);
            aGame.addNewPlayer(sue);
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
}
