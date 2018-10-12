package com.example.stepa.war;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends Activity {
    private Card opponentCard = new Card(true), playerCard = new Card(true);
    private int opponentPoints, playerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        clearCard(true); clearCard(false);
        setOnClickListeners();
        opponentPoints = 26; playerPoints = 26; updateState();

        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        opponentCard = new Card(randomPriority, randomSuit);
        updateState();
    }

    private void updateState(){
        ImageView imgViewOp = (ImageView) findViewById(R.id.imgView_opponent_card);
        imgViewOp.setImageResource(opponentCard.getIdOfCardImage());
        ImageView imgViewPl = (ImageView) findViewById(R.id.imgView_player_card);
        imgViewPl.setImageResource(playerCard.getIdOfCardImage());
        TextView txtViewOp = (TextView) findViewById(R.id.textView_opponent_points);
        txtViewOp.setText(("" + opponentPoints).toCharArray(), 0, ("" + opponentPoints).toCharArray().length);
        TextView txtViewPl = (TextView) findViewById(R.id.textView_player_points);
        txtViewPl.setText(("" + playerPoints).toCharArray(), 0, ("" + playerPoints).toCharArray().length);
    }

    private void setOnClickListeners(){
        final ImageView imgViewPl = (ImageView) findViewById(R.id.imgView_player_card);
        imgViewPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOnClosedCard(imgViewPl);
            }
        });
    }

    private void onClickOnClosedCard(final ImageView imgView){
        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        playerCard = new Card(randomPriority, randomSuit);

        if(!opponentCard.isEqual(playerCard)) {
            if (opponentCard.isBetterThan(playerCard)) {
                opponentPoints += 1;
                playerPoints -= 1;
            }
            else{
                opponentPoints -= 1;
                playerPoints += 1;
            }
        }

        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickOnOpenedCard(imgView);
            }
        });
        updateState();
    }

    private void onClickOnOpenedCard(final ImageView imgView){
        clearCard(false);
        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        opponentCard = new Card(randomPriority, randomSuit);

        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickOnClosedCard(imgView);
            }
        });
        updateState();
    }

    private void clearCard(boolean isOpponent){
        if(isOpponent){
            opponentCard = new Card(true);
            updateState();
        }
        else{
            playerCard = new Card(true);
            updateState();
        }
    }
}
