package com.spacekarst.android.tictactoe.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.spacekarst.android.tictactoe.model.Game;
import com.spacekarst.android.tictactoe.model.Player;
import com.spacekarst.android.tictactoe.R;
import com.spacekarst.android.tictactoe.presenter.GamePlay;


public class MainActivity extends AppCompatActivity implements GamePlay.View {

    private final String TAG = getClass().getSimpleName();
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the button click listener to all the buttons
        for (int i = 0; i < Buttons.getButtons().length; i++) {
            ImageView buttonNum = findViewById(Buttons.getButtons()[i]);
            buttonNum.setOnClickListener(addOnClickListener());
        }

        mGame = new Game(Player.PlayerToken.X);
    }

    private View.OnClickListener addOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Can only select if empty.
                if (Buttons.EMPTY == Buttons.getButtonState(view.getId())){
                    selectButton(Integer.getInteger(view.getTag().toString()));
                    runAI();
                }
            }
        };
    }

    private void runAI(){
        mGame.aiMove();
    }

    @Override
    public void selectButton(int index){
        Buttons.setButtonState(index, Buttons.X);

        // Get all the button view ids.
        //int[] buttons = Buttons.getButtons();
        for (int buttonId: Buttons.getButtons()){
            View button = findViewById(buttonId);
            if (button.getTag().equals(index)){
                // Visually mark the button.
                ((ImageView)button).setScaleType(ImageView.ScaleType.FIT_CENTER);
                ((ImageView)button).setImageResource(R.drawable.x);
            }
        }
    }
}
