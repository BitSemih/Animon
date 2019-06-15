package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.animons.Animon;

public class Battle extends Entity {
    private Animon playerAnimon, npcAnimon;
    private int turn, background, battlePlatform;
    private Bitmap backgroundBitmap, platformBitmap;
    private Game game;

    public Battle(int background, Game game, int battlePlatform) {
        this.game = game;
        this.battlePlatform = battlePlatform;
        this.background = background;
    }

    public void startBattle(Animon playerAnimon, Animon npcAnimon){

    }

    public void endBattle(){

    }

    @Override
    public void draw(GameView gv) {
        if (state == 1){
            if (backgroundBitmap == null || platformBitmap == null){
                backgroundBitmap = gv.getBitmapFromResource(background);
                platformBitmap = gv.getBitmapFromResource(battlePlatform);
            }
            gv.drawBitmap(backgroundBitmap, 0, 0, game.getWidth(), game.getHeight());
            gv.drawBitmap(platformBitmap, 4, 5, 4, 1);
        }
    }
}
