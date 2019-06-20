package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Snake extends Animon {
    public Snake(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Poison", 4, 1));
        this.attackMoves.add(new AttackMove("Strangle", 7, 0.6));
        this.attackMoves.add(new AttackMove("Tail Slap", 5, 0.9));
        this.attackMoves.add(new AttackMove("Bite", 4, 1));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        if (super.bitmap == null) {
            super.bitmap = gv.getBitmapFromResource(R.drawable.a_snake);
        }
    }
}
