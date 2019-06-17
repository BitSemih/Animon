package nl.saxion.playground.animon.game;

import android.graphics.RectF;

import org.json.JSONArray;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Layer extends Entity {

    private int[][] tiles = null;

    // The game to draw in
    private Game game;

    private String name;

    private SpriteSheet map_sheet;

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

        try {
            int x = 0;
            int y = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                int number = jsonArray.getInt(i);
                tiles[x][y] = number;

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
        // Calculate which treesTiles are visible at the current scroll position.
        int startX = (int) (game.getEntity(Player.class).getX() - 7);
        int startY = (int) (game.getEntity(Player.class).getY() - (game.getHeight() / 2));
        int endX = startX + 18;
        int endY = (int) (startY + game.getHeight() + 1) + 1;

        int tile = 0;
        if (startX < 0) {
            startX = 0;
        }

        if (endX >= tiles.length) {
            endX = tiles.length - 1;
        }

        if (startY < 0) {
            startY = 0;
        }

        if (endY >= tiles.length) {
            endY = tiles.length - 1;
        }

        if (map_sheet == null) {
            map_sheet = SpriteSheet.createSheetFromTileSize(gv.getBitmapFromResource(R.drawable.tileset), 16, 16);
        }


        // Draw any visible tiles.
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Ground
                tile = tiles[x][y];
                if (tile != 0) {
                    map_sheet.drawFrame(tile - 1, gv.getCanvas(), new RectF(x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), (x - game.getEntity(Player.class).getX() + 7) + 1, (y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2))) + 1));
                }
            }
        }
    }
}


