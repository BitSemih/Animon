package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;

import org.json.JSONArray;

import nl.saxion.playground.animon.R;
import nl.saxion.playground.animon._lib.Entity;
import nl.saxion.playground.animon._lib.GameView;

public class Layer extends Entity {

    private int[][] tiles = null;

    // The game to draw in
    private Game game;

    // When resources are first used, the decoded Bitmap is written to this array, as a cache.
    static private Bitmap[] spriteBitmaps;

    // The list of resource ids to draw each tile type with.
    static private final int[] spriteResourceIds = {0,
            R.drawable.s_0002,    //1
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
            R.drawable.s_0744,  // 40
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





















    };

    public Layer(Game game, int width, int height, JSONArray jsonArray) {
        this.game = game;
        this.tiles = new int[width][height];
        if (spriteBitmaps == null) spriteBitmaps = new Bitmap[spriteResourceIds.length];

        try {
            int x = 0;
            int y = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                int number = jsonArray.getInt(i);
                if (number == 2) {
                    tiles[x][y] = 1;
                } else if (number == 41) {
                    tiles[x][y] = 2;
                } else if (number == 42) {
                    tiles[x][y] = 3;
                } else if (number == 664) {
                    tiles[x][y] = 4;
                } else if (number == 665) {
                    tiles[x][y] = 5;
                } else if (number == 666) {
                    tiles[x][y] = 6;
                } else if (number == 667) {
                    tiles[x][y] = 7;
                } else if (number == 668) {
                    tiles[x][y] = 8;
                } else if (number == 758) {
                    tiles[x][y] = 9;
                } else if (number == 759) {
                    tiles[x][y] = 10;
                } else if (number == 760) {
                    tiles[x][y] = 11;
                } else if (number == 761) {
                    tiles[x][y] = 12;
                } else if (number == 762) {
                    tiles[x][y] = 13;
                } else if (number == 852) {
                    tiles[x][y] = 14;
                } else if (number == 853) {
                    tiles[x][y] = 15;
                } else if (number == 854) {
                    tiles[x][y] = 16;
                } else if (number == 135) {
                    tiles[x][y] = 17;

                    // tree 1
                } else if (number == 13) {
                    tiles[x][y] = 18;
                } else if (number == 14) {
                    tiles[x][y] = 19;
                } else if (number == 15) {
                    tiles[x][y] = 20;
                } else if (number == 107) {
                    tiles[x][y] = 21;
                } else if (number == 108) {
                    tiles[x][y] = 22;
                } else if (number == 109) {
                    tiles[x][y] = 23;
                } else if (number == 201) {
                    tiles[x][y] = 24;
                } else if (number == 202) {
                    tiles[x][y] = 25;
                } else if (number == 203) {
                    tiles[x][y] = 26;

                    // tree 2
                } else if (number == 16) {
                    tiles[x][y] = 27;
                } else if (number == 17) {
                    tiles[x][y] = 28;
                } else if (number == 18) {
                    tiles[x][y] = 29;
                } else if (number == 110) {
                    tiles[x][y] = 30;
                } else if (number == 111) {
                    tiles[x][y] = 31;
                } else if (number == 112) {
                    tiles[x][y] = 32;
                } else if (number == 204) {
                    tiles[x][y] = 33;
                } else if (number == 205) {
                    tiles[x][y] = 34;
                } else if (number == 206) {
                    tiles[x][y] = 35;

                    // grassRoad
                } else if (number == 1036) {
                    tiles[x][y] = 36;
                } else if (number == 941) {
                    tiles[x][y] = 37;
                } else if (number == 942) {
                    tiles[x][y] = 38;
                } else if (number == 943) {
                    tiles[x][y] = 39;
                } else if (number == 944) {
                    tiles[x][y] = 40;
                } else if (number == 945) {
                    tiles[x][y] = 41;
                } else if (number == 1035) {
                    tiles[x][y] = 42;
                } else if (number == 1037) {
                    tiles[x][y] = 43;
                } else if (number == 1038) {
                    tiles[x][y] = 44;
                } else if (number == 1039) {
                    tiles[x][y] = 45;
                } else if (number == 1129) {
                    tiles[x][y] = 46;
                } else if (number == 1130) {
                    tiles[x][y] = 47;
                } else if (number == 1131) {
                    tiles[x][y] = 48;


                    // tree 3
                } else if (number == 19) {
                    tiles[x][y] = 49;
                } else if (number == 20) {
                    tiles[x][y] = 50;
                } else if (number == 21) {
                    tiles[x][y] = 51;
                } else if (number == 113) {
                    tiles[x][y] = 52;
                } else if (number == 114) {
                    tiles[x][y] = 53;
                } else if (number == 115) {
                    tiles[x][y] = 54;
                } else if (number == 207) {
                    tiles[x][y] = 55;
                } else if (number == 208) {
                    tiles[x][y] = 56;
                } else if (number == 209) {
                    tiles[x][y] = 57;

                    // animon
                } else if (number == 7) {
                    tiles[x][y] = 58;

                    // tree 4
                } else if (number == 22) {
                    tiles[x][y] = 59;
                } else if (number == 23) {
                    tiles[x][y] = 60;
                } else if (number == 24) {
                    tiles[x][y] = 61;
                } else if (number == 116) {
                    tiles[x][y] = 62;
                } else if (number == 117) {
                    tiles[x][y] = 63;
                } else if (number == 118) {
                    tiles[x][y] = 64;
                } else if (number == 210) {
                    tiles[x][y] = 65;
                } else if (number == 211) {
                    tiles[x][y] = 66;
                } else if (number == 212) {
                    tiles[x][y] = 67;

                    // tree 5
                } else if (number == 28) {
                    tiles[x][y] = 68;
                } else if (number == 29) {
                    tiles[x][y] = 69;
                } else if (number == 30) {
                    tiles[x][y] = 70;
                } else if (number == 122) {
                    tiles[x][y] = 71;
                } else if (number == 123) {
                    tiles[x][y] = 72;
                } else if (number == 124) {
                    tiles[x][y] = 73;
                } else if (number == 216) {
                    tiles[x][y] = 74;
                } else if (number == 217) {
                    tiles[x][y] = 75;
                } else if (number == 218) {
                    tiles[x][y] = 76;

                    //tree 6
                } else if (number == 31) {
                    tiles[x][y] = 77;
                } else if (number == 32) {
                    tiles[x][y] = 78;
                } else if (number == 33) {
                    tiles[x][y] = 79;
                } else if (number == 125) {
                    tiles[x][y] = 80;
                } else if (number == 126) {
                    tiles[x][y] = 81;
                } else if (number == 127) {
                    tiles[x][y] = 82;
                } else if (number == 219) {
                    tiles[x][y] = 83;
                } else if (number == 220) {
                    tiles[x][y] = 84;
                } else if (number == 221) {
                    tiles[x][y] = 85;

                    //palm tree
                } else if (number == 1066) {
                    tiles[x][y] = 86;
                } else if (number == 1067) {
                    tiles[x][y] = 87;
                } else if (number == 1068) {
                    tiles[x][y] = 88;
                } else if (number == 1159) {
                    tiles[x][y] = 89;
                } else if (number == 1160) {
                    tiles[x][y] = 90;
                } else if (number == 1161) {
                    tiles[x][y] = 91;
                } else if (number == 1162) {
                    tiles[x][y] = 92;
                } else if (number == 1253) {
                    tiles[x][y] = 93;
                } else if (number == 1254) {
                    tiles[x][y] = 94;
                } else if (number == 1255) {
                    tiles[x][y] = 95;
                } else if (number == 1256) {
                    tiles[x][y] = 96;
                } else if (number == 1348) {
                    tiles[x][y] = 97;
                } else if (number == 1349) {
                    tiles[x][y] = 98;
                } else if (number == 1350) {
                    tiles[x][y] = 99;

                    // tree stump 1
                } else if (number == 679) {
                    tiles[x][y] = 100;
                } else if (number == 680) {
                    tiles[x][y] = 101;
                } else if (number == 681) {
                    tiles[x][y] = 102;
                } else if (number == 773) {
                    tiles[x][y] = 103;
                } else if (number == 774) {
                    tiles[x][y] = 104;
                } else if (number == 775) {
                    tiles[x][y] = 105;











































                } else {
                    tiles[x][y] = 0;
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
    public void draw(GameView gv) {
        // Calculate which treesTiles are visible at the current scroll position.
        //float offset = game.getEntity(Movement.class).z;
        int startX = (int) (game.getEntity(Player.class).getX() - 7);
        int startY = (int) (game.getEntity(Player.class).getY() - (game.getHeight() / 2));
        int endX = startX + 18;
        int endY = (int) (startY + game.getHeight() + 1) + 1;

        int tile = 0;
        // Draw any visible tiles.
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Ground
                tile = tiles[x][y];
                if (tile != 0) {
                    if (spriteBitmaps[tile] == null) {
                        // Load/decode bitmaps before we first draw them.
                        spriteBitmaps[tile] = gv.getBitmapFromResource(spriteResourceIds[tile]);
                    }
                    gv.drawBitmap(spriteBitmaps[tile], x - game.getEntity(Player.class).getX() + 7, y - (game.getEntity(Player.class).getY() - (game.getHeight() / 2)), 1, 1);
                }
            }
        }

    }

}
