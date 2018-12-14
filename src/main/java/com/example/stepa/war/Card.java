package com.example.stepa.war;

public class Card {
    private int numberOfPriority, suit;

    Card(int numberOfPriority, int suit) throws IllegalArgumentException{
        if((numberOfPriority < 0) || (numberOfPriority > 12) || (suit < 0) || (suit > 3)){
            throw new IllegalArgumentException();
        }
        this.numberOfPriority = numberOfPriority;
        this.suit = suit;
    }

    private int findIdOfCardImage(int numberOfPriority, int suit){
        int cardImageIds[][] = new int[][]{
                {R.drawable.card_101, R.drawable.card_102, R.drawable.card_103, R.drawable.card_104,
                R.drawable.card_105, R.drawable.card_106, R.drawable.card_107, R.drawable.card_108,
                R.drawable.card_109, R.drawable.card_110, R.drawable.card_111, R.drawable.card_112,
                R.drawable.card_113},
                {R.drawable.card_201, R.drawable.card_202, R.drawable.card_203, R.drawable.card_204,
                R.drawable.card_205, R.drawable.card_206, R.drawable.card_207, R.drawable.card_208,
                R.drawable.card_209, R.drawable.card_210, R.drawable.card_211, R.drawable.card_212,
                R.drawable.card_213},
                {R.drawable.card_301, R.drawable.card_302, R.drawable.card_303, R.drawable.card_304,
                R.drawable.card_305, R.drawable.card_306, R.drawable.card_307, R.drawable.card_308,
                R.drawable.card_309, R.drawable.card_310, R.drawable.card_311, R.drawable.card_312,
                R.drawable.card_313},
                {R.drawable.card_401, R.drawable.card_402, R.drawable.card_403, R.drawable.card_404,
                R.drawable.card_405, R.drawable.card_406, R.drawable.card_407, R.drawable.card_408,
                R.drawable.card_409, R.drawable.card_410, R.drawable.card_411, R.drawable.card_412,
                R.drawable.card_413}};

        return cardImageIds[suit][numberOfPriority];
    }

    public boolean isBetterThan(Card card){
        if((this.numberOfPriority == 0) && (card.numberOfPriority == 12))
            return true;
        if((this.numberOfPriority == 12) && (card.numberOfPriority == 0))
            return false;
        return (this.numberOfPriority > card.numberOfPriority);
    }

    public boolean isEqual(Card card){
        return (this.numberOfPriority == card.numberOfPriority);
    }

    public int getIdOfCardImage(){
        return findIdOfCardImage(this.numberOfPriority, this.suit);
    }
}
