package com.spacekarst.android.tictactoe.utils;

import com.spacekarst.android.tictactoe.view.Buttons;
import com.spacekarst.android.tictactoe.model.Game;
import com.spacekarst.android.tictactoe.model.Player;

/**
 * Created by jasonkotz on 10/17/17.
 */

public class MiniMax {

    private final String TAG = getClass().getSimpleName();

    private static double mDepth;

    public static void MinMax(Player.PlayerToken playerToken, Game game, int currentDepth){
        MiniMax.mDepth = currentDepth;
        miniMax(playerToken, game, 0);
    }

    /**
     * run MiniMax.
     * @param playerToken   the player
     * @param game          the current game
     * @param currentDepth  the current depth
     * @return              the score of the game
     */
    private static int miniMax (Player.PlayerToken playerToken, Game game, int currentDepth) {
        if (currentDepth++ == mDepth || game.isGameOver()) {
            return score(playerToken, game);
        }

        if (game.getCurrentPlayer() == playerToken) {
            return getMax(playerToken, game, currentDepth);
        } else {
            return getMin(playerToken, game, currentDepth);
        }

    }

    /**
     * the move with the highest possible score.
     * @param playerToken   the player
     * @param game          the current game
     * @param currentDepth  the current depth
     * @return              the score of the game
     */
    private static int getMax (Player.PlayerToken playerToken, Game game, int currentDepth) {

        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer emptySpace : Buttons.getEmptyButtons()) {

            Game gameCopy = game.getCopy();
            gameCopy.move(emptySpace);

            int score = miniMax(playerToken, gameCopy, currentDepth);

            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = emptySpace;
            }

        }

        game.move(indexOfBestMove);
        return (int)bestScore;
    }

    /**
     * Play the move with the lowest score.
     * @param playerToken   the player
     * @param game          the current game
     * @param currentDepth  the current depth
     * @return              the score of the game
     */
    private static int getMin (Player.PlayerToken playerToken, Game game, int currentDepth) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer emptySpace : Buttons.getEmptyButtons()) {

            Game gameCopy = game.getCopy();

            int score = miniMax(playerToken, gameCopy, currentDepth);

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = emptySpace;
            }

        }

        game.move(indexOfBestMove);
        return (int)bestScore;
    }

    private static int score(Player.PlayerToken playerToken, Game game){

        int score = 0;

        Player.PlayerToken opponent = (playerToken == Player.PlayerToken.X) ? Player.PlayerToken.O : Player.PlayerToken.X;

        if (game.isGameOver() && game.getWinner() == playerToken) {
            score = ((Double) (10 - mDepth)).intValue();
        } else if (game.isGameOver() && game.getWinner() == opponent) {
            score = ((Double) (mDepth -10)).intValue();
        }

        return score;
    }


}
