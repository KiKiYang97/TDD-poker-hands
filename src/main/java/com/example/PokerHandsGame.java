package com.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public int judgeCard(List<Card> cards) {
        Set<String> types = new HashSet<>();
        cards = cards.stream().map(card -> {
            types.add(card.getType());
            return card;
        }).collect(Collectors.toList());
        Set<Integer> numbers = new HashSet<>();
        cards = cards.stream().map(card -> {
            numbers.add(card.getNumber());
            return card;
        }).collect(Collectors.toList());
        boolean straight = numbers.size() == 5 && (Integer) numbers.toArray()[4] - (Integer) numbers.toArray()[0] == 4;
        if (!straight) {
            if (numbers.size() == 5 && types.size() != 1) {
                return 1;
            } else if (numbers.size() == 4 && types.size() != 1) {
                return 2;
            }
        }

        return 0;
    }
}
