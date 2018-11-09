package com.example.stepa.war;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    Deck(){
        deck = new ArrayList<>();
    }

    public void addCard(Card card){
        deck.add(card);
    }

    public Card getTopCard(){
        return deck.get(0);
    }

    public void deleteTopCard(){
        deck.remove(0);
    }

    public int getSizeOfTheDeck(){
        return deck.size();
    }
}
