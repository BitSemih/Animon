package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Mees extends Animon {
    public Mees(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Scrum", 6, 80));
        this.attackMoves.add(new AttackMove("Bite", 8, 75));
        this.attackMoves.add(new AttackMove("Scratch", 5, 87));
        this.attackMoves.add(new AttackMove("SAIYAN", 9999, 100));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);
        if (super.bitmap == null) {
            super.bitmap = gv.getBitmapFromResource(R.drawable.a_mees);
        }
    }
}
