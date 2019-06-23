package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon.game.Game;

public abstract class Animon extends Entity {

    protected String name;
    protected float health, maxHealth;
    protected int level;
    protected int experience;
    protected ArrayList<AttackMove> attackMoves = new ArrayList<>();
    protected Bitmap bitmap;

    protected Game game;

    public Animon(Game game, String name, int health, int level, int experience) {
        this.game = game;
        this.name = name;
        this.health = health;
        this.level = level;
        this.experience = experience;
        this.maxHealth = health;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth(){
        return maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public ArrayList<AttackMove> getAttackMoves() {
        return attackMoves;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
