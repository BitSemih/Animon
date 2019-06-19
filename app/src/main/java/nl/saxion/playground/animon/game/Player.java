package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.animons.Animon;
import nl.saxion.playground.animon.animons.*;

public class Player extends Entity implements KeyListener {

    private static final String TAG = "PlayerEntity";

    private float x;
    private float y;
    private Collision collision;

    private int rightBoundary = 0;
    private int leftBoundary = 0;
    private int upperBoundary = 0;
    private int downBoundary = 0;

    static private final int[] spriteResourceIds = {0, R.drawable.s_player};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    private Game game;

    private ArrayList<Animon> animonArrayList = new ArrayList<>();

    public Player(Game game, Collision collision) {
        //TODO check if the player already has an instance
        spriteBitmaps = new Bitmap[spriteResourceIds.length];

        this.game = game;
        this.collision = collision;
        this.game.getEntity(KeyEntity.class).addKeyListener(this);

        x = 7;
        y = (int) (game.getHeight()/2) + 1;

        rightBoundary = Map.getWidth() - 7;
        leftBoundary = 7;
        upperBoundary = (int) (game.getHeight()/2) + 1;
        downBoundary = Map.getHeight() - (int) (game.getHeight()/2);

        if (animonArrayList.size() < 1) {
            animonArrayList.add(new Chicken(this.game, "Chicken wing", 50, 5, 0));
        }
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);
        if (state == 0){
            //Draw the player sprite
            spriteBitmaps[1] = gv.getBitmapFromResource(spriteResourceIds[1]);
            gv.drawBitmap(spriteBitmaps[1], 7,(int)(game.getHeight()/2) + 1, 1, 1);
        }
    }

    @Override
    public void onRightKey() {
        if (x == rightBoundary){
            return;
        }

        if (!collision.checkForCollision(this.x+0.4f, this.y, 0)){
            x+=0.2;
        }
    }

    @Override
    public void onLeftKey() {
        if (x==leftBoundary){
            return;
        }

        if (!collision.checkForCollision(this.x-0.4f, this.y,0)){
            x-=0.2;
        }
    }

    @Override
    public void onUpKey() {
        if (y==upperBoundary){
            return;
        }

        if (!collision.checkForCollision(this.x, this.y-0.2f, 1)){
            y-=0.2;
        }
    }

    @Override
    public void onDownKey() {
        if (x==downBoundary){
            return;
        }

        if (!collision.checkForCollision(this.x, this.y+1.1f, 1)){
            y+=0.2;
        }
    }

    @Override
    public void onMenuKey() {
        //nothing
    }

    @Override
    public void onAKey() {
        //nothing
    }

    @Override
    public void onBKey() {
        //nothing
    }

    @Override
    public void onMenuDownKey() {
        //nothing
    }

    @Override
    public void onMenuUpKey() {
        //nothing
    }

    /**
     *
     * @param x x to check
     * @param y y to check
     * @return true if there is a collision, false when there is not
     */
    private boolean checkCollisions(float x, float y){
        //return this.game.getEntity(Tiles.class).returnTile(x, y, 2) != 0;
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
