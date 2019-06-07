package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import org.json.JSONArray;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Layer extends Entity {

    private int[][] tiles = null;

    // The game to draw in
    private Game game;

    private Bitmap spritesheet = null;

    private int columns = 94;
    private int rows = 150;
    private int length = 0;

    private int tileWidth = 16;
    private int tileHeight = 16;

    public Layer(Game game, int width, int height, JSONArray jsonArray) {
        this.game = game;
        this.tiles = new int[width][height];
        this.length = jsonArray.length();

        try {
            int x = 0;
            int y = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                int number = jsonArray.getInt(i);
                tiles[x][y] = number;
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
        //float offset = game.getEntity(Movement.class).z;
        int startX = (int) (game.getEntity(Player.class).getX() - 7);
        int startY = (int) (game.getEntity(Player.class).getY() - (game.getHeight() / 2));
        int endX = startX + 18;
        int endY = (int) (startY + game.getHeight() + 1) + 1;

        if (this.spritesheet == null){
            this.spritesheet = gv.getBitmapFromResource(R.drawable.tileset);
        }

        int tile = 0;
        // Draw any visible tiles.
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                tile = tiles[x][y];
                if (tile != 0) {
                    int row = tile / rows;
                    int column = tile % columns;

                    Rect src = new Rect (column * tileWidth,
                            row*tileHeight,
                            (column+1)*tileWidth,
                            (row+1) * tileHeight);
                    RectF dst= new RectF(x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), 1, 1);
                    gv.getCanvas().drawBitmap(spritesheet, src, dst, null);

                }
            }
        }

    }

}
