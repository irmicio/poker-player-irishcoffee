package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";
    
    static boolean leave = false;

    public static int betRequest(JsonElement request) {

    	JsonObject asJsonObject = request.getAsJsonObject();
    	JsonElement playersElement = asJsonObject.get("players");
    	int round = asJsonObject.get("round").getAsBigInteger().intValue();
    	int current_buy_in = asJsonObject.get("current_buy_in").getAsBigInteger().intValue();
    	JsonArray asJsonArray = playersElement.getAsJsonArray();

    	JsonArray comunityCards = asJsonObject.get("community_cards").getAsJsonArray();
    	
    	int myBet  = 0;
    	int myBetStep = 10;
    	int mystack = 1000;
    	
    	for (int i = 0; i < asJsonArray.size(); i++) {
    		JsonObject jsonElement = asJsonArray.get(i).getAsJsonObject();
    		String name = jsonElement.get("name").getAsString();
    		if (name.equals("IrishCoffee")) {
    			mystack = jsonElement.get("stack").getAsBigInteger().intValue();
        		JsonArray mycards = jsonElement.get("hole_cards").getAsJsonArray();
        		JsonObject card1 = mycards.get(0).getAsJsonObject();
        		JsonObject card2 = mycards.get(1).getAsJsonObject();
        		if (card1.get("rank").equals(card2.get("rank"))) {
        			return mystack;
        		} 
        		else if (card1.get("suit").equals(card2.get("suit"))) {
        			int found = 0;
        			for (int j = 0; j < comunityCards.size(); j++) {
        				if (card1.get("suit").equals(comunityCards.get(j).getAsJsonObject().get("suit").getAsString())){
        					found++;
        				}
					}
        			if (found == 3) {
            			return mystack;
        			}
        			if (found == 2 && round == 3) {
        				if (current_buy_in > mystack) {
        					return 0;
        				}
            			return current_buy_in;
        			} else if (found == 2 && round == 1) {
        				myBetStep = 100; 
        			}
        			else {
        				myBetStep = 50;
        			}
        		}
        		else {
        			return 0;
        		}
    			break;
    		}
		}
    	myBet = current_buy_in + myBetStep;
    	if (myBet >= mystack)
    	{
    		return 0;
    	}
        return myBet;
    }

    public static void showdown(JsonElement game) {
//    	game.
    }
}
