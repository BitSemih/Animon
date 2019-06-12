package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Player extends Entity implements KeyListener {

    private static final String TAG = "PlayerEntity";

    private float x;
    private float y;
    private Collision collision;

    private Game game;

    private int rightBoundary = 0;
    private int leftBoundary = 0;
    private int upperBoundary = 0;
    private int downBoundary = 0;
    private float playerXPos;
    private float playerYPos;
    private float camYCenter;

    static private final int[] spriteResourceIds = {0, R.drawable.s_player};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    public Player(Game game, Collision collision) {
        //TODO check if the player already has an instance
        spriteBitmaps = new Bitmap[spriteResourceIds.length];

        this.game = game;
        this.collision = collision;
        this.game.getEntity(KeyEntity.class).addKeyListener(this);
        this.playerXPos = 7;
        this.playerYPos = (game.getHeight() / 2) + 1;
        x = 7;
        y = (int) (game.getHeight() / 2) + 1;

        camYCenter = (game.getHeight() / 2) + 1;

        rightBoundary = Map.getWidth() - 10;
        leftBoundary = 7;
        upperBoundary = (int) (game.getHeight() / 2) + 1;
        downBoundary = Map.getHeight() - (int) (game.getHeight() / 2) - 2;
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        //Draw the player sprite
        spriteBitmaps[1] = gv.getBitmapFromResource(spriteResourceIds[1]);
        //gv.drawBitmap(spriteBitmaps[1], 7,(int)(game.getHeight()/2) + 1, 1, 1);
        gv.drawBitmap(spriteBitmaps[1], playerXPos, playerYPos, 1, 1);
    }

    @Override
    public void onRightKey() {
        if (x > rightBoundary - 0.2f) {
            x = rightBoundary;
        }
        if (x >= rightBoundary || playerXPos < 7) {
            if (playerXPos < 14.8) {
                playerXPos += 0.2;
            }
        } else {
            if (!collision.checkForCollision(this.x + 0.4f, this.y, 0)) {
                x += 0.2;
            }
        }
    }

    @Override
    public void onLeftKey() {
        if (playerXPos < 0.2f) {
            playerXPos = 0;
        }
        if (x == leftBoundary || playerXPos > 7) {
            if (playerXPos > 0) {
                playerXPos -= 0.2;
            }
        } else {
            if (!collision.checkForCollision(this.x - 0.4f, this.y, 0)) {
                x -= 0.2;
            }
        }
    }

    @Override
    public void onUpKey() {
        if (playerYPos < 0.2f) {
            playerYPos = 0;
        }

        if (y == upperBoundary || playerYPos > camYCenter) {
            if (playerYPos > 0) {
                playerYPos -= 0.2;
            }
        } else {
            if (!collision.checkForCollision(this.x, this.y - 0.2f, 1)) {
                y -= 0.2;
            }
        }
    }

    @Override
    public void onDownKey() {
        if (playerYPos > game.getHeight() - 0.2f) {
            playerYPos = game.getHeight();
        }

        if (y >= downBoundary || playerYPos < camYCenter) {
            if (playerYPos < game.getHeight() - 1.2f) {
                playerYPos += 0.2;
            }
        } else {
            if (!collision.checkForCollision(this.x, this.y + 1.1f, 1)) {
                y += 0.2;
            }
        }
    }

    /**
     * @param x x to check
     * @param y y to check
     * @return true if there is a collision, false when there is not
     */
    private boolean checkCollisions(float x, float y) {
        //return this.game.getEntity(Tiles.class).returnTile(x, y, 2) != 0;
        return false;
    }

    private boolean checkBoundary() {
        if (y == downBoundary || y == upperBoundary || x == leftBoundary || x == rightBoundary) {
            return true;
        }
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
