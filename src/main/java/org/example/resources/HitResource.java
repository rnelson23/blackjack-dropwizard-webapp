package org.example.resources;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.api.Cards;
import org.example.api.Response;

@Path("/hit")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HitResource {
    public HitResource() {}

    @POST
    public Response hit(@NotNull @Valid Cards cards) {
        String[] deck = cards.getDeck().split(",");
        String[] playerHand = cards.getPlayerHand().split(",");
        String[] dealerHand = cards.getDealerHand().split(",");
        double money = cards.getMoney();
        String status = "hit";

        int random = (int) (Math.random() * deck.length);
        String card = deck[random];

        String[] newDeck = new String[deck.length - 1];
        String[] newPlayerHand = new String[playerHand.length + 1];

        int j = 0;

        for (int i = 0; i < deck.length; i++) {
            if (i != random) {
                newDeck[j] = deck[i];
                j++;
            }
        }

        System.arraycopy(playerHand, 0, newPlayerHand, 0, playerHand.length);
        newPlayerHand[playerHand.length] = card;

        int aceCount = 0;
        int playerHandValue = 0;

        for (String s : newPlayerHand) {
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
        }

        return new Response(String.join(",", newDeck), String.join(",", newPlayerHand), String.join(",", dealerHand), money, status);
    }
}
