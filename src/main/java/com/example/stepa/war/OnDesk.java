package com.example.stepa.war;

import java.util.ArrayList;

public class OnDesk {
    private ArrayList<Card> array_of_cards;
    private int number_of_players_cards;
    private int number_of_opponents_cards;

    OnDesk(){
        array_of_cards = new ArrayList<Card>();
        number_of_players_cards = 0;
        number_of_opponents_cards = 0;
    }

    public void add_card(Card card, int owner_of_card) throws IllegalArgumentException{
        if(owner_of_card == Card.OPPONENT_CARD)
            number_of_opponents_cards++;
        else if(owner_of_card == Card.PLAYERS_CARD)
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

    public Card get_card(int index_of_card){
        return array_of_cards.get(index_of_card);
    }

    public int get_deck_size(){
        return array_of_cards.size();
    }
}
