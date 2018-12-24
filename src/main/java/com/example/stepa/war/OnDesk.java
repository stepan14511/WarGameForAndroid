package com.example.stepa.war;

import java.util.ArrayList;

public class OnDesk {
    public static final int OPPONENT_CARD = 0;
    public static final int PLAYERS_CARD = 1;

    private ArrayList<Card> array_of_cards;
    private int number_of_players_cards;
    private int number_of_opponents_cards;

    OnDesk(){
        array_of_cards = new ArrayList<>();
        number_of_players_cards = 0;
        number_of_opponents_cards = 0;
    }

    public void addCard(Card card, int owner_of_card) throws IllegalArgumentException{
        if(owner_of_card == OPPONENT_CARD)
            number_of_opponents_cards++;
        else if(owner_of_card == PLAYERS_CARD)
            number_of_players_cards++;
        else throw new IllegalArgumentException();
        array_of_cards.add(card);
    }

    public int get_number_of_players_cards(){
        return number_of_players_cards;
    }

    public int get_number_of_opponents_cards(){
        return number_of_opponents_cards;
    }
}
