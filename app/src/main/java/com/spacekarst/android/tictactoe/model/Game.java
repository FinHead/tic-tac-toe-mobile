package com.spacekarst.android.tictactoe.model;

import android.content.Context;

import com.spacekarst.android.tictactoe.view.Buttons;
import com.spacekarst.android.tictactoe.utils.MiniMax;

/**
 * Created by jasonkotz on 10/17/17.
 */
public class Game {

    private Context mContext;
    private boolean mGameOver;
    private Player.PlayerToken mWinner;
    private Player.PlayerToken mCurrentPlayer;
    private int mMoveCnt;

    public Game( Player.PlayerToken currentPlayer){
        mCurrentPlayer = currentPlayer;
        newGame();
    }

    private void newGame(){
        mMoveCnt = 0;
        mGameOver = false;
        mWinner = Player.PlayerToken.Blank;
    }

    public void aiMove(){

        mCurrentPlayer = (mCurrentPlayer == Player.PlayerToken.X) ? Player.PlayerToken.O : Player.PlayerToken.X;

        MiniMax.MinMax(mCurrentPlayer, this, 1);
    }

    /**
     * Is the game over?
     * @return
     */
    public boolean isGameOver () {
        return mGameOver;
    }

    /**
     * Who won?
     * @return
     */
    public Player.PlayerToken getWinner () {
        if (!mGameOver) {
            throw new IllegalStateException("TicTacToe is not over yet.");
        }
        return mWinner;
    }

    /**
     * Return the current player
     * @return the current player
     */
    public Player.PlayerToken getCurrentPlayer () {
        return mCurrentPlayer;
    }

    /**
     * Get a copy of the current game to simulate moves.
     * @return copy of the current game
     */
    public Game getCopy () {

        Game newGame = new Game(mCurrentPlayer);

        return newGame;
    }

    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     * @param index The button tag to select.
     * @return true if the move was successful.
     */
    public boolean move (int index) {

        // Whose move is it?
        int buttonState = (mCurrentPlayer == Player.PlayerToken.X) ? Buttons.X : Buttons.O;
        //Buttons.setButtonTagState(index, buttonState);

        // Check for a winner.
        if (checkRowsForWinner()) mGameOver = true; mWinner = mCurrentPlayer;
        if (checkColumnsForWinner()) mGameOver = true; mWinner = mCurrentPlayer;
        if (checkDiagonalsForWinner()) mGameOver = true; mWinner = mCurrentPlayer;
        if (checkForDraw()) mGameOver = true; mWinner = Player.PlayerToken.Blank;

        mCurrentPlayer = (mCurrentPlayer == Player.PlayerToken.X) ? Player.PlayerToken.O : Player.PlayerToken.X;
        return true;
    }

    private boolean checkRowsForWinner () {

        boolean isWinner = false;

        // Check 1st row.
        if (!isButtonEmpty(0) && !isButtonEmpty(1) && !isButtonEmpty(2)
            && (getButtonState(0) == getButtonState(1))
                && (getButtonState(0) == getButtonState(2))){
            isWinner = true;
        }
        // Check 2nd row.
        if (!isWinner
                && !isButtonEmpty(3) && !isButtonEmpty(4) && !isButtonEmpty(5)
                && (getButtonState(3) == getButtonState(4))
                && (getButtonState(3) == getButtonState(5))){
            isWinner = true;
        }
        // Check 3rd row.
        if (!isWinner
                && !isButtonEmpty(6) && !isButtonEmpty(7) && !isButtonEmpty(8)
                && (getButtonState(6) == getButtonState(7))
                && (getButtonState(6) == getButtonState(8))){
            isWinner = true;
        }

        return isWinner;
    }

    private boolean checkColumnsForWinner () {

        boolean isWinner = false;

        // Check 1st column.
        if (!isButtonEmpty(0) && !isButtonEmpty(3) && !isButtonEmpty(6)
                && (getButtonState(0) == getButtonState(3))
                && (getButtonState(0) == getButtonState(6))){
            isWinner = true;
        }
        // Check 2nd column.
        if (!isWinner
                && !isButtonEmpty(1) && !isButtonEmpty(4) && !isButtonEmpty(7)
                && (getButtonState(1) == getButtonState(4))
                && (getButtonState(1) == getButtonState(7))){
            isWinner = true;
        }
        // Check 3rd column.
        if (!isWinner
                && !isButtonEmpty(2) && !isButtonEmpty(5) && !isButtonEmpty(8)
                && (getButtonState(2) == getButtonState(5))
                && (getButtonState(2) == getButtonState(8))){
            isWinner = true;
        }

        return isWinner;
    }

    private boolean checkDiagonalsForWinner () {

        boolean isWinner = false;

        // Check Upper Left to Lower Right.
        if (!isButtonEmpty(0) && !isButtonEmpty(4) && !isButtonEmpty(8)
                && (getButtonState(0) == getButtonState(4))
                && (getButtonState(0) == getButtonState(8))){
            isWinner = true;
        }
        // Check Upper Right to Lower Left.
        if (!isWinner
                && !isButtonEmpty(2) && !isButtonEmpty(4) && !isButtonEmpty(6)
                && (getButtonState(2) == getButtonState(4))
                && (getButtonState(2) == getButtonState(6))){
            isWinner = true;
        }

        return isWinner;
    }

    private boolean checkForDraw () {

        boolean isDraw = false;

        if (0 == Buttons.getEmptyButtons().size()) isDraw = true;

        return isDraw;
    }

    private int getButtonState(int buttonNum){
        return Buttons.getButtonState(Buttons.getButtonState(buttonNum));
    }

    private boolean isButtonEmpty(int buttonNum){
        return Buttons.EMPTY == getButtonState(buttonNum);
    }
}
