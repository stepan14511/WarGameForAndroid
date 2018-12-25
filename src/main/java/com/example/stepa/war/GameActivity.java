package com.example.stepa.war;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends Activity {
    private Card opponentCard, playerCard;
    private boolean isOpponentsCardOpened = false, isPlayersCardOpened = false;
    private Deck playersDeck = new Deck(), opponentsDeck = new Deck();
    private OnDesk onDesk = new OnDesk();
    int currentLayer;
    int[][] listOfCardsLayers = new int[][]{
            {R.id.linearLayout_cards0, R.id.imgView_opponent_card0, R.id.imgView_player_card0},
            {R.id.linearLayout_cards1, R.id.imgView_opponent_card1, R.id.imgView_player_card1},
            {R.id.linearLayout_cards2, R.id.imgView_opponent_card2, R.id.imgView_player_card2},
            {R.id.linearLayout_cards3, R.id.imgView_opponent_card3, R.id.imgView_player_card3},
            {R.id.linearLayout_cards4, R.id.imgView_opponent_card4, R.id.imgView_player_card4},
            {R.id.linearLayout_cards5, R.id.imgView_opponent_card5, R.id.imgView_player_card5},
            {R.id.linearLayout_cards6, R.id.imgView_opponent_card6, R.id.imgView_player_card6},
            {R.id.linearLayout_cards7, R.id.imgView_opponent_card7, R.id.imgView_player_card7},
            {R.id.linearLayout_cards8, R.id.imgView_opponent_card8, R.id.imgView_player_card8},
            {R.id.linearLayout_cards9, R.id.imgView_opponent_card9, R.id.imgView_player_card9},
            {R.id.linearLayout_cards10, R.id.imgView_opponent_card10, R.id.imgView_player_card10},
            {R.id.linearLayout_cards11, R.id.imgView_opponent_card11, R.id.imgView_player_card11},
            {R.id.linearLayout_cards12, R.id.imgView_opponent_card12, R.id.imgView_player_card12},
            {R.id.linearLayout_cards13, R.id.imgView_opponent_card13, R.id.imgView_player_card13},
            {R.id.linearLayout_cards14, R.id.imgView_opponent_card14, R.id.imgView_player_card14},
            {R.id.linearLayout_cards15, R.id.imgView_opponent_card15, R.id.imgView_player_card15},
            {R.id.linearLayout_cards16, R.id.imgView_opponent_card16, R.id.imgView_player_card16},
            {R.id.linearLayout_cards17, R.id.imgView_opponent_card17, R.id.imgView_player_card17},
            {R.id.linearLayout_cards18, R.id.imgView_opponent_card18, R.id.imgView_player_card18},
            {R.id.linearLayout_cards19, R.id.imgView_opponent_card19, R.id.imgView_player_card19},
            {R.id.linearLayout_cards20, R.id.imgView_opponent_card20, R.id.imgView_player_card20},
            {R.id.linearLayout_cards21, R.id.imgView_opponent_card21, R.id.imgView_player_card21},
            {R.id.linearLayout_cards22, R.id.imgView_opponent_card22, R.id.imgView_player_card22},
            {R.id.linearLayout_cards23, R.id.imgView_opponent_card23, R.id.imgView_player_card23},
            {R.id.linearLayout_cards24, R.id.imgView_opponent_card24, R.id.imgView_player_card24},
            {R.id.linearLayout_cards25, R.id.imgView_opponent_card25, R.id.imgView_player_card25}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        shuffleCardsToDecks();

        opponentCard = opponentsDeck.getTopCard();
        isOpponentsCardOpened = true;
        onDesk.add_card(opponentCard, Card.OPPONENT_CARD);
        isPlayersCardOpened = false;
        currentLayer = 0;

        startInitElements();
        updateState();
    }

    private void startInitElements(){
        TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
        winMessage.setVisibility(View.INVISIBLE);
        for(int i = 1; i < listOfCardsLayers.length; i++){
            LinearLayout linearLayout = (LinearLayout) findViewById(listOfCardsLayers[i][0]);
            linearLayout.setVisibility(View.INVISIBLE);

            //Set left margins
            RelativeLayout.LayoutParams parameter =  (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            parameter.setMargins(10 * i, parameter.topMargin, parameter.rightMargin, parameter.bottomMargin);
            linearLayout.setLayoutParams(parameter);
        }

        setOnClickListeners();
    }

    private void shuffleCardsToDecks(){
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                cards.add(i * 100 + j);
            }
        }
        for(int i = 0; i < 26; i++){
            int card = new Random().nextInt(cards.size());
            playersDeck.addCard(new Card(cards.get(card) % 100, cards.get(card) / 100));
            cards.remove(card);
            card = new Random().nextInt(cards.size());
            opponentsDeck.addCard(new Card(cards.get(card) % 100, cards.get(card) / 100));
            cards.remove(card);
        }
    }

    private void setOnClickListeners(){
        final ImageView imgViewPl = (ImageView) findViewById(listOfCardsLayers[currentLayer][2]);
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
        playerCard = playersDeck.getTopCard();
        isPlayersCardOpened = true;
        onDesk.add_card(playerCard, Card.PLAYERS_CARD);

        if(!opponentCard.isEqual(playerCard)) {
            if (opponentCard.isBetterThan(playerCard)) {
                if(playersDeck.getSizeOfTheDeck() <= 0) {
                    for (int i = 0; i < onDesk.get_deck_size(); i++) {
                        opponentsDeck.addCard(onDesk.get_card(i));
                    }
                    onDesk = new OnDesk();
                    sayToPlayer(false);
                }
            }
            else{
                if(opponentsDeck.getSizeOfTheDeck() <= 0) {
                    for(int i = 0 ; i < onDesk.get_deck_size(); i++){
                        playersDeck.addCard(onDesk.get_card(i));
                    }
                    onDesk = new OnDesk();
                    sayToPlayer(true);
                }
            }
        }
        updateState();
    }

    private void onClickOnOpenedCard(final ImageView imgView){
        if(playerCard.isEqual(opponentCard)){
            imgView.setOnClickListener(null);

            if(currentLayer + 2 > playersDeck.getSizeOfTheDeck())
                sayToPlayer(false);
            else if(currentLayer + 2 > opponentsDeck.getSizeOfTheDeck())
                sayToPlayer(true);
            else {
                onDesk.add_card(opponentsDeck.getTopCard(), Card.OPPONENT_CARD);
                onDesk.add_card(playersDeck.getTopCard(), Card.PLAYERS_CARD);
                LinearLayout linearLayout = (LinearLayout) findViewById(listOfCardsLayers[currentLayer + 1][0]);
                linearLayout.setVisibility(View.VISIBLE);
                ImageView imageView1 = (ImageView) findViewById(listOfCardsLayers[currentLayer + 1][1]);
                imageView1.setImageResource(R.drawable.card_back);
                ImageView imageView2 = (ImageView) findViewById(listOfCardsLayers[currentLayer + 1][2]);
                imageView2.setImageResource(R.drawable.card_back);

                currentLayer += 2;

                if (currentLayer + 1 > playersDeck.getSizeOfTheDeck())
                    sayToPlayer(false);
                else if (currentLayer + 1 > opponentsDeck.getSizeOfTheDeck())
                    sayToPlayer(true);
                else {
                    linearLayout = (LinearLayout) findViewById(listOfCardsLayers[currentLayer][0]);
                    linearLayout.setVisibility(View.VISIBLE);
                    imageView1 = (ImageView) findViewById(listOfCardsLayers[currentLayer][1]);
                    imageView1.setImageResource(R.drawable.card_back);
                    final ImageView imageView3 = (ImageView) findViewById(listOfCardsLayers[currentLayer][2]);
                    imageView3.setImageResource(R.drawable.card_back);

                    imageView3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onClickOnClosedCard(imageView3);
                        }
                    });
                }
            }
        }
        else{
            if (opponentCard.isBetterThan(playerCard)) {
                for (int i = 0; i < onDesk.get_deck_size(); i++) {
                    opponentsDeck.addCard(onDesk.get_card(i));
                }
            }
            else{
                for(int i = 0; i < onDesk.get_deck_size(); i++){
                    playersDeck.addCard(onDesk.get_card(i));
                }
            }
            currentLayer = 0;
            onDesk = new OnDesk();
            final ImageView imageView = (ImageView) findViewById(listOfCardsLayers[currentLayer][2]);
            imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { onClickOnClosedCard(imageView);
                    }
                });
            for(int i = 1; i < listOfCardsLayers.length; i++){
                LinearLayout linearLayout = (LinearLayout) findViewById(listOfCardsLayers[i][0]);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        }
        isPlayersCardOpened = false;
        opponentCard = opponentsDeck.getTopCard();
        isOpponentsCardOpened = true;
        onDesk.add_card(opponentCard, Card.OPPONENT_CARD);
        updateState();
    }

    private void sayToPlayer(boolean isWin){
        TextView winMessage = (TextView) findViewById(R.id.textView_win_message);
        winMessage.setVisibility(View.VISIBLE);
        if(isWin){
            winMessage.setTextColor(getResources().getColor(R.color.green));
            winMessage.setText(R.string.message_win);
        }
        else{
            winMessage.setTextColor(getResources().getColor(R.color.red));
            winMessage.setText(R.string.message_lose);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(listOfCardsLayers[currentLayer][0]);
        linearLayout.setOnClickListener(new View.OnClickListener() {
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
        updateState();
    }

    private void updateState(){
        ImageView imgViewOp = (ImageView) findViewById(listOfCardsLayers[currentLayer][1]);
        if(isOpponentsCardOpened)
            imgViewOp.setImageResource(opponentCard.getIdOfCardImage());
        else
            imgViewOp.setImageResource(R.drawable.card_back);
        ImageView imgViewPl = (ImageView) findViewById(listOfCardsLayers[currentLayer][2]);
        if(isPlayersCardOpened)
            imgViewPl.setImageResource(playerCard.getIdOfCardImage());
        else
            imgViewPl.setImageResource(R.drawable.card_back);
        TextView txtViewOp = (TextView) findViewById(R.id.textView_opponent_points);
        txtViewOp.setText(("" + (opponentsDeck.getSizeOfTheDeck() + (onDesk.get_deck_size() + 1) / 2)).toCharArray(), 0, ("" + opponentsDeck.getSizeOfTheDeck()).toCharArray().length);
        TextView txtViewPl = (TextView) findViewById(R.id.textView_player_points);
        txtViewPl.setText(("" + (playersDeck.getSizeOfTheDeck() + (onDesk.get_deck_size() / 2))).toCharArray(), 0, ("" + playersDeck.getSizeOfTheDeck()).toCharArray().length);
    }
}