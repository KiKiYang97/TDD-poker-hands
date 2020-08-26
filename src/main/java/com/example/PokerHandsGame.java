package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PokerHandsGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("Black:");
            String black = scanner.nextLine();
            if (black.equals("")) {
                break;
            }
            System.out.println("White:");
            String white = scanner.nextLine();
            PokerHandsGame game = new PokerHandsGame();
            System.out.println(game.play(black,white));
        }
    }

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

    public double judgeCard(List<Card> cards) {
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
        Collections.sort(cards);
        boolean straight = numbers.size() == 5 && (Integer) numbers.toArray()[4] - (Integer) numbers.toArray()[0] == 4;
        if (!straight) {
            if (types.size() != 1) {
                if (numbers.size() == 5) {
                    return isHighCard(cards);
                } else if (numbers.size() == 4) {
                    return isPair(cards);
                } else if (numbers.size() == 3) {
                    if (cards.get(0).getNumber().equals(cards.get(2).getNumber()) ||
                            cards.get(1).getNumber().equals(cards.get(3).getNumber()) ||
                            cards.get(2).getNumber().equals(cards.get(4).getNumber())) {
                        return isThreeOfAKind(cards);
                    } else {
                        return isTwoPair(cards, numbers);
                    }
                } else {
                    if (cards.get(0).getNumber().equals(cards.get(3).getNumber()) ||
                        cards.get(1).getNumber().equals(cards.get(4).getNumber())) {
                        return isFourOfAKind(cards);
                    } else {
                        return isFullHouse(cards);
                    }
                }
            } else {
                return isFlush(cards);
            }
        } else {
            if (types.size() != 1) {
                return isStraight(cards);
            } else {
                return isStraightFlush(cards);
            }
        }
    }

    private double isStraightFlush(List<Card> cards) {
        return fomateDouble(9.0 + cards.get(4).getNumber() * 0.01, 2);
    }

    private double isFourOfAKind(List<Card> cards) {
        if (cards.get(0).getNumber().equals(cards.get(3).getNumber())) {
            return fomateDouble(8.0 + cards.get(0).getNumber() * 0.01, 2);
        } else {
            return fomateDouble(8.0 + cards.get(4).getNumber() * 0.01, 2);
        }
    }

    private double isFullHouse(List<Card> cards) {
        if (cards.get(0).getNumber().equals(cards.get(2).getNumber())) {
            return fomateDouble(7.0 + cards.get(0).getNumber() * 0.01, 2);
        } else {
            return fomateDouble(7.0 + cards.get(4).getNumber() * 0.01, 2);
        }
    }

    private double isFlush(List<Card> cards) {
        return fomateDouble(6.0 + cards.get(4).getNumber() * 0.01, 2);
    }

    private double isStraight(List<Card> cards) {
        return fomateDouble(5.0 + cards.get(4).getNumber() * 0.01, 2);
    }

    private double isThreeOfAKind(List<Card> cards) {
        if (cards.get(2).getNumber().equals(cards.get(4).getNumber())) {
            return fomateDouble(4.0 + cards.get(4).getNumber() * 0.01 + cards.get(1).getNumber() * 0.0001, 4);
        } else {
            return fomateDouble(4.0 + cards.get(2).getNumber() * 0.01 + cards.get(4).getNumber() * 0.0001, 4);
        }
    }

    private double isTwoPair(List<Card> cards, Set<Integer> numbers) {
        double result = 0d;
        int highCardIndex = 4;
        for (int i = 1; i < 5; i++) {
            if (cards.get(i).getNumber().equals(cards.get(i - 1).getNumber())) {
                numbers.remove(cards.get(i).getNumber());
                result = cards.get(i).getNumber() * 0.01 + result * 0.01;
            }
        }
        return fomateDouble(3.0 + result + (int)numbers.stream().findFirst().get() * 0.000001, 6);
    }

    private double isPair(List<Card> cards) {
        double result = 0d;
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getNumber().equals(cards.get(i + 1).getNumber())) {
                if (i == 3) {
                    result = 2.0 + cards.get(i).getNumber() * 0.01 + cards.get(i - 1).getNumber() * 0.0001;
                } else {
                    result = 2.0 + cards.get(i).getNumber() * 0.01 + cards.get(4).getNumber() * 0.0001;
                }
            }
        }
        return fomateDouble(result, 4);
    }

    private double isHighCard(List<Card> cards) {
        double result = 0d;
        for (int i = 0; i < cards.size(); i++) {
            result = result * 0.01 + cards.get(i).getNumber() * 0.01;
        }
        return fomateDouble(result + 1.0, 10);
    }

    private double fomateDouble(double number, int bit) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(bit, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String play(String black, String white) {
        List<Card> blackCards = translate(black);
        List<Card> whiteCards = translate(white);
        double blackNumber = judgeCard(blackCards);
        double whiteNumber = judgeCard(whiteCards);
        StringBuilder stringBuilder = new StringBuilder();
        if (blackNumber == whiteNumber) {
            stringBuilder.append("Tie.");
        } else{
            int result;
            if (blackNumber > whiteNumber) {
                stringBuilder.append("Black wins.");
                result = (int)blackNumber;
            } else {
                stringBuilder.append("White wins.");
                result = (int)whiteNumber;
            }
            switch (result) {
                case 1: stringBuilder.append(" - with high card");break;
                case 2: stringBuilder.append(" - with pair");break;
                case 3: stringBuilder.append(" - with two pairs");break;
                case 4: stringBuilder.append(" - with three of a kind");break;
                case 5: stringBuilder.append(" - with straight");break;
                case 6: stringBuilder.append(" - with flush");break;
                case 7: stringBuilder.append(" - with full house");break;
                case 8: stringBuilder.append(" - with four of a kind");break;
                case 9: stringBuilder.append(" - with straight flush");break;
            }
        }
        return stringBuilder.toString();
    }
}
