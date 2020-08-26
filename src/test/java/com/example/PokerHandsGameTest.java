package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PokerHandsGameTest {

    static PokerHandsGame game;

    @BeforeAll
    static void init() {
        game = new PokerHandsGame();
    }

    @Test
    public void should_return_10H_11D_12S_13C_14D_when_translate_given_player1_TH_JD_QS_KC_AD() {
        // given
        Card  card1 = new Card(10, "H");
        Card  card2 = new Card(11, "D");
        Card  card3 = new Card(12, "S");
        Card  card4 = new Card(13, "C");
        Card  card5 = new Card(14, "D");
        List<Card> expectedCards = asList(card1, card2,card3,card4, card5);
        // when
        List<Card>  cards =  game.translate("TH JD QS KC AD");
        // then
        assertEquals(expectedCards.get(0).toString(), cards.get(0).toString());
        assertEquals(expectedCards.get(1).toString(), cards.get(1).toString());
        assertEquals(expectedCards.get(2).toString(), cards.get(2).toString());
        assertEquals(expectedCards.get(3).toString(), cards.get(3).toString());
        assertEquals(expectedCards.get(4).toString(), cards.get(4).toString());
    }

    @Test
    public void should_return_high_card_when_judge_card_given_2H_3D_5S_9C_KD() {
        // given
        Card  card1 = new Card(2, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(9, "C");
        Card  card5 = new Card(13, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(1.13,judgeNumber);
    }

    @Test
    public void should_return_pair_when_judge_card_given_3H_3D_5S_9C_KD() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(9, "C");
        Card  card5 = new Card(13, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(2.0313,judgeNumber);
    }

    @Test
    public void should_return_two_pairs_when_judge_card_given_3H_3D_5S_9C_5D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(5, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(9, "C");
        Card  card5 = new Card(9, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(3.090503,judgeNumber);
    }

    @Test
    public void should_return_three_of_a_kind_when_judge_card_given_3H_3D_5S_9C_3D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(9, "C");
        Card  card5 = new Card(3, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(4.0309,judgeNumber);
    }

    @Test
    public void should_return_straight_when_judge_card_given_3H_4D_5S_6C_7D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(4, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(6, "C");
        Card  card5 = new Card(7, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(5.07,judgeNumber);
    }

    @Test
    public void should_return_flush_when_judge_card_given_2H_3H_5H_9H_KH() {
        // given
        Card  card1 = new Card(2, "H");
        Card  card2 = new Card(3, "H");
        Card  card3 = new Card(5, "H");
        Card  card4 = new Card(9, "H");
        Card  card5 = new Card(13, "H");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(6.13,judgeNumber);
    }

    @Test
    public void should_return_full_home_when_judge_card_given_3H_3D_5S_5C_3D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(5, "C");
        Card  card5 = new Card(3, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(7.03,judgeNumber);
    }

    @Test
    public void should_return_four_of_a_kind_when_judge_card_given_3H_3D_3S_5C_3D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(3, "S");
        Card  card4 = new Card(5, "C");
        Card  card5 = new Card(3, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(8.03,judgeNumber);
    }

    @Test
    public void should_return_straight_flush_when_judge_card_given_3H_4H_5H_6H_7H() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(4, "H");
        Card  card3 = new Card(5, "H");
        Card  card4 = new Card(6, "H");
        Card  card5 = new Card(7, "H");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        double judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(9.07,judgeNumber);
    }
}
