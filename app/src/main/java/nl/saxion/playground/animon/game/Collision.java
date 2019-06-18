package nl.saxion.playground.animon.game;

import nl.saxion.playground.animon._lib.Entity;

public class Collision extends Entity {
    private int[][] collisionTiles = new int[512][512];

    public Collision(){
    }

    public void addCollisionTiles(int x, int y, int number){
        this.collisionTiles[x][y] = number;
    }

    public boolean checkForCollision(float x, float y, int action){
        int X = Math.round(x);
        int Y = Math.round(y);
        int midY = Math.round(y+0.5f);
        int botY = Math.round(y+1f);
        if (action == 0){
            //left and right
            if (this.collisionTiles[X][Y] != 0 || this.collisionTiles[X][midY] != 0 || this.collisionTiles[X][botY] != 0){
                return true;
            }
        } else {
            //up and down
            if (this.collisionTiles[X][Y] != 0){
                return true;
            }
        }
        return false;
    }
}
