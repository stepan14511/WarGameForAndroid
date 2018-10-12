package com.example.stepa.war;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        opponentPoints = 5; playerPoints = 4;

        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        opponentCard = new Card(randomPriority, randomSuit);

        TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
        winMessage.setVisibility(View.INVISIBLE);
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
        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickOnOpenedCard(imgView);
            }
        });

        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        playerCard = new Card(randomPriority, randomSuit);

        if(!opponentCard.isEqual(playerCard)) {
            if (opponentCard.isBetterThan(playerCard)) {
                opponentPoints += 1;
                playerPoints -= 1;
                if(playerPoints <= 0){
                    TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
                    winMessage.setVisibility(View.VISIBLE);
                    winMessage.setTextColor(getResources().getColor(R.color.red));
                    winMessage.setText(R.string.message_lose);
                    imgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });

                    RelativeLayout fullScreenView = (RelativeLayout) findViewById(R.id.fullScreenView);
                    fullScreenView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
            else{
                opponentPoints -= 1;
                playerPoints += 1;
                if(opponentPoints <= 0){
                    TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
                    winMessage.setVisibility(View.VISIBLE);
                    winMessage.setTextColor(getResources().getColor(R.color.green));
                    winMessage.setText(R.string.message_win);
                    imgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });

                    RelativeLayout fullScreenView = (RelativeLayout) findViewById(R.id.fullScreenView);
                    fullScreenView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
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
        }
        else{
            playerCard = new Card(true);
        }
    }
}
