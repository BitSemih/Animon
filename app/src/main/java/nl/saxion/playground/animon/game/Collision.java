package nl.saxion.playground.animon.game;

import nl.saxion.playground.animon._lib.Entity;

public class Collision extends Entity {
    private int[][] collisionTiles = new int[256][256], waterTiles = new int[256][256], animonTiles = new int[256][256];

    public Collision() {
    }

    public void addCollisionTiles(int x, int y, int number) {
        this.collisionTiles[x][y] = number;
    }

    public void addWaterTiles(int x, int y, int number){
        this.waterTiles[x][y] = number;
    }

    public void addAnimonTiles(int x, int y, int number) {
        this.animonTiles[x][y] = number;
    }

    public boolean checkForCollision(float x, float y, int action) {
        int X = Math.round(x);
        int Y = Math.round(y+0.2f);
        int midY = Math.round(y + 0.5f);
        int botY = Math.round(y + 1f);

        if (action == 0) {
            //left and right
            if (this.collisionTiles[X][Y] != 0 || this.collisionTiles[X][midY] != 0 || this.collisionTiles[X][botY] != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            //up and down
            if (this.collisionTiles[X][Y] != 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkForAnimons(float x, float y, int action){
        int X = Math.round(x);
        int Y = Math.round(y+0.2f);
        int midY = Math.round(y + 0.5f);
        int botY = Math.round(y + 1f);

        if (action == 0) {
            //left and right
            return this.animonTiles[X][Y] != 0 || this.animonTiles[X][midY] != 0 || this.animonTiles[X][botY] != 0;
        } else {
            //up and down
            return this.animonTiles[X][Y] != 0;
        }
    }

    public boolean checkForWater(int x, int y){
        if (this.waterTiles[x][y] != 0){
            return true;
        }
        return false;
    }
}
