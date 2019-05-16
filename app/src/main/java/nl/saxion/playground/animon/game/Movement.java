package nl.saxion.playground.animon.game;

import nl.saxion.playground.animon._lib.Entity;

public class Movement extends Entity {

    private Animon game;

    public float z = 0;

    public Movement(Animon game) {
        this.game = game;
    }

    // Auto-scroll
    @Override
    public void tick() {
        if (z > 0){
            z -= 0.01f;
        }
    }


}
