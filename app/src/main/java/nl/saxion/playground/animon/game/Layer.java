package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

import org.json.JSONArray;

import java.util.Arrays;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Layer extends Entity {

    private int[][] tiles = null;

    // The game to draw in
    private Game game;

    private String name;

    private SpriteSheet map_sheet;

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    // The list of resource ids to draw each tile type with.
    static private final int[] spriteResourceIds = {0,
            R.drawable.sprite_0000,    //1
            R.drawable.s_0041,    //2
            R.drawable.s_0042,    //3
            R.drawable.s_0664,    //4
            R.drawable.s_0665,    //5
            R.drawable.s_0666,    //6
            R.drawable.s_0667,    //7
            R.drawable.s_0668,    //8
            R.drawable.s_0758,    //9
            R.drawable.s_0759,    //10
            R.drawable.s_0760,    //11
            R.drawable.s_0761,    //12
            R.drawable.s_0762,    //13
            R.drawable.s_0852,    //14
            R.drawable.s_0853,    //15
            R.drawable.s_0854,    //16
            R.drawable.s_0135,    //17

            // tree 1
            R.drawable.s_0011,    // 18
            R.drawable.s_0012,    //19
            R.drawable.s_0013,    //20
            R.drawable.s_0077,    //21
            R.drawable.s_0078,    //22
            R.drawable.s_0079,    //23
            R.drawable.s_0160,    //24
            R.drawable.s_0161,    //25
            R.drawable.s_0162,    //26

            // tree 2
            R.drawable.s_0014,      //27
            R.drawable.s_0015,      //28
            R.drawable.s_0016,      //29
            R.drawable.s_0080,      //30
            R.drawable.s_0081,      //31
            R.drawable.s_0082,       //32
            R.drawable.s_0163,       //33
            R.drawable.s_0164,      //34
            R.drawable.s_0165,       //35


            // grass road
            R.drawable.s_0812,      //36
            R.drawable.s_0741,      //37
            R.drawable.s_0742,      //38
            R.drawable.s_0743,       //39
            R.drawable.s_0744,       // 40
            R.drawable.s_0745,      //41
            R.drawable.s_0811,       //42
            R.drawable.s_0813,      //43
            R.drawable.s_0814,      //44
            R.drawable.s_0815,      //45
            R.drawable.s_0876,      //46
            R.drawable.s_0877,      //47
            R.drawable.s_0878,      //48

            //tree 3
            R.drawable.sprite_0017, //49
            R.drawable.sprite_0018, //50
            R.drawable.sprite_0019, //51
            R.drawable.sprite_0083, //52
            R.drawable.sprite_0084, //53
            R.drawable.sprite_0085, //54
            R.drawable.sprite_0166, //55
            R.drawable.sprite_0167, //56
            R.drawable.sprite_0168, //57

            //animon1
            R.drawable.sprite_0005, //58

            //tree 4
            R.drawable.sprite_0020, //59
            R.drawable.sprite_0021, //60
            R.drawable.sprite_0022, //61
            R.drawable.sprite_0086, //62
            R.drawable.sprite_0087, //63
            R.drawable.sprite_0088, //64
            R.drawable.sprite_0169, //65
            R.drawable.sprite_0170, //66
            R.drawable.sprite_0171, //67

            // tree 5
            R.drawable.sprite_0026, //68
            R.drawable.sprite_0027, //69
            R.drawable.sprite_0028, //70
            R.drawable.sprite_0092, //71
            R.drawable.sprite_0093, //72
            R.drawable.sprite_0094, //74
            R.drawable.sprite_0175, //75
            R.drawable.sprite_0176, //76
            R.drawable.sprite_0177, //77

            //tree 6
            R.drawable.sprite_0029, //78
            R.drawable.sprite_0030, //79
            R.drawable.sprite_0031, //80
            R.drawable.sprite_0095, //81
            R.drawable.sprite_0096, //82
            R.drawable.sprite_0097, //83
            R.drawable.sprite_0178, //84
            R.drawable.sprite_0179, //85
            R.drawable.sprite_0180, //86

            // palm tree
            R.drawable.sprite_0837, //87
            R.drawable.sprite_0838, //88
            R.drawable.sprite_0839, //89
            R.drawable.sprite_0906, //90
            R.drawable.sprite_0907, //91
            R.drawable.sprite_0908, //92
            R.drawable.sprite_0909, //93
            R.drawable.sprite_0975, //94
            R.drawable.sprite_0976, //95
            R.drawable.sprite_0977, //96
            R.drawable.sprite_0978, //97
            R.drawable.sprite_1056, //98
            R.drawable.sprite_1057, //99
            R.drawable.sprite_1058, //100

            //tree stump 1
            R.drawable.sprite_0543, //101
            R.drawable.sprite_0544, //102
            R.drawable.sprite_0545, //103
            R.drawable.sprite_0617, //104
            R.drawable.sprite_0618, //105
            R.drawable.sprite_0619, //106

            //big tree 1
            R.drawable.sprite_0566, //107
            R.drawable.sprite_0567, //108
            R.drawable.sprite_0568, //109
            R.drawable.sprite_0640, //110
            R.drawable.sprite_0641, //111
            R.drawable.sprite_0642, //112
            R.drawable.sprite_0643, //113
            R.drawable.sprite_0644, //114
            R.drawable.sprite_0710, //115
            R.drawable.sprite_0711, //116
            R.drawable.sprite_0712, //117
            R.drawable.sprite_0713, //118
            R.drawable.sprite_0714, //119
            R.drawable.sprite_0780, //120
            R.drawable.sprite_0781, //121
            R.drawable.sprite_0782, //122
            R.drawable.sprite_0783, //123
            R.drawable.sprite_0784, //124
            R.drawable.sprite_0850, //125
            R.drawable.sprite_0851, //125
            R.drawable.sprite_0852, //125

            //tree stump 2
            R.drawable.sprite_0546, //126
            R.drawable.sprite_0547, //127
            R.drawable.sprite_0620, //128
            R.drawable.sprite_0621, //129

            //flora
            R.drawable.sprite_0154, //130
    };

    public int[][] getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    public Layer(Game game, int width, int height, JSONArray jsonArray, String name, Collision collision) {
        this.game = game;
        this.tiles = new int[width][height];
        this.name = name;

        if (spriteBitmaps == null) spriteBitmaps = new Bitmap[spriteResourceIds.length];

        try {
            int x = 0;
            int y = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                int number = jsonArray.getInt(i);
                if (number != 0) {
                    tiles[x][y] = number;
                } else {
                    tiles[x][y] = 0;
                }

                if (name.equals("Trees") && number != 0 || name.equals("Buildings") && number != 0) {
                    collision.addCollisionTiles(x, y, number);
                }

                x++;

                if (x == width) {
                    x = 0;
                    y++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
        public void draw (GameView gv){
            // Calculate which treesTiles are visible at the current scroll position.
            //float offset = game.getEntity(Movement.class).z;
            int startX = (int) (game.getEntity(Player.class).getX() - 7);
            int startY = (int) (game.getEntity(Player.class).getY() - (game.getHeight() / 2));
            int endX = startX + 18;
            int endY = (int) (startY + game.getHeight() + 1) + 1;

            int tile = 0;
            if (startX < 0) {
                startX = 0;
            }

            if (endX >= tiles.length) {
                endX = tiles.length - 1;
            }

            if (startY < 0) {
                startY = 0;
            }

            if (endY >= tiles.length) {
                endY = tiles.length - 1;
            }

            if (map_sheet == null) {
                Bitmap bitmap = gv.getBitmapFromResource(R.drawable.tileset);

             //   int tileSize = (int) (16 * gv.getResources().getDisplayMetrics().density);
            //    System.out.println(gv.getResources().getDisplayMetrics().density);
                map_sheet = SpriteSheet.createSheetFromTileSize(gv.getBitmapFromResource(R.drawable.tileset), 16, 16);

            }



            // Draw any visible tiles.
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    // Ground
                    tile = tiles[x][y];
                    if (tile != 0) {

                            map_sheet.drawFrame(tile-1, gv.getCanvas(), new RectF( x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), (x - game.getEntity(Player.class).getX() + 7) + 1, (y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2))) + 1));

                            /*if (spriteBitmaps[tile] == null) {
                                // Load/decode bitmaps before we first draw them.
                                spriteBitmaps[tile] = gv.getBitmapFromResource(spriteResourceIds[tile]);
                            }
                            gv.drawBitmap(spriteBitmaps[tile], x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), 1, 1);
                        */
                    }
                }
            }
        }
    }


