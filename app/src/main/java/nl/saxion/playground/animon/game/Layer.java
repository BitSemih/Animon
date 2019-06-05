package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Layer extends Entity {


    private int[][] tiles = null;

    // The game to draw in
    private Game game;

    private String name;

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    // The list of resource ids to draw each tile type with.
    static private final int[] spriteResourceIds = {0,
            R.drawable.s_0002,    //1
            R.drawable.s_0041,    //2
            R.drawable.s_0042,    //3
            R.drawable.s_0664,    //4
            R.drawable.s_0665,    //5
            R.drawable.s_0666,    //6
            R.drawable.s_0667,    //7
            R.drawable.s_0668,    //8
            R.drawable.s_0758,    //9
            R.drawable.s_0759,    //10
            R.drawable.s_0760,    //11
            R.drawable.s_0761,    //12
            R.drawable.s_0762,    //13
            R.drawable.s_0852,    //14
            R.drawable.s_0853,    //15
            R.drawable.s_0854,    //16
            R.drawable.s_0135     //17
    };

    public int[][] getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    public Layer(Game game, int width, int height, JSONArray jsonArray, String name, Collision collision) {
        this.game = game;
        this.tiles = new int[width][height];
        this.name = name;

        if (spriteBitmaps == null) spriteBitmaps = new Bitmap[spriteResourceIds.length];

        try {
            int x = 0;
            int y = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                int number = jsonArray.getInt(i);
                if (number == 2) {
                    tiles[x][y] = 1;
                } else if (number == 41) {
                    tiles[x][y] = 2;
                } else if (number == 42) {
                    tiles[x][y] = 3;
                } else if (number == 664) {
                    tiles[x][y] = 4;
                } else if (number == 665) {
                    tiles[x][y] = 5;
                } else if (number == 666) {
                    tiles[x][y] = 6;
                } else if (number == 667) {
                    tiles[x][y] = 7;
                } else if (number == 668) {
                    tiles[x][y] = 8;
                } else if (number == 758) {
                    tiles[x][y] = 9;
                } else if (number == 759) {
                    tiles[x][y] = 10;
                } else if (number == 760) {
                    tiles[x][y] = 11;
                } else if (number == 761) {
                    tiles[x][y] = 12;
                } else if (number == 762) {
                    tiles[x][y] = 13;
                } else if (number == 852) {
                    tiles[x][y] = 14;
                } else if (number == 853) {
                    tiles[x][y] = 15;
                } else if (number == 854) {
                    tiles[x][y] = 16;
                } else if (number == 135) {
                    tiles[x][y] = 17;
                } else {
                    tiles[x][y] = 0;
                }

                if (name.equals("Trees") && number != 0 || name.equals("Buildings") && number != 0) {
                    collision.addCollisionTiles(x, y, number);
                }

                x++;

                if (x == width) {
                    x = 0;
                    y++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GameView gv) {
        //Check if state = 0 so draw the map
        if (state == 0) {
            // Calculate which treesTiles are visible at the current scroll position.
            // float offset = game.getEntity(Movement.class).z;
            int startX = (int) (game.getEntity(Player.class).getX() - 7);
            int startY = (int) (game.getEntity(Player.class).getY() - (game.getHeight() / 2));
            int endX = startX + 18;
            int endY = (int) (startY + game.getHeight() + 1) + 1;

            int tile = 0;
            // Draw any visible tiles.
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    // Ground
                    tile = tiles[x][y];
                    if (tile != 0) {
                        if (spriteBitmaps[tile] == null) {
                            // Load/decode bitmaps before we first draw them.
                            spriteBitmaps[tile] = gv.getBitmapFromResource(spriteResourceIds[tile]);
                        }
                        gv.drawBitmap(spriteBitmaps[tile], x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), 1, 1);
                    }
                }
            }
        }
    }

}
