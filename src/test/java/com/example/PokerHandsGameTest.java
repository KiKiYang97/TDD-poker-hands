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
        int judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(1,judgeNumber);
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
        int judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(2,judgeNumber);
    }

    @Test
    public void should_return_two_pairs_when_judge_card_given_3H_3D_5S_9C_5D() {
        // given
        Card  card1 = new Card(3, "H");
        Card  card2 = new Card(3, "D");
        Card  card3 = new Card(5, "S");
        Card  card4 = new Card(9, "C");
        Card  card5 = new Card(5, "D");
        List<Card> cards = asList(card1, card2,card3,card4, card5);
        // when
        int judgeNumber = game.judgeCard(cards);
        // then
        assertEquals(3,judgeNumber);
    }
}
