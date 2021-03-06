package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Random;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;
import nl.saxion.playground.animon.animons.Animon;
import nl.saxion.playground.animon.animons.AttackMove;
import nl.saxion.playground.animon.animons.Bear;
import nl.saxion.playground.animon.animons.Chicken;

public class Battle extends Entity implements KeyListener {
    private Animon playerAnimon, npcAnimon;
    private int background, battlePlatform, messageBox, count = 0, messageDelay, currentSelector = 0;
    private Bitmap backgroundBitmap, platformBitmap, messageBoxBitmap, whatWillPlayerDoBitmap, attackMoveBoxBitmap, statHpBar, statHpBarFiller, statPlayerBitmap, statNpcBitmap, menuSelectorBitmap;
    private Game game;
    private String welcomeMessage;
    private float frame, fightX, animonsX, runX, w, h;
    private Typeface pokemonfont;
    private Paint p;
    private char[] welcomeMessageLetters;

    private boolean nextMessageTrigger, startBattle, playerTurn, playerCanAttack, isBattleOngoing;
    private float[] menuSelectorPositions = new float[3], attackMovesXPositions = new float[4];
    private AttackMove attackMove;
    private ArrayList<AttackMove> playerAttackMoves, npcAttackMoves;

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

        this.game.getEntity(KeyEntity.class).addKeyListener(this);



        this.w = game.getWidth();
        this.h = game.getHeight();
    }

    public void startBattle(Animon npcAnimon) {
        game.startBattleMusic();
        //Set welcome message
        String s = "A wild " + npcAnimon.getName() + " appeared!";

        this.welcomeMessageLetters = null;
        welcomeMessage = "";
        this.welcomeMessageLetters = s.toCharArray();

        ArrayList<Animon> animons = game.getEntities(Animon.class);
        double randAnimon = new Random().nextInt(animons.size());

        this.npcAnimon = npcAnimon;
        this.npcAttackMoves = npcAnimon.getAttackMoves();
        this.playerAnimon = animons.get((int)randAnimon);
        this.playerAttackMoves = playerAnimon.getAttackMoves();


        this.game.setState(1);
        state = BATTLE_STATE;

        //Reset booleans and vars
        this.startBattle = true;
        this.playerCanAttack = false;
        this.isBattleOngoing = false;
        this.nextMessageTrigger = false;
        this.messageDelay = 0;
        this.count = 0;
        this.playerTurn = true;
        this.currentSelector = 0;

        //Add full healing to animons
        npcAnimon.setHealth((int)npcAnimon.getMaxHealth());
        playerAnimon.setHealth((int)playerAnimon.getMaxHealth());

    }

    public void endBattle() {
        game.endBattleMusic();
        state = MAP_STATE;
        startBattle = false;
    }

    public boolean calculateChance(double attackMoveChance) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 1;
        int randomInt = (int) randomDouble;

        int chance = (int) (attackMoveChance * 100);

        if (randomInt <= chance) {
            return true;
        } else {
            return false;
        }
    }

    public void playerMove(AttackMove attackMove) {
        playerTurn = false;

        if (calculateChance(attackMove.getSucceedChance())) {
            int npcHealth = (int) npcAnimon.getHealth() - attackMove.getDamage();
            npcAnimon.setHealth(npcHealth);
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
            int playerHealth = (int) playerAnimon.getHealth() - attackMove.getDamage();
            playerAnimon.setHealth(playerHealth);
        }

        if (playerAnimon.getHealth() <= 0) {
            endBattle();
        } else {
            playerTurn = true;
        }
    }

    @Override
    public void tick() {
        if (startBattle) {
            frame += 0.05;
            if ((int) frame % 2 == 1) {
                //do walk stuff
                addLetterToWelcomeMessage();
                frame = 0;
                if (messageDelay < 10) {
                    messageDelay++;
                }
            }

            if (playerTurn && attackMove != null && playerCanAttack && messageDelay == 10) {
                playerMove(attackMove);
            }
        }
    }

    @Override
    public void draw(GameView gv) {
        if (state == BATTLE_STATE) {

            //When bitmaps are not set
            if (backgroundBitmap == null || platformBitmap == null) {
                backgroundBitmap = gv.getBitmapFromResource(background);
                platformBitmap = gv.getBitmapFromResource(battlePlatform);
                messageBoxBitmap = gv.getBitmapFromResource(messageBox);
                menuSelectorBitmap = gv.getBitmapFromResource(R.drawable.menu_select_arrow);
                whatWillPlayerDoBitmap = gv.getBitmapFromResource(R.drawable.s_whatwillplayerdo_background);
                attackMoveBoxBitmap = gv.getBitmapFromResource(R.drawable.s_attackmove_background);
                statHpBar = gv.getBitmapFromResource(R.drawable.s_battle_hp_bar);
                statHpBarFiller = gv.getBitmapFromResource(R.drawable.s_battle_hp_bar_filler);
                statPlayerBitmap = gv.getBitmapFromResource(R.drawable.s_battle_stat_player_background);
                statNpcBitmap = gv.getBitmapFromResource(R.drawable.s_battle_stat_npc_background);
            }

            //Draw the backgrounds of the battle
            gv.drawBitmap(backgroundBitmap, 0, 0, w, h);
            gv.drawBitmap(platformBitmap, w - 7.5f, h * 0.3f, 7, 2);
            gv.drawBitmap(platformBitmap, 0.5f, h * 0.75f, 8, 2);
            if (!isBattleOngoing) {
                //Draw the white message box background
                gv.drawBitmap(messageBoxBitmap, 0, h * 0.80f, w, h * 0.2f);
            }

            float scaleFactor = 0.5f;

            gv.getCanvas().save();
            gv.getCanvas().scale(scaleFactor, scaleFactor);
            //scale the whole gameview to 50%

            if (!nextMessageTrigger || messageDelay < 10) {
                //Display the welcome message to a battle
                gv.getCanvas().drawText(welcomeMessage, 2 + (welcomeMessage.length() * 0.25f) / scaleFactor, h * 0.9f / scaleFactor, p);
            } else if (!isBattleOngoing) {
                // Player is presented with options after welcome message
                if (fightX == 0 || animonsX == 0 || runX == 0) {
                    fightX = 2.5f / scaleFactor;
                    animonsX = (w / 2) / scaleFactor;
                    runX = (w - 2.5f) / scaleFactor;
                    menuSelectorPositions[0] = 1;
                    menuSelectorPositions[1] = animonsX - 5f;
                    menuSelectorPositions[2] = runX - 3;
                }
                gv.getCanvas().drawText("FIGHT", fightX, h * 0.9f / scaleFactor, p);
                gv.getCanvas().drawText("ANIMONS", animonsX, h * 0.9f / scaleFactor, p);
                gv.getCanvas().drawText("RUN", runX, h * 0.9f / scaleFactor, p);
                gv.drawBitmap(menuSelectorBitmap, menuSelectorPositions[currentSelector], h * 0.875f / scaleFactor, h * 0.05f, h * 0.05f);
            } else if (isBattleOngoing) {
                //When player has selected FIGHT! option

                //if attackmovespositions is not filled with the positions of the attack move options for the selector
                if (attackMovesXPositions[0] == 0) {
                    attackMovesXPositions[0] = w - 3;
                    attackMovesXPositions[1] = w - 3;
                    attackMovesXPositions[2] = w + 6;
                    attackMovesXPositions[3] = w + 6;
                }

                //What will player do? message box
                gv.drawBitmap(whatWillPlayerDoBitmap, 0, (h * 0.8f) / scaleFactor, w - 4, h * 0.4f);
                gv.getCanvas().drawText("What will", 3.5f / scaleFactor, (h * 0.88f) / scaleFactor, p);
                gv.getCanvas().drawText(" you do ?", 3 / scaleFactor, (h * 0.93f) / scaleFactor, p);

                //Attack move option box
                gv.drawBitmap(attackMoveBoxBitmap, w - 4, (h * 0.8f) / scaleFactor, w + 4, h * 0.4f);

                //Draw attackmove options for the player
                gv.getCanvas().drawText(playerAttackMoves.get(0).getMoveName(), w + 1, (h * 0.88f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAttackMoves.get(1).getMoveName(), w + 1, (h * 0.93f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAttackMoves.get(2).getMoveName(), w * 2 - 5, (h * 0.88f) / scaleFactor, p);
                gv.getCanvas().drawText(playerAttackMoves.get(3).getMoveName(), w * 2 - 5, (h * 0.93f) / scaleFactor, p);

                //draw the NPC stats
                gv.drawBitmap(statNpcBitmap, 1, h * 0.3f, w * 1.1f, h * 0.3f);
                gv.getCanvas().drawText(npcAnimon.getName().toUpperCase(), 6, h * 0.4f, p);
                gv.getCanvas().drawText("Lv" + npcAnimon.getLevel(), w - 3, h * 0.4f, p);

                //draw health stats for npc
                gv.drawBitmap(statHpBar, 3.5f, h * 0.45f, w - 3f, 1);
                gv.drawBitmap(statHpBarFiller, 6.6f, h * 0.465f, 9.5f * (npcAnimon.getHealth() / npcAnimon.getMaxHealth()), 0.4f);

                //draw the player stats
                gv.drawBitmap(statPlayerBitmap, w * 0.9f, h * 1.2f, w * 1.1f, h * 0.3f);
                gv.getCanvas().drawText(playerAnimon.getName().toUpperCase(), w * 1.3f, h * 1.3f, p);
                gv.getCanvas().drawText("Lv" + playerAnimon.getLevel(), w * 1.7f, h * 1.3f, p);


                //draw health stats for player
                gv.drawBitmap(statHpBar, w + 1.6f, h * 1.32f, w - 3f, 1);
                gv.drawBitmap(statHpBarFiller, w + 4.7f, h * 1.335f, 9.5f * (playerAnimon.getHealth() / playerAnimon.getMaxHealth()), 0.37f);
                gv.getCanvas().drawText((int) playerAnimon.getHealth() + "/" + (int) playerAnimon.getMaxHealth(), w * 1.65f, h * 0.72f / scaleFactor, p);

                //Draw animon bitmaps
                gv.drawBitmap(npcAnimon.getBitmap(), w * 1.31f, h * 0.4f, h * 0.4f, h * 0.4f);
                gv.drawBitmap(playerAnimon.getBitmap(), 5, h * 1.2f, h * 0.4f, h * 0.4f);

                //Check where the selector is positioned
                if (currentSelector == 0 || currentSelector == 2) {
                    gv.drawBitmap(menuSelectorBitmap, attackMovesXPositions[currentSelector], (h * 0.85f) / scaleFactor, h * 0.07f, h * 0.07f);
                } else if (currentSelector == 1 || currentSelector == 3) {
                    gv.drawBitmap(menuSelectorBitmap, attackMovesXPositions[currentSelector], (h * 0.90f) / scaleFactor, h * 0.07f, h * 0.07f);
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
        //Check if the battle state is on and ongoing
        if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 1) {
            currentSelector = 0;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 3) {
            currentSelector = 2;
        }
    }

    @Override
    public void onDownKey() {
        //Check if the battle state is on and ongoing
        if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 0) {
            currentSelector = 1;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 2) {
            currentSelector = 3;
        }
    }

    @Override
    public void onAKey() {
        if (isBattleOngoing) {
            // Perform selected attack move
            attackMove = playerAnimon.getAttackMoves().get(currentSelector);
        }

        if (currentSelector == 0 && !isBattleOngoing && nextMessageTrigger) {
            //When player picks FIGHT option
            isBattleOngoing = true;
        } else if (currentSelector == 1 && !isBattleOngoing && nextMessageTrigger) {
            //When player chooses inventory option
        } else if (currentSelector == 2 && !isBattleOngoing && nextMessageTrigger) {
            //When player wants to run from battle
            state = MAP_STATE;
        }

        if (isBattleOngoing && !playerCanAttack) {
            playerCanAttack = true;
        }
    }

    @Override
    public void onBKey() {

    }

    @Override
    public void onRightKey() {
        if (state == BATTLE_STATE && !isBattleOngoing && currentSelector < 2) {
            //Battle options menu
            currentSelector++;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 0) {
            //Attack moves menu
            currentSelector = 2;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 1) {
            //Attack moves menu
            currentSelector = 3;
        }
    }

    @Override
    public void onLeftKey() {
        if (state == BATTLE_STATE && currentSelector > 0 && !isBattleOngoing) {
            currentSelector--;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 2) {
            //Attack moves menu
            currentSelector = 0;
        } else if (state == BATTLE_STATE && isBattleOngoing && currentSelector == 3) {
            //Attack moves menu
            currentSelector = 1;
        }
    }

    @Override
    public void onMenuKey() {

    }
}
