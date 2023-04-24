package org.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private String deck;
    private String playerHand;
    private String dealerHand;
    private double money;
    private String status;

    public Response(String deck, String playerHand, String dealerHand, double money, String status) {
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.money = money;
        this.status = status;
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
    public String getStatus() {
        return status;
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

    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }
}
