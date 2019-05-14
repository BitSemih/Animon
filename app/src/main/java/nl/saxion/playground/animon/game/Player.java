package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Player extends Entity implements KeyListener {

    private static final String TAG = "Player";

    private int x;
    private int y;

    private float vx = 0;
    private float vy = 0;

    static private final int[] spriteResourceIds = {0, R.drawable.s_player};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    private Animon game;

    public Player(Animon game) {
        spriteBitmaps = new Bitmap[spriteResourceIds.length];

        this.game = game;

        this.game.getEntity(KeyEntity.class).addKeyListener(this);

        x = 0;
        y = 0;
    }

    @Override
    public int getLayer() {
        return 10;
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        //Draw the player sprite
        spriteBitmaps[1] = gv.getBitmapFromResource(spriteResourceIds[1]);
        gv.drawBitmap(spriteBitmaps[1], x,y, 1, 1);
    }

    @Override
    public void onRightKey() {
        x+=1;
    }

    @Override
    public void onLeftKey() {
        x-=1;
    }

    @Override
    public void onUpKey() {
        y-=1;
    }

    @Override
    public void onDownKey() {
        y+=1;
    }

//
//    @Override
//    public void onDownSwipe() {
//        Log.i(TAG, "x: " + x + " y: " + y);
//        if (this.game.getEntity(Path.class).returnTile(x, y + 1) == 0){
//            return;
//        } else {
//            y += 1;
//        }
//        Log.i(TAG, "Tile: " + this.game.getEntity(Path.class).returnTile(x/32, y/32));
//    }

}
