package nl.saxion.playground.animon.game;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameModel;
import nl.saxion.playground.animon.animons.Bear;
import nl.saxion.playground.animon.animons.Buffalo;
import nl.saxion.playground.animon.animons.Chicken;
import nl.saxion.playground.animon.animons.Elephant;
import nl.saxion.playground.animon.animons.Horse;
import nl.saxion.playground.animon.animons.Mees;
import nl.saxion.playground.animon.animons.Pig;
import nl.saxion.playground.animon.animons.Sloth;
import nl.saxion.playground.animon.animons.Snake;
import nl.saxion.playground.animon.animons.Walrus;
import nl.saxion.playground.animon.animons.Whale;
import nl.saxion.playground.animon.game.menu.Help;
import nl.saxion.playground.animon.game.menu.Inventory;
import nl.saxion.playground.animon.game.menu.LoadGame;
import nl.saxion.playground.animon.game.menu.Menu;
import nl.saxion.playground.animon.game.menu.SaveGame;

public class Game extends GameModel {

    private String jsonString = "";
    private Typeface pokemonfont;
    private Context context;
    private MediaPlayer mapMusic;
    private MediaPlayer battleMusic;
    private int musicPos = 0;
    private int state;

    public Game(String jsonString, Typeface pokemonfont, Context context) {
        this.jsonString = jsonString;
        this.pokemonfont = pokemonfont;
        this.context = context;
    }

    @Override
    public void start() {
        addEntity(new KeyEntity(this));
        //addEntity(new Tiles(this));
        Collision collision = new Collision();
        addEntity(new Map(jsonString, this, collision));

        addEntity(new Player(this, collision));

        Menu menu = new Menu(this, this.context);

        addEntity(menu);

        mapMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.map_music);
        mapMusic.start();
        mapMusic.setLooping(true);

        battleMusic = MediaPlayer.create(context.getApplicationContext(),R.raw.battle_music);
        battleMusic.setLooping(true);

        //Adding menu items
        SaveGame saveGame = new SaveGame("SAVE GAME", this, pokemonfont);
        LoadGame loadGame = new LoadGame("LOAD GAME", this, pokemonfont);
        Inventory inventory = new Inventory("INVENTORY", this, pokemonfont);
        Help help = new Help("HELP", this, pokemonfont);

        //Add menu items to menu class
        menu.addMenuItem(saveGame);
        menu.addMenuItem(loadGame);
        menu.addMenuItem(inventory);
        menu.addMenuItem(help);

        addEntity(saveGame);
        addEntity(loadGame);
        addEntity(inventory);
        addEntity(help);

        addEntity(new Chicken(this, "Chicken", 101, 12, 0));
        addEntity(new Bear(this, "Bear", 97, 15, 0));
        addEntity(new Buffalo(this, "Buffalo", 105, 14, 0));
        addEntity(new Elephant(this, "Elephant", 134, 12, 0));
        addEntity(new Horse(this, "Horse", 121, 11, 0));
        addEntity(new Pig(this, "Pig", 104, 16, 0));
        addEntity(new Mees(this, "Mees", 9999, 69, 0));
        addEntity(new Sloth(this, "Sloth", 89, 10, 0));
        addEntity(new Snake(this, "Snake", 115, 15, 0));
        addEntity(new Walrus(this, "Walrus", 124, 14, 0));
        addEntity(new Whale(this, "Whale", 147, 21, 0));

        addEntity(new Battle(R.drawable.s_battle_background, this, R.drawable.s_battle_platform, R.drawable.s_battle_message_box, pokemonfont));

        Log.i("Game virtual size:", getWidth() + " / " + getHeight());
        Log.i("Game actual size:", actualWidth + " / " + actualHeight);
    }

    @Override
    public float getWidth() {
        // Width is always 16 units.
        return 16f;
    }

    @Override
    public float getHeight() {
        // Height fills actual screen size, but is based on width scaling.
        return actualHeight / actualWidth * getWidth();
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void resumeMusic() {
        if (mapMusic != null){
            mapMusic.start();
        }
    }

    public void pauseMusic() {
        if (mapMusic != null){
            mapMusic.pause();
        }
        battleMusic.pause();
    }

    public void endBattleMusic() {
        battleMusic.pause();
        mapMusic.start();
    }

    public void startBattleMusic() {
        mapMusic.pause();
        battleMusic.seekTo(0);
        battleMusic.start();
    }
}
