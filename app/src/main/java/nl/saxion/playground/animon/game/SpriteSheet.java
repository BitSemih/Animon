package nl.saxion.playground.animon.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Wrapper around a bitmap so that we can use it as a spritesheet.
 */
public class SpriteSheet {

    /**
     * Create a spritesheet based on a given tilesize
     *
     * @param pBitmap
     * @param pTileSizeX
     * @param pTileSizeY
     * @return
     */
    public static SpriteSheet createSheetFromTileSize(Bitmap pBitmap, int pTileSizeX, int pTileSizeY) {
        SpriteSheet spriteSheet = new SpriteSheet(pBitmap);

        spriteSheet._tilesizeX = pTileSizeX;
        spriteSheet._tilesizeY = pTileSizeY;
        spriteSheet._columns = spriteSheet._width / pTileSizeX;
        spriteSheet._rows = spriteSheet._height / pTileSizeY;
        spriteSheet._frameCount = spriteSheet._rows * spriteSheet._columns;

        return spriteSheet;
    }

    /**
     * Create a spritesheet based on given nr of columns and rows
     *
     * @param pBitmap
     * @param pColumns
     * @param pRows
     * @return
     */
    public static SpriteSheet createSheetFromColumnsAndRows(Bitmap pBitmap, int pColumns, int pRows) {
        SpriteSheet spriteSheet = new SpriteSheet(pBitmap);

        spriteSheet._tilesizeX = spriteSheet._width / pColumns;
        spriteSheet._tilesizeY = spriteSheet._height / pRows;
        spriteSheet._columns = pColumns;
        spriteSheet._rows = pRows;
        spriteSheet._frameCount = spriteSheet._rows * spriteSheet._columns;
        System.out.println(spriteSheet._tilesizeX);
        System.out.println(spriteSheet._tilesizeY);
        return spriteSheet;
    }

    public static SpriteSheet createMapSheetFromColumsAndRows(Bitmap pBitmap, int pColumns, int pRows) {
        SpriteSheet spriteSheet = new SpriteSheet(pBitmap);

        spriteSheet._tilesizeX = 32;
        spriteSheet._tilesizeY = 32;       //<----- deze lijn klopte niet
        spriteSheet._columns = pColumns;
        spriteSheet._rows = pRows;
        spriteSheet._frameCount = spriteSheet._rows * spriteSheet._columns;

        return spriteSheet;
    }

    private Bitmap _bitmap;
    private int _width;
    private int _height;
    private int _tilesizeX;
    private int _tilesizeY;
    private int _columns;
    private int _rows;
    private int _frameCount;

    private final Rect _srcRect = new Rect();
    private final Rect _dstRect = new Rect();

    private SpriteSheet(Bitmap pBitmap) {
        _bitmap = pBitmap;
        _width = pBitmap.getWidth();
        _height = pBitmap.getHeight();
    }

    /**
     * Get the rectangle for the given frame. If source is passed in, that rectangle will be used
     * instead of creating a new one for performance reasons.
     *
     * @param pFrame
     * @param pSource
     * @return
     */
    public Rect getRect(int pFrame, Rect pSource) {
        pFrame %= _frameCount;
        int column = pFrame % _columns;
        int row = pFrame / _columns;

        pSource = pSource == null ? new Rect() : pSource;
        pSource.left = column * _tilesizeX;
        pSource.right = pSource.left + _tilesizeX;
        pSource.top = row * _tilesizeY;
        pSource.bottom = pSource.top + _tilesizeY;

        return pSource;
    }

    /**
     * Draw the given frame to the given canvas at the given position
     *
     * @param pFrame
     * @param pCanvas
     * @param pTarget
     */
    public void drawFrame(int pFrame, Canvas pCanvas, RectF pTarget) {
        pCanvas.drawBitmap(_bitmap, getRect(pFrame, _srcRect), pTarget, null);
    }
}
