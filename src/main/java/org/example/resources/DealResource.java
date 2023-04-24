package org.example.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.api.Cards;

import java.util.ArrayList;

@Path("/deal")
@Produces(MediaType.APPLICATION_JSON)
public class DealResource {
    public DealResource() {}

    @GET
    public Cards deal() {
        ArrayList<String> deck = new ArrayList<>();
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        double money = 100;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 13; i++) {
                switch (i) {
                    case 0:
                        deck.add("A");
                        break;

                    case 10:
                        deck.add("J");
                        break;

                    case 11:
                        deck.add("Q");
                        break;

                    case 12:
                        deck.add("K");
                        break;

                    default:
                        deck.add(Integer.toString(i + 1));
                        break;
                }
            }
        }

        dealCard(deck, playerHand);
        dealCard(deck, dealerHand);
        dealCard(deck, playerHand);
        dealCard(deck, dealerHand);

        return new Cards(String.join(",", deck), String.join(",", playerHand), String.join(",", dealerHand), money);
    }

    public void dealCard(ArrayList<String> deck, ArrayList<String> hand) {
        String card = deck.remove((int) (Math.random() * deck.size()));
        hand.add(card);
    }
}
