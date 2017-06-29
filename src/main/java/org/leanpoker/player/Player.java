package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
//    	JsonArray asJsonArray = request.getAsJsonArray();
//    	JsonElement jsonElement = asJsonArray.get(0);
    	JsonObject asJsonObject = request.getAsJsonObject();
    	JsonElement playersElement = asJsonObject.get("players");
    	JsonArray asJsonArray = playersElement.getAsJsonArray();
    	
    	int myBet  = 0;
    	
    	for (int i = 0; i < asJsonArray.size(); i++) {
    		JsonObject jsonElement = asJsonArray.get(i).getAsJsonObject();
    		BigInteger bet = jsonElement.get("bet").getAsBigInteger();
    		myBet = Math.max(myBet, bet.intValue());
		}
    	myBet++;
//    	JsonElement jsonElement = playersElement.getAsJsonObject().get("bet");
//    	System.out.println(playersElement);
//    	jsonElement.get
        return myBet;
    }

    public static void showdown(JsonElement game) {
    }
}
