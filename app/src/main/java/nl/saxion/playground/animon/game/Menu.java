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
            bitmap = gv.getBitmapFromResource(R.drawable.onscreen_menu_background);
        }

        if (ismenuactive) {
            Log.d("paint", "painting menu");
            gv.drawBitmap(bitmap, game.getWidth()/2, 0.5f, (game.getWidth()/2)-0.5f, 10);
        }
    }

    public boolean isIsmenuactive() {
        return ismenuactive;
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
        if (!ismenuactive){
            ismenuactive = true;
        } else {
            ismenuactive = false;
        }
    }
}

