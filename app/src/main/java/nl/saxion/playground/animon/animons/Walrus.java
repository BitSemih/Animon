package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Walrus extends Animon {
    public Walrus(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Bubble gun", 4, 1));
        this.attackMoves.add(new AttackMove("Tail Slap", 5, 0.9));
        this.attackMoves.add(new AttackMove("Water board", 9, 0.35));
        this.attackMoves.add(new AttackMove("Bite", 6, 0.8));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        bitmap = gv.getBitmapFromResource(R.drawable.a_walrus);
        gv.drawBitmap(bitmap, 3,3,3,3);
    }
}