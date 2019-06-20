package nl.saxion.playground.animon.animons;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon.game.Game;

public abstract class Animon extends Entity {

    protected String name;
    protected int health;
    protected int level;
    protected int experience;
    protected ArrayList<AttackMove> attackMoves = new ArrayList<>();

    protected Game game;

    public Animon(Game game, String name, int health, int level, int experience) {
        this.game = game;
        this.name = name;
        this.health = health;
        this.level = level;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
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
