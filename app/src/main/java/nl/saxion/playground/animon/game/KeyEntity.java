package nl.saxion.playground.animon.game;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;

public class KeyEntity extends Entity {

    private ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private Game game;

    public KeyEntity(Game game) {
        this.game = game;
    }

    public void onKeyPress(String key){
        if (key.equals("right")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onRightKey();
            }
        }

        if (key.equals("left")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onLeftKey();
            }
        }

        if (key.equals("up")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onUpKey();
            }
        }

        if (key.equals("down")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onDownKey();
            }
        }

        if (key.equals("menu")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onMenuKey();
            }
        }

        if (key.equals("a")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onAKey();
            }
        }

        if (key.equals("b")){
            for (KeyListener keyListener: keyListeners){
                keyListener.onBKey();
            }
        }
    }

    public void addKeyListener(KeyListener keyListener){
        this.keyListeners.add(keyListener);
    }
}
