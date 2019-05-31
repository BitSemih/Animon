package nl.saxion.playground.animon.game;

public class AttackMove{
    private String moveName;
    private int damage;
    private int succeedChance;

    public AttackMove(String moveName, int damage, int succeedChance) {
        this.moveName = moveName;
        this.damage = damage;
        this.succeedChance = succeedChance;
    }

    public String getMoveName() {
        return moveName;
    }

    public int getDamage() {
        return damage;
    }

    public int getSucceedChance() {
        return succeedChance;
    }
}
