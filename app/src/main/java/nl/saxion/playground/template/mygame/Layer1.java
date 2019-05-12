package nl.saxion.playground.template.mygame;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Layer1 extends Entity {

    // Size of the level in tiles.
    private static final int width = 27;
    private static final int height = 22;

    private String jsonString = "[{\"data\":[0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 22, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 32, 28, 0, 29, 32, 32, 32, 32, 32, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 23, 20, 20, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 23, 20, 20, 20, 20, 20, 20, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 29, 32, 32, 32, 32, 32, 32, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 32, 32, 32, 32, 33, 37, 38, 38, 38, 38, 39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 44, 44, 44, 44, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 44, 44, 44, 44, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 50, 50, 50, 50, 51, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]}]";

    // For each tile, an integer indexing `spriteResourceIds`. 0 means nothing is shown.
    private int[][] tiles = new int[width][height];

    // The list of resource ids to draw each tile type with.
    static private final int[] spriteResourceIds = {0,
            R.drawable.my_path_up,  //1
            R.drawable.my_path_down, //2
            R.drawable.my_path_right, //3
            R.drawable.my_path_left}; //4

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    private MyGame game;

    Layer1(MyGame game) {
        this.game = game;

        try {
            JSONArray jr = new JSONArray(jsonString);
            JSONObject jb = (JSONObject)jr.getJSONObject(0);
            JSONArray st = jb.getJSONArray("data");

            int x = 0;
            int y = 0;
            for (int i = 0; i < st.length(); i++) {
                int number = st.getInt(i);
                if (number == 20){
                    tiles[x][y] = 1;
                } else if (number == 32){
                    tiles[x][y] = 2;
                } else if (number == 27){
                    tiles[x][y] = 3;
                } else if (number == 25){
                    tiles[x][y] = 4;
                } else {
                    tiles[x][y] = 0;
                }
                x++;

                if (x == width){
                    x = 0;
                    y++;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        if (spriteBitmaps ==null) spriteBitmaps = new Bitmap[spriteResourceIds.length];
    }

    @Override
    public void draw(GameView gv) {
        // Calculate which tiles are visible at the current scroll position.
        int endX = Math.min(1+(int)game.getWidth(), width);
        int endY = Math.min((int)Math.ceil(game.getHeight()), height);

        // Draw any visible tiles.
        for(int x = 0; x < endX; x++) {
            for (int y = 0; y < endY; y++) {
                int tile = tiles[x][y];
                if (tile == 0) continue;
                if (spriteBitmaps[tile] == null) {
                    // Load/decode bitmaps before we first draw them.
                    spriteBitmaps[tile] = gv.getBitmapFromResource(spriteResourceIds[tile]);
                }
                gv.drawBitmap(spriteBitmaps[tile], x*32,y*32, 32, 32);
            }
        }
    }
}