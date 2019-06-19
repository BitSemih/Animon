package nl.saxion.playground.animon.game;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.GameModel;
import nl.saxion.playground.animon.game.menu.Help;
import nl.saxion.playground.animon.game.menu.Inventory;
import nl.saxion.playground.animon.game.menu.LoadGame;
import nl.saxion.playground.animon.game.menu.Menu;
import nl.saxion.playground.animon.game.menu.SaveGame;

public class Game extends GameModel {

    private String jsonString =  "";
    private Typeface pokemonfont;
    private Context context;
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

        addEntity(new Battle(R.drawable.s_battle_background, this, R.drawable.s_battle_platform, R.drawable.s_battle_message_box, pokemonfont));

//        addEntity(new Bear(this, "Bear", 100, 2, 0));

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
        return actualHeight/actualWidth * getWidth();
    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }
}
