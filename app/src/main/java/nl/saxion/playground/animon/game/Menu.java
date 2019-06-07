package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Menu extends Entity implements KeyListener {

    private boolean ismenuactive;
    private Game game;
    private Bitmap bitmapBackground, bitmapSelector;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private int currentSelector = 0;

    public Menu(Game game) {
        this.game = game;
        this.game.getEntity(KeyEntity.class).addKeyListener(this);
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        if (bitmapBackground == null || bitmapSelector == null) {
            Log.d("paint", "bitmap init");
            bitmapBackground = gv.getBitmapFromResource(R.drawable.onscreen_menu_background);
            bitmapSelector = gv.getBitmapFromResource(R.drawable.menu_select_arrow);
        }

        if (ismenuactive) {
            Log.d("paint", "painting menu");
            gv.drawBitmap(bitmapBackground, game.getWidth() / 2, 0.5f, (game.getWidth() / 2) - 0.5f, 10);
            gv.drawBitmap(bitmapSelector, game.getWidth() * 0.55f, 0.5f + ((currentSelector + 1) * 2), 0.5f, 0.5f);
        }
    }

    public boolean isIsmenuactive() {
        return ismenuactive;
    }

    public void addMenuItem(MenuItem item) {
        this.menuItems.add(item);
    }

    @Override
    public void onUpKey() {
        if (currentSelector > 0){
            currentSelector--;
        }
    }

    @Override
    public void onDownKey() {
        if (currentSelector < menuItems.size()-1){
            currentSelector++;
        }
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
        if (!ismenuactive) {
            ismenuactive = true;
        } else {
            ismenuactive = false;
        }
    }
}

