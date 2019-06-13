package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;

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

    private Game game;

    private int rightBoundary = 0;
    private int leftBoundary = 0;
    private int upperBoundary = 0;
    private int downBoundary = 0;
    private float playerOffsetX;
    private float playerOffsetY;
    private float cameraCenterY;
    private String walkDirection = "right";
    private int animationCount = 1;
    private boolean walking = false;

    static private final int[] spriteResourceIds = {0, R.drawable.s_player, R.drawable.s_player_down, R.drawable.s_player_up, R.drawable.s_player_walk0, R.drawable.s_player_walk1, R.drawable.s_player_up_walk0, R.drawable.s_player_up_walk1, R.drawable.s_player_down_walk0, R.drawable.s_player_down_walk1};

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap bitmapFaceRight, bitmapWalkRight1, bitmapWalkRight2, bitmapFaceLeft, bitmapWalkLeft1, bitmapWalkLeft2, bitmapFaceUp, bitmapFaceDown, bitmapWalkUp1, bitmapWalkUp2, bitmapWalkDown1, bitmapWalkDown2;

    private ArrayList<Animon> animonArrayList = new ArrayList<>();

    public Player(Game game, Collision collision) {

        this.game = game;
        this.collision = collision;
        this.game.getEntity(KeyEntity.class).addKeyListener(this);
        this.playerOffsetX = 7;
        this.playerOffsetY = (game.getHeight() / 2) + 1;
        x = 14;
        y = (int) (game.getHeight() / 2) + 1;

        cameraCenterY = (int) playerOffsetY;

        rightBoundary = Map.getWidth() - 10;
        leftBoundary = 7;
        upperBoundary = (int) (game.getHeight() / 2) + 1;
        downBoundary = Map.getHeight() - (int) (game.getHeight() / 2);

        if (animonArrayList.size() < 1) {
            animonArrayList.add(new Chicken(this.game, "Chicken wing", 50, 5, 0));
        }
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        //Draw the player sprite
        if (bitmapFaceRight == null) {
            Matrix matrix = new Matrix();
            matrix.preScale(-1.0f, 1.0f);

            //Players stands still
            bitmapFaceRight = gv.getBitmapFromResource(spriteResourceIds[1]);
            bitmapFaceDown = gv.getBitmapFromResource(spriteResourceIds[2]);
            bitmapFaceUp = gv.getBitmapFromResource(spriteResourceIds[3]);
            bitmapFaceLeft = Bitmap.createBitmap(bitmapFaceRight, 0, 0, bitmapFaceRight.getWidth(), bitmapFaceRight.getHeight(), matrix, true);

            //Walk right animation
            bitmapWalkRight1 = gv.getBitmapFromResource(spriteResourceIds[4]);
            bitmapWalkRight2 = gv.getBitmapFromResource(spriteResourceIds[5]);

            //Walk left animation
            bitmapWalkLeft1 = Bitmap.createBitmap(bitmapWalkRight1, 0, 0, bitmapFaceRight.getWidth(), bitmapFaceRight.getHeight(), matrix, true);
            bitmapWalkLeft2 = Bitmap.createBitmap(bitmapWalkRight2, 0, 0, bitmapFaceRight.getWidth(), bitmapFaceRight.getHeight(), matrix, true);

            //Walk up animation
            bitmapWalkUp1 = gv.getBitmapFromResource(spriteResourceIds[6]);
            bitmapWalkUp2 = gv.getBitmapFromResource(spriteResourceIds[7]);

            //Walk down animation
            bitmapWalkDown1 = gv.getBitmapFromResource(spriteResourceIds[8]);
            bitmapWalkDown2 = gv.getBitmapFromResource(spriteResourceIds[9]);
        }

        //Look which direction the player is walking
        switch (walkDirection) {
            case "left":
                if (!walking) {
                    //Standing still
                    gv.drawBitmap(bitmapFaceLeft, playerOffsetX, playerOffsetY, 1, 1);
                } else {
                    //Walk animation
                    if (animationCount == 1) {
                        gv.drawBitmap(bitmapWalkLeft1, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount++;
                    } else {
                        gv.drawBitmap(bitmapWalkLeft2, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount--;
                    }
                }
                break;
            case "right":
                if (!walking) {
                    //Standing still
                    gv.drawBitmap(bitmapFaceRight, playerOffsetX, playerOffsetY, 1, 1);
                } else {
                    //Walk animation
                    if (animationCount == 1) {
                        gv.drawBitmap(bitmapWalkRight1, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount++;
                    } else {
                        gv.drawBitmap(bitmapWalkRight2, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount--;
                    }
                }
                break;
            case "up":
                if (!walking) {
                    //Standing still
                    gv.drawBitmap(bitmapFaceUp, playerOffsetX, playerOffsetY, 1, 1);
                } else {
                    //Walk animation
                    if (animationCount == 1) {
                        gv.drawBitmap(bitmapWalkUp1, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount++;
                    } else {
                        gv.drawBitmap(bitmapWalkUp2, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount--;
                    }
                }
                break;
            case "down":
                if (!walking) {
                    //Standing still
                    gv.drawBitmap(bitmapFaceDown, playerOffsetX, playerOffsetY, 1, 1);
                } else {
                    //Walk animation
                    if (animationCount == 1) {
                        gv.drawBitmap(bitmapWalkDown1, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount++;
                    } else {
                        gv.drawBitmap(bitmapWalkDown2, playerOffsetX, playerOffsetY, 1, 1);
                        animationCount--;
                    }
                }
                break;
        }
        walking = false;
    }

    @Override
    public void onRightKey() {
        if (x > rightBoundary - 0.2f) {
            x = rightBoundary;
        }
        if (x >= rightBoundary || playerOffsetX < 7) {
            if (playerOffsetX < game.getWidth() - 1.2f) {
                playerOffsetX += 0.1f;
            }
        } else {
            if (playerOffsetX != 7) {
                playerOffsetX = 7;
            }
            if (!collision.checkForCollision(this.x + 0.3f, this.y, 0)) {
                x += 0.1f;
            }
        }
        this.walkDirection = "right";
        this.walking = true;
    }

    @Override
    public void onLeftKey() {
        if (playerOffsetX < 0.2f) {
            playerOffsetX = 0;
        }
        if (x <= leftBoundary || playerOffsetX > 7) {
            if (playerOffsetX > 0) {
                playerOffsetX -= 0.1f;
            }
        } else {
            if (playerOffsetX != 7) {
                playerOffsetX = 7;
            }
            if (!collision.checkForCollision(this.x - 0.2f, this.y, 0)) {
                x -= 0.1f;
            }
        }
        this.walkDirection = "left";
        this.walking = true;
    }

    @Override
    public void onUpKey() {
        if (playerOffsetY < 0.2f) {
            playerOffsetY = 0;
        }

        if (y <= upperBoundary || playerOffsetY > cameraCenterY) {
            if (playerOffsetY > 0) {
                playerOffsetY -= 0.1f;
            }
        } else {
            if (playerOffsetY != cameraCenterY) {
                playerOffsetY = cameraCenterY;
            }
            if (!collision.checkForCollision(this.x, this.y - 0.2f, 1)) {
                y -= 0.1f;
            }
            this.walkDirection = "up";
            this.walking = true;
        }
    }

    @Override
    public void onDownKey() {
        if (playerOffsetY > game.getHeight() - 0.2f) {
            playerOffsetY = game.getHeight();
        }

        if (y >= downBoundary || playerOffsetY < cameraCenterY) {
            if (playerOffsetY < game.getHeight() - 1.2f) {
                playerOffsetY += 0.1f;
            }
        } else {
            if (playerOffsetY != cameraCenterY) {
                playerOffsetY = cameraCenterY;
            }
            if (!collision.checkForCollision(this.x, this.y + 1.1f, 1)) {
                y += 0.1f;
            }
            this.walkDirection = "down";
            this.walking = true;
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
