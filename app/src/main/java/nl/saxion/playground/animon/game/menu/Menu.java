package nl.saxion.playground.animon.game.menu;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;
import nl.saxion.playground.animon.game.KeyEntity;
import nl.saxion.playground.animon.game.KeyListener;

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

    public int getCurrentSelector() {
        return currentSelector;
    }

    @Override
    public void onMenuUpKey() {
        //When menu is active and the arrow is not trying to get a index thats under 0
        if (ismenuactive && currentSelector > 0){
            currentSelector--;
        }
    }

    @Override
    public void onMenuDownKey() {
        //When menu is active and the arrow is not above the array size of the menu items
        if (ismenuactive && currentSelector < menuItems.size()-1){
            currentSelector++;
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
        int menuOptionId = game.getEntity(Menu.class).getCurrentSelector();
        switch(menuOptionId){
            case 0:
                game.getEntity(SaveGame.class);
                System.out.println("SAVE GAME");
                break;
            case 1:
                game.getEntity(LoadGame.class);
                System.out.println("LOAD GAME");
                break;
            case 2:
                game.getEntity(Inventory.class);
                System.out.println("INVENTORY");
                break;
            case 3:
                game.getEntity(Help.class);
                System.out.println("HELP");
                break;
        }
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

