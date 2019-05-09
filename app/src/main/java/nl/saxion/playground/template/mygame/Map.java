package nl.saxion.playground.template.mygame;

import android.graphics.Bitmap;

import java.util.Random;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;


public class Map extends Entity {

    // Size of the level in tiles.
    private static final int width = 20;
    private static final int height = 20;

    // For each tile, an integer indexing `spriteResourceIds`. 0 means nothing is shown.
    private int[][] tiles = new int[width][height];

    // The list of resource ids to draw each tile type with.
    static private final int[] spriteResourceIds = {0,
            R.drawable.my_ground,
            R.drawable.my_path,
            R.drawable.my_path_left,
            R.drawable.my_path_right,
            R.drawable.my_path_down,
            R.drawable.my_path_up};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    private MyGame game;

    Map(MyGame game) {
        this.game = game;
        for (int x = 0; x < width ; x++) {
            for (int y = 0; y < height; y++) {
                Random r=new Random();
                int randomNumber=r.nextInt(spriteResourceIds.length);
                tiles[x][y] = 1;
            }
        }
        path(3,4,2,0, true);
        path(6, 3, 5, 4,false );
        if (spriteBitmaps ==null) spriteBitmaps = new Bitmap[spriteResourceIds.length];
    }

    public void path(int width, int height, int xStart, int yStart, boolean up){
        for (int x = xStart; x < xStart+width; x++) {
            for (int y = yStart; y < yStart+height; y++) {
                if (up){
                    if (x == xStart){
                        tiles[x][y] = 3;
                    } else if (x == xStart + width -1){
                        tiles[x][y] = 4;
                    } else {
                        tiles[x][y] = 2;
                    }
                } else {
                    if (y == yStart){
                        tiles[x][y] = 6;
                    } else if (y == yStart + height -1){
                        tiles[x][y] = 5;
                    } else {
                        tiles[x][y] = 2;
                    }
                }

            }
        }
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
