package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import java.util.ArrayList;

import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;


public class Animon extends Entity {
    private String name;
    private int health;
    private int level;
    private int experience;
    private ArrayList<AttackMove> attackMoves;
    private int spriteResource;

    private static Bitmap bitmap;

    public Animon(String name, int health, int level, int experience, ArrayList<AttackMove> attackMoves, int spriteResource) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.experience = experience;
        this.attackMoves = attackMoves;
        this.spriteResource = spriteResource;
    }

    public void addMove(AttackMove attackMove) {
        attackMoves.add(attackMove);
    }

    @Override
    public void draw(GameView gv) {
        super.draw(gv);
        // bitmap = spriteResource
        gv.drawBitmap(bitmap, 1, 1, 1, 1);

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

    public int getSpriteResource() {
        return spriteResource;
    }
}
