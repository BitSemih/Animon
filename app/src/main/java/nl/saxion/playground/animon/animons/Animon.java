package nl.saxion.playground.animon.animons;

import android.graphics.Bitmap;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon.game.Game;

public abstract class Animon extends Entity {
    private String name;
    private int health;
    private int level;
    private int experience;
    private ArrayList<AttackMove> attackMoves;

    private Game game;

    public Animon(Game game, String name, int health, int level, int experience, ArrayList<AttackMove> attackMoves) {
        this.game = game;
        this.name = name;
        this.health = health;
        this.level = level;
        this.experience = experience;
        this.attackMoves = attackMoves;
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

}
