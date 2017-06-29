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
    	if (leave)
    	{
    		return -1;
    	}
    	JsonObject asJsonObject = request.getAsJsonObject();
    	JsonElement playersElement = asJsonObject.get("players");
    	JsonElement pot = asJsonObject.get("players");
    	JsonArray asJsonArray = playersElement.getAsJsonArray();
    	
    	int myBet  = 0;
    	
    	int mystack = 1000;
    	
    	for (int i = 0; i < asJsonArray.size(); i++) {
    		JsonObject jsonElement = asJsonArray.get(i).getAsJsonObject();
    		BigInteger bet = jsonElement.get("bet").getAsBigInteger();
    		String name = jsonElement.get("name").getAsString();
    		if (name.equals("IrishCoffee")) {
    			mystack = jsonElement.get("stack").getAsBigInteger().intValue();
    			continue;
    		}
    		myBet = Math.max(myBet, bet.intValue());
		}
    	myBet++;
    	myBet = Math.min(myBet, mystack);
        return myBet;
    }

    public static void showdown(JsonElement game) {
//    	game.
    }
}
