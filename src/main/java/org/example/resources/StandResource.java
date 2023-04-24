package org.example.resources;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.api.Cards;
import org.example.api.Response;

@Path("/stand")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StandResource {
    public StandResource() {}

    @POST
    public Response stand(@NotNull @Valid Cards cards) {
        String[] deck = cards.getDeck().split(",");
        String[] playerHand = cards.getPlayerHand().split(",");
        String[] dealerHand = cards.getDealerHand().split(",");
        double money = cards.getMoney();
        String status;

        int dealerHandValue = 0;
        int aceCount = 0;

        for (String s : dealerHand) {
            if (s.equals("A")) {
                aceCount++;

            } else if (s.equals("J") || s.equals("Q") || s.equals("K")) {
                dealerHandValue += 10;

            } else {
                dealerHandValue += Integer.parseInt(s);
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (dealerHandValue + 11 <= 21) {
                dealerHandValue += 11;

            } else {
                dealerHandValue += 1;
            }
        }

        while (dealerHandValue < 17) {
            int random = (int) (Math.random() * deck.length);
            String card = deck[random];

            String[] newDeck = new String[deck.length - 1];
            String[] newDealerHand = new String[dealerHand.length + 1];

            int j = 0;

            for (int i = 0; i < deck.length; i++) {
                if (i != random) {
                    newDeck[j] = deck[i];
                    j++;
                }
            }

            System.arraycopy(dealerHand, 0, newDealerHand, 0, dealerHand.length);
            newDealerHand[dealerHand.length] = card;

            dealerHand = newDealerHand;
            deck = newDeck;

            dealerHandValue = 0;
            aceCount = 0;

            for (String s : dealerHand) {
                if (s.equals("A")) {
                    aceCount++;

                } else if (s.equals("J") || s.equals("Q") || s.equals("K")) {
                    dealerHandValue += 10;

                } else {
                    dealerHandValue += Integer.parseInt(s);
                }
            }

            for (int i = 0; i < aceCount; i++) {
                if (dealerHandValue + 11 <= 21) {
                    dealerHandValue += 11;

                } else {
                    dealerHandValue += 1;
                }
            }
        }

        int playerHandValue = 0;
        aceCount = 0;

        for (String s : playerHand) {
            if (s.equals("A")) {
                aceCount++;

            } else if (s.equals("J") || s.equals("Q") || s.equals("K")) {
                playerHandValue += 10;

            } else {
                playerHandValue += Integer.parseInt(s);
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (playerHandValue + 11 <= 21) {
                playerHandValue += 11;

            } else {
                playerHandValue += 1;
            }
        }

        if (playerHandValue > 21) {
            status = "bust";
            money -= 10;

        } else if (dealerHandValue > 21) {
            status = "win";
            money += 10;

        } else if (playerHandValue > dealerHandValue) {
            status = "win";
            money += 10;

        } else if (playerHandValue < dealerHandValue) {
            status = "lose";
            money -= 10;

        } else {
            status = "push";
        }

        return new Response(String.join(",", deck), String.join(",", playerHand), String.join(",", dealerHand), money, status);
    }
}
