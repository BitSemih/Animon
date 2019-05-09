package nl.saxion.playground.template.mygame;

import android.graphics.Color;
import android.graphics.Paint;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Background extends Entity {

    MyGame game;

    public Background(MyGame game) {

    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);

        Paint bgPaint = new Paint();
        bgPaint.setColor(Color.BLUE);
        //make sure we see the whole view
        gv.getCanvas().drawRect(0, 0, gv.getWidth(), gv.getHeight(),bgPaint);
    }
}
