package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameModel;
import nl.saxion.playground.animon._lib.GameView;

public class OnScreenButtons extends Entity {
    private int imageId;
    private String direction;
    private Bitmap bitmap;
    private int x, y;
    private boolean touched;
    private float width, height;

    public OnScreenButtons(int imageId, String direction, int x, int y) {
        this.imageId = imageId;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.width = 2;
        this.height = 2;
    }

    @Override
    public void draw(GameView gv) {
        if (this.bitmap == null) {
            bitmap = gv.getBitmapFromResource(imageId);
        }
        gv.drawBitmap(bitmap, x, y, width, height);
    }

    public String getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean checkForTouch(GameModel.Touch touch) {
        if (touch.x >= (this.x - (this.width / 2)) && touch.x <= (this.x + (this.width / 2)) && touch.y >= (this.y - (this.height / 2)) && touch.y <= (this.y + (this.height / 2))) {
            return true;
        }
        return false;
    }
}
