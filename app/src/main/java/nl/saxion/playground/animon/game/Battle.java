package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.animons.Animon;
import nl.saxion.playground.animon.animons.AttackMove;
import nl.saxion.playground.animon.animons.Chicken;
import nl.saxion.playground.animon.animons.AttackMove;
import nl.saxion.playground.animon.animons.Bear;
import nl.saxion.playground.animon.animons.Chicken;

public class Battle extends Entity implements KeyListener {
    private static final String TAG = "keys";
    private Animon playerAnimon, npcAnimon;
    private int turn, background, battlePlatform, messageBox, count = 0, messageDelay, currentSelector = 0;
    private Bitmap backgroundBitmap, platformBitmap, messageBoxBitmap, whatWillPlayerDoBitmap, attackMoveBoxBitmap;
    private Game game;
    private String welcomeMessage;
    private float frame, fightX, animonsX, runX;
    private Typeface pokemonfont;
    private Paint p;
    private char[] welcomeMessageLetters;
    private boolean playerTurn = true;

    private boolean gameEnded = false;
    private int counter = 0;
    private ArrayList<AttackMove> playerAttackMoves;
    private ArrayList<AttackMove> npcAttackMoves;

    private boolean nextMessageTrigger = false;
    private Bitmap menuSelectorBitmap;
    private float[] menuSelectorPositions = new float[3], attackMovesXPositions = new float[4];
    private boolean isBattleActive = false;
    private boolean isBattleOngoing = false;
    private AttackMove attackMove;

    public Battle(int background, Game game, int battlePlatform, int messageBox, Typeface pokemonfont) {
        this.game = game;
        this.battlePlatform = battlePlatform;
        this.background = background;
        this.messageBox = messageBox;
        this.pokemonfont = pokemonfont;

        this.p = new Paint();
        this.p.setColor(Color.BLACK);
        this.p.setTextSize(1);
        this.p.setTypeface(pokemonfont);
        this.p.setAntiAlias(true);
        this.p.setTextAlign(Paint.Align.CENTER);
        this.p.setLinearText(true);
        welcomeMessage = "";
        String s = "A wild Mees appeared!";
        this.welcomeMessageLetters = s.toCharArray();

        this.game.getEntity(KeyEntity.class).addKeyListener(this);

        this.playerAnimon = game.getEntity(Chicken.class);
        this.npcAnimon = game.getEntity(Bear.class);
        this.playerAttackMoves = playerAnimon.getAttackMoves();
        this.npcAttackMoves = npcAnimon.getAttackMoves();

        isBattleActive = true;

        this.game.setState(state);
    }

    public void startBattle(Animon playerAnimon, Animon npcAnimon) {

    }

    public void endBattle() {
        state = 0;
    }

    public boolean calculateChance(double attackMoveChance) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 1;
        int randomInt = (int) randomDouble;

        int chance = (int) (attackMoveChance * 100);

        if (randomInt <= chance) {
            return true;
        } else return false;
    }

    public void isPlayerTurn() {
        if (playerTurn) {
            playerMove(attackMove);
        } else npcMove();

    }

    public void playerMove(AttackMove attackMove) {
        playerTurn = false;

        if (calculateChance(attackMove.getSucceedChance())) {
            int npcHealth = npcAnimon.getHealth() - attackMove.getDamage();
            npcAnimon.setHealth(npcHealth);
            Log.d("npcHealth", String.valueOf(npcHealth));
        }

        this.attackMove = null;
        if (npcAnimon.getHealth() <= 0) {
            endBattle();
        } else {
            npcMove();
        }

    }

    public void npcMove() {
        int randMove = (int) (Math.random() * 4);
        AttackMove attackMove = npcAttackMoves.get(randMove);

        if (calculateChance(attackMove.getSucceedChance())) {
            int playerHealth = playerAnimon.getHealth() - attackMove.getDamage();
            playerAnimon.setHealth(playerHealth);
            Log.d("playerHealth", String.valueOf(playerHealth));
        }

        if (playerAnimon.getHealth() <= 0) {
            endBattle();
        } else {
            playerTurn = true;
        }
    }

    @Override
    public void tick() {
        frame += 0.05;
        if ((int) frame % 2 == 1) {
            //do walk stuff
            addLetterToWelcomeMessage();
            frame = 0;
            if (messageDelay < 10) {
                messageDelay++;
            }
        }
        if (playerTurn && attackMove != null) {
            playerMove(attackMove);
        }
    }

    @Override
    public void draw(GameView gv) {
        if (state == 1) {
            if (backgroundBitmap == null || platformBitmap == null) {
                backgroundBitmap = gv.getBitmapFromResource(background);
                platformBitmap = gv.getBitmapFromResource(battlePlatform);
                messageBoxBitmap = gv.getBitmapFromResource(messageBox);
                menuSelectorBitmap = gv.getBitmapFromResource(R.drawable.menu_select_arrow);
                whatWillPlayerDoBitmap = gv.getBitmapFromResource(R.drawable.s_whatwillplayerdo_background);
                attackMoveBoxBitmap = gv.getBitmapFromResource(R.drawable.s_attackmove_background);
            }
            gv.drawBitmap(backgroundBitmap, 0, 0, game.getWidth(), game.getHeight());
            gv.drawBitmap(platformBitmap, game.getWidth() - 7.5f, game.getHeight() * 0.5f, 7, 2);
            gv.drawBitmap(platformBitmap, 0.5f, game.getHeight() - 5, 8, 2);
            if (!isBattleOngoing) {
                gv.drawBitmap(messageBoxBitmap, 0, game.getHeight() - 4, game.getWidth(), 4);
            }

            float scaleFactor = 0.5f;

            gv.getCanvas().save();
            gv.getCanvas().scale(scaleFactor, scaleFactor);
            gv.getCanvas().drawText(welcomeMessage, 2 + (welcomeMessage.length() * 0.25f) / scaleFactor, (game.getHeight() - 2.5f) / scaleFactor, p);
            gv.getCanvas().scale(scaleFactor, scaleFactor);
            //scale the whole gameview to 50%

            if (!nextMessageTrigger || messageDelay < 10) {
                //Display the welcome message to a battle
                gv.getCanvas().drawText(welcomeMessage, 2 + (welcomeMessage.length() * 0.25f) / scaleFactor, (game.getHeight() - 2.5f) / scaleFactor, p);
            } else if (!isBattleOngoing) {
                // Player is presented with options after welcome message
                if (fightX == 0 || animonsX == 0 || runX == 0) {
                    fightX = 2.5f / scaleFactor;
                    animonsX = (game.getWidth() / 2) / scaleFactor;
                    runX = (game.getWidth() - 2.5f) / scaleFactor;
                    menuSelectorPositions[0] = 1;
                    menuSelectorPositions[1] = animonsX - 5f;
                    menuSelectorPositions[2] = runX - 3;
                }
                gv.getCanvas().drawText("FIGHT", fightX, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.getCanvas().drawText("ANIMONS", animonsX, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.getCanvas().drawText("RUN", runX, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.drawBitmap(menuSelectorBitmap, menuSelectorPositions[currentSelector], (game.getHeight() * 2) - 6, 1, 1);
            } else if (isBattleOngoing) {
                //When player has selected FIGHT! option

                //if attackmovespositions is not filled with the positions of the attack move options for the selector
                if (attackMovesXPositions[0] == 0) {
                    attackMovesXPositions[0] = game.getWidth() - 3;
                    attackMovesXPositions[1] = game.getWidth() - 3;
                    attackMovesXPositions[2] = game.getWidth() + 6;
                    attackMovesXPositions[3] = game.getWidth() + 6;
                }

                gv.drawBitmap(whatWillPlayerDoBitmap, 0, (game.getHeight() - 4) / scaleFactor, game.getWidth() - 4, 8);
                gv.getCanvas().drawText("What will", 3.5f / scaleFactor, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.getCanvas().drawText(" you do ?", 3 / scaleFactor, (game.getHeight() - 1.5f) / scaleFactor, p);
                gv.drawBitmap(attackMoveBoxBitmap, game.getWidth() - 4, (game.getHeight() - 4) / scaleFactor, game.getWidth() + 4, 8);

                //Draw attackmove options for the player
                gv.getCanvas().drawText(playerAnimon.getAttackMoves().get(0).getMoveName(), game.getWidth() + 1, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAnimon.getAttackMoves().get(1).getMoveName(), game.getWidth() + 1, (game.getHeight() - 1.5f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAnimon.getAttackMoves().get(2).getMoveName(), game.getWidth() * 2 - 5, (game.getHeight() - 2.5f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAnimon.getAttackMoves().get(3).getMoveName(), game.getWidth() * 2 - 5, (game.getHeight() - 1.5f) / scaleFactor, p);

                //Check where the selector is positioned
                if (currentSelector == 0 || currentSelector == 2) {
                    gv.drawBitmap(menuSelectorBitmap, attackMovesXPositions[currentSelector], (game.getHeight() - 3) / scaleFactor, 1, 1);
                } else if (currentSelector == 1 || currentSelector == 3) {
                    gv.drawBitmap(menuSelectorBitmap, attackMovesXPositions[currentSelector], (game.getHeight() - 2) / scaleFactor, 1, 1);
                }
            }
            gv.getCanvas().restore();
        }
    }


    public void addLetterToWelcomeMessage() {
        //Add a letter to the welcome message every 50 ms
        if (messageDelay == 10) {
            if (count < welcomeMessageLetters.length) {
                welcomeMessage += welcomeMessageLetters[count];
            } else if (!nextMessageTrigger) {
                nextMessageTrigger = true;
                messageDelay = 0;
            }
            count++;
        }
    }

    @Override
    public void onMenuUpKey() {

    }

    @Override
    public void onMenuDownKey() {

    }

    @Override
    public void onUpKey() {
        if (state == 1 && isBattleOngoing && currentSelector == 1) {
            currentSelector = 0;
        } else if (state == 1 && isBattleOngoing && currentSelector == 3) {
            currentSelector = 2;
        }
    }

    @Override
    public void onDownKey() {
        if (state == 1 && isBattleOngoing && currentSelector == 0) {
            currentSelector = 1;
        } else if (state == 1 && isBattleOngoing && currentSelector == 2) {
            currentSelector = 3;
        }
    }

    @Override
    public void onAKey() {
        System.out.println(count);
        if (currentSelector == 0 && !isBattleOngoing && nextMessageTrigger) {
            isBattleOngoing = true;
        }
        if (isBattleOngoing) {
            // Perform selected attack move
            attackMove = playerAnimon.getAttackMoves().get(currentSelector);
        }
    }

    @Override
    public void onBKey() {

    }

    @Override
    public void onRightKey() {
        if (state == 1 && !isBattleOngoing && currentSelector < 2) {
            //Battle options menu
            currentSelector++;
        } else if (state == 1 && isBattleOngoing && currentSelector == 0) {
            //Attack moves menu
            currentSelector = 2;
        } else if (state == 1 && isBattleOngoing && currentSelector == 1) {
            //Attack moves menu
            currentSelector = 3;
        }
    }

    @Override
    public void onLeftKey() {
        if (state == 1 && currentSelector > 0 && !isBattleOngoing) {
            currentSelector--;
        } else if (state == 1 && isBattleOngoing && currentSelector == 2) {
            //Attack moves menu
            currentSelector = 0;
        } else if (state == 1 && isBattleOngoing && currentSelector == 3) {
            //Attack moves menu
            currentSelector = 1;
        }
    }

    @Override
    public void onMenuKey() {

    }

    public void performAttackMove(int attackMoveNumber) {
        //player or npc doesnt matter
        AttackMove currentAttackMove = playerAnimon.getAttackMoves().get(attackMoveNumber);
    }
}
