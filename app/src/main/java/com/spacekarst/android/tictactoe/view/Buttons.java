package com.spacekarst.android.tictactoe.view;

import android.support.annotation.IntDef;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.widget.ImageView;

import com.spacekarst.android.tictactoe.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;

/**
 * Created by jasonkotz on 10/17/17.
 */

public abstract class Buttons {

    @IntDef({EMPTY, O, X})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonState {}

    public static final int EMPTY = 0;
    public static final int O = 1;
    public static final int X = 2;

    /**
     * define array of buttons used on the screen.
     */
    private static int[] mButtons = {R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4,
            R.id.imageButton5, R.id.imageButton6, R.id.imageButton7, R.id.imageButton8, R.id.imageButton9};
    //public static int[] buttons = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    private static SparseIntArray BUTTON_STATE = new SparseIntArray();
    static {

        for (int i=0; i< mButtons.length; i++){
            BUTTON_STATE.put(mButtons[i], EMPTY);
        }
    }

    @ButtonState
    public static int getButtonState(int buttonId){
        return BUTTON_STATE.get(buttonId);
    }

    public static HashSet<Integer> getEmptyButtons(){
        HashSet<Integer> emptyButtons = new HashSet<>();

        for (int i=0; i< BUTTON_STATE.size(); i++){
            if (EMPTY == BUTTON_STATE.valueAt(i)){
                emptyButtons.add(i);
            }
        }

        return emptyButtons;
    }

    public static int[] getButtons(){
        return mButtons;
    }

    public static void setButtonIdState(int buttonId, @ButtonState int buttonState){
        BUTTON_STATE.append(buttonId, buttonState);
    }

    public static void setButtonState(int index, @ButtonState int buttonState){
        BUTTON_STATE.append(BUTTON_STATE.keyAt(index), buttonState);
    }
}
