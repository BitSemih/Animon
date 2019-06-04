package nl.saxion.playground.animon.animons;

import java.util.ArrayList;

public class Moves {
    private static ArrayList<AttackMove> attackMovesBear = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesBuffalo = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesChicken = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesElephant = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesHorse = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesPig = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesSloth = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesSnake = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesWalrus = new ArrayList<>();
    private static ArrayList<AttackMove> attackMovesWhale = new ArrayList<>();

    static {
        attackMovesBear.add(new AttackMove("Scratch", 6, 0.8));
        attackMovesBear.add(new AttackMove("Hairball", 5, 0.75));
        attackMovesBear.add(new AttackMove("Manipulate", 3, 0.9));
        attackMovesBear.add(new AttackMove("Slap", 10, 0.4));

        attackMovesBuffalo.add(new AttackMove("Horn ram", 6, 0.8));
        attackMovesBuffalo.add(new AttackMove("Trample", 8, 0.8));
        attackMovesBuffalo.add(new AttackMove("Kick", 5, 0.8));
        attackMovesBuffalo.add(new AttackMove("Poop", 6, 0.8));

        attackMovesChicken.add(new AttackMove("Pick", 6, 0.8));
        attackMovesChicken.add(new AttackMove("Slap Wings", 5, 0.95));
        attackMovesChicken.add(new AttackMove("Poop", 6, 0.8));
        attackMovesChicken.add(new AttackMove("Scratch", 4, 1));

        attackMovesElephant.add(new AttackMove("Trample", 9, 0.5));
        attackMovesElephant.add(new AttackMove("Charge", 6, 0.8));
        attackMovesElephant.add(new AttackMove("Ear slap", 5, 0.9));
        attackMovesElephant.add(new AttackMove("Bite", 4, 1));

        attackMovesHorse.add(new AttackMove("Gentleman", 4, 0.75));
        attackMovesHorse.add(new AttackMove("Bite", 6, 0.5));
        attackMovesHorse.add(new AttackMove("Kick", 5, 0.8));
        attackMovesHorse.add(new AttackMove("Bumb", 4, 0.9));

        attackMovesPig.add(new AttackMove("Poop", 6, 0.8));
        attackMovesPig.add(new AttackMove("Roll", 6, 0.9));
        attackMovesPig.add(new AttackMove("Kick", 5, 0.75));
        attackMovesPig.add(new AttackMove("Shoarma", 3, 1));

        attackMovesSloth.add(new AttackMove("Bite", 4, 1));
        attackMovesSloth.add(new AttackMove("Poop", 6, 0.8));
        attackMovesSloth.add(new AttackMove("Scratch", 4, 1));
        attackMovesSloth.add(new AttackMove("Infect", 5, 1));

        attackMovesSnake.add(new AttackMove("Poison", 4, 1));
        attackMovesSnake.add(new AttackMove("Strangle", 7, 0.6));
        attackMovesSnake.add(new AttackMove("Tail Slap", 5, 0.9));
        attackMovesSnake.add(new AttackMove("Bite", 4, 1));

        attackMovesWalrus.add(new AttackMove("Bubble gun", 4, 1));
        attackMovesWalrus.add(new AttackMove("Tail Slap", 5, 0.9));
        attackMovesWalrus.add(new AttackMove("Water board", 9, 0.35));
        attackMovesWalrus.add(new AttackMove("Bite", 6, 0.8));

        attackMovesWhale.add(new AttackMove("Bubble gun", 4, 1));
        attackMovesWhale.add(new AttackMove("Body Slam", 8, 0.5));
        attackMovesWhale.add(new AttackMove("Tail Slap", 5, 0.9));
        attackMovesWhale.add(new AttackMove("Bite", 6, 0.8));
    }

    public static ArrayList<AttackMove> getAttackMovesBear() {
        return attackMovesBear;
    }

    public static ArrayList<AttackMove> getAttackMovesBuffalo() {
        return attackMovesBuffalo;
    }

    public static ArrayList<AttackMove> getAttackMovesChicken() {
        return attackMovesChicken;
    }

    public static ArrayList<AttackMove> getAttackMovesElephant() {
        return attackMovesElephant;
    }

    public static ArrayList<AttackMove> getAttackMovesHorse() {
        return attackMovesHorse;
    }

    public static ArrayList<AttackMove> getAttackMovesPig() {
        return attackMovesPig;
    }

    public static ArrayList<AttackMove> getAttackMovesSloth() {
        return attackMovesSloth;
    }

    public static ArrayList<AttackMove> getAttackMovesSnake() {
        return attackMovesSnake;
    }

    public static ArrayList<AttackMove> getAttackMovesWalrus() {
        return attackMovesWalrus;
    }

    public static ArrayList<AttackMove> getAttackMovesWhale() {
        return attackMovesWhale;
    }
}
