package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Buffalo extends Animon {
    public Buffalo(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Horn ram", 6, 0.8));
        this.attackMoves.add(new AttackMove("Trample", 8, 0.8));
        this.attackMoves.add(new AttackMove("Kick", 5, 0.8));
        this.attackMoves.add(new AttackMove("Poop", 6, 0.8));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        bitmap = gv.getBitmapFromResource(R.drawable.a_buffalo);
        gv.drawBitmap(bitmap, 3,3,3,3);
    }
}
