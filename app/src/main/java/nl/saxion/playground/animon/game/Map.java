package nl.saxion.playground.animon.game;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.saxion.playground.animon._lib.Entity;

public class Map extends Entity {

    private static final int width = 256;
    private static final int height = 256;

    private static final String TAG = "Map";

    public Map(String jsonString, Game game) {
        try {
            JSONObject jsonObjectLayers = new JSONObject(jsonString);
            JSONArray jsonArrayLayers = jsonObjectLayers.getJSONArray("layers");

            for (int i = 0; i < jsonArrayLayers.length(); i++) {
                JSONObject jsonObjectData = jsonArrayLayers.getJSONObject(i);
                JSONArray jsonArrayData = jsonObjectData.getJSONArray("data");
                game.addEntity(new Layer(game, width, height, jsonArrayData));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

}
