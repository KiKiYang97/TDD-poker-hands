package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandsGame {
    public List<Card> translate(String input) {
        List<String> cardStrings = Arrays.asList(input.split(" "));
        return cardStrings.stream().map(cardString -> {
            String firstChar = cardString.substring(0, 1);
            Card card = new Card();
            switch (firstChar) {
                case "T":
                    card.setNumber(10);
                    break;
                case "J":
                    card.setNumber(11);
                    break;
                case "Q":
                    card.setNumber(12);
                    break;
                case "K":
                    card.setNumber(13);
                    break;
                case "A":
                    card.setNumber(14);
                    break;
                default:
                    card.setNumber(Integer.parseInt(firstChar));
                    break;
            }
            card.setType(cardString.substring(1, 2));
            return card;
        }).collect(Collectors.toList());
    }
}
