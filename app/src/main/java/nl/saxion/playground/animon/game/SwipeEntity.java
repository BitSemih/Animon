package nl.saxion.playground.animon.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameModel;

public class SwipeEntity extends Entity {

    private ArrayList<SwipeListener> swipeListeners = new ArrayList<>();
    private Animon _game;

    public SwipeEntity(Animon pGame) {
        _game = pGame;
    }

    @Override
    public void handleTouch(GameModel.Touch touch, MotionEvent event) {
        if (touch.lastAction != MotionEvent.ACTION_UP) return;

        if ((touch.x - touch.startX) < - 0.2f * _game.getWidth()) {
            for (SwipeListener listener : swipeListeners) {
                listener.onLeftSwipe();
            }
        }

        if ((touch.startX - touch.x) < - 0.2f * _game.getWidth()) {
            for (SwipeListener listener : swipeListeners) {
                listener.onRightSwipe();
            }
        }

        if ((touch.y - touch.startY) < - 0.2f * _game.getHeight()){
            for (SwipeListener listener : swipeListeners) {
                listener.onUpSwipe();
            }
        }

        if ((touch.startY - touch.y) < - 0.2f * _game.getHeight()){
            for (SwipeListener listener : swipeListeners) {
                listener.onDownSwipe();
            }
        }
    }

    public void addSwipeListener(SwipeListener swipeListener) {
        swipeListeners.add(swipeListener);
    }
}
