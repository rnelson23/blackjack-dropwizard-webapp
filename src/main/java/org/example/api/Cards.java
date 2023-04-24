package org.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cards {
    private String deck;
    private String playerHand;
    private String dealerHand;
    private double money;

    public Cards() {
        // Jackson deserialization
    }

    public Cards(String deck, String playerHand, String dealerHand, double money) {
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.money = money;
    }

    @JsonProperty
    public String getDeck() {
        return deck;
    }

    @JsonProperty
    public String getPlayerHand() {
        return playerHand;
    }

    @JsonProperty
    public String getDealerHand() {
        return dealerHand;
    }

    @JsonProperty
    public double getMoney() {
        return money;
    }

    @JsonProperty
    public void setDeck(String deck) {
        this.deck = deck;
    }

    @JsonProperty
    public void setPlayerHand(String playerHand) {
        this.playerHand = playerHand;
    }

    @JsonProperty
    public void setDealerHand(String dealerHand) {
        this.dealerHand = dealerHand;
    }

    @JsonProperty
    public void setMoney(double money) {
        this.money = money;
    }
}
