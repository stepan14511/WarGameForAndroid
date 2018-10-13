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

        opponentCard = generateCard(false);
        playerCard = generateCard(true);
        opponentPoints = 26; playerPoints = 26;

        startInitElements();
        updateState();
    }

    private void startInitElements(){
        TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
        winMessage.setVisibility(View.INVISIBLE);

        setOnClickListeners();
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
        playerCard = generateCard(false);

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
        playerCard = generateCard(true);
        opponentCard = generateCard(false);

        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickOnClosedCard(imgView);
            }
        });
        updateState();
    }

    private Card generateCard(boolean isClear){
        if(isClear)
            return new Card(true);

        int randomPriority = new Random().nextInt(13);
        int randomSuit = new Random().nextInt(4);
        return new Card(randomPriority, randomSuit);
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
}