package com.spacekarst.android.tictactoe.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jasonkotz on 10/17/17.
 */

public abstract class Player {

    public enum PlayerType {PUNY_HUMAN, MINIMAX}

//    @IntDef({PUNY_HUMAN, MINIMAX})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface PlayerType {}
//
//    public static final int PUNY_HUMAN = 0;
//    public static final int MINIMAX = 1;
//
//    @PlayerType
//    public abstract int getPlayerType();

    public enum PlayerToken {Blank, X, O}

//    @IntDef({X, CIRCLE})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface PlayerToken {}
//
//    public static final int X = 0;
//    public static final int CIRCLE = 1;
//
//    @PlayerToken
//    public abstract int getPlayerToken();
}
