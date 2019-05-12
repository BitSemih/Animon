package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Player extends Entity implements SwipeListener {

    private float x;
    private float y;

    private float vx = 0;
    private float vy = 0;

    static private final int[] aspriteResourceIds = {0};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] aspriteBitmaps;

    public Player(Animon game) {
        aspriteBitmaps = new Bitmap[aspriteResourceIds.length];

        game.getEntity(SwipeEntity.class).addSwipeListener(this);

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

        //make sure we see the whole view
        //gv.getCanvas().drawRect(0, 0, gv.getWidth(), gv.getHeight(), bgPaint);
        //aspriteBitmaps[1] = gv.getBitmapFromResource(aspriteResourceIds[1]);
       // gv.drawBitmap(aspriteBitmaps[1], 0,0, 32, 32);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        gv.getCanvas().drawRect(x, y, x+32, y+32, paint);
    }


//    @Override
//    public void tick() {
//        x += vx;
//        y += vy;
//
//        vx *= 0.9f;
//        vy *= 0.9f;
//    }
//
//
//    public void handleTouch(GameModel.Touch touch, MotionEvent event) {
//        vx += (touch.x - x)/100;
//        vy += (touch.y - y)/100;
//    }

    @Override
    public void onLeftSwipe() {
        x -= 32;
        //vx += (0 - x)/100;
    }

    @Override
    public void onRightSwipe() {
        x += 32;
        //vx += (0 - x)/100;
    }

    @Override
    public void onUpSwipe() {
        y -= 32;
    }

    @Override
    public void onDownSwipe() {
        y += 32;
    }
}
