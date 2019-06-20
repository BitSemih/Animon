package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Chicken extends Animon {
    public Chicken(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Pick", 6, 0.8));
        this.attackMoves.add(new AttackMove("Slap", 5, 0.95));
        this.attackMoves.add(new AttackMove("Poop", 6, 0.8));
        this.attackMoves.add(new AttackMove("Scratch", 4, 1));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        bitmap = gv.getBitmapFromResource(R.drawable.a_chicken);
        gv.drawBitmap(bitmap, 3,3,3,3);
    }


}
