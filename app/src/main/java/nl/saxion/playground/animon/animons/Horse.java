package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Horse extends Animon {
    public Horse(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Step on", 4, 0.75));
        this.attackMoves.add(new AttackMove("Bite", 6, 0.5));
        this.attackMoves.add(new AttackMove("Kick", 5, 0.8));
        this.attackMoves.add(new AttackMove("Bumb", 4, 0.9));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        if (super.bitmap == null) {
            super.bitmap = gv.getBitmapFromResource(R.drawable.a_horse);
        }
    }
}
