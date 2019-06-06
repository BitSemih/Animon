package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Bear extends Animon {
    public Bear(Game game, String name, int health, int level, int experience) {
        super(game, name, health, level, experience);

        this.attackMoves.add(new AttackMove("Scratch", 6, 80));
        this.attackMoves.add(new AttackMove("Hairball", 5, 75));
        this.attackMoves.add(new AttackMove("Manipulate", 3, 90));
        this.attackMoves.add(new AttackMove("Schleep", 10, 40));
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        bitmap = gv.getBitmapFromResource(R.drawable.a_bear);
        gv.drawBitmap(bitmap, 3,3,3,3);
    }
}
