package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.util.Log;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Menu extends Entity implements KeyListener {

    private boolean ismenuactive;
    private Game game;
    private Bitmap bitmap;

    public Menu(Game game) {
        this.game = game;
        this.game.getEntity(KeyEntity.class).addKeyListener(this);

    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        if (bitmap == null) {
            Log.d("paint", "bitmap init");
            bitmap = gv.getBitmapFromResource(R.drawable.animon_menu);
        }

        if (ismenuactive) {
            Log.d("paint", "painting menu");
            gv.drawBitmap(bitmap, 500, 505, gv.getWidth(), gv.getHeight());
        }
    }

    @Override
    public void onUpKey() {
    }

    @Override
    public void onDownKey() {

    }

    @Override
    public void onAKey() {

    }

    @Override
    public void onBKey() {

    }

    @Override
    public void onRightKey() {

    }

    @Override
    public void onLeftKey() {

    }

    @Override
    public void onMenuKey() {


        if (ismenuactive) {
            Log.d("menu","menu shuttin donw");
            ismenuactive = false;
        } else {
            Log.d("menu","menu activating");
            ismenuactive = true;
        }

    }
}

