package nl.saxion.playground.animon.animons;

public class AttackMove{
    private String moveName;
    private int damage;
    private double succeedChance;

    public AttackMove(String moveName, int damage, double succeedChance) {
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

    public double getSucceedChance() {
        return succeedChance;
    }

    public boolean success(){
        return false;
    }


}
