package com.example;

public class Card {
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
}
