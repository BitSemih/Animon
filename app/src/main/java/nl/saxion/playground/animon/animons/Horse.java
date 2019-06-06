package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import java.util.ArrayList;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.game.Game;

public class Horse extends Animon {
    public Horse(Game game, String name, int health, int level, int experience, ArrayList<AttackMove> attackMoves) {
        super(game, name, health, level, experience, attackMoves);
    }

    private Bitmap bitmap;

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        bitmap = gv.getBitmapFromResource(R.drawable.a_horse);
        gv.drawBitmap(bitmap, 3,3,3,3);
    }
}
