package com.example;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    private Integer number;
    private String type;

    public Card() {
    }

    public Card(Integer number, String type) {
        this.number = number;
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Card card2) {
        if (getNumber()<card2.getNumber()) {
            return -1;
        } else if (getNumber() > card2.getNumber()) {
            return 1;
        }
        return 0;
    }
}
