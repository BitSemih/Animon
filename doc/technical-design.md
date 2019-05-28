## Technisch ontwerp / Technical design

```plantuml

skinparam class {
    BackgroundColor<< lib >> white
    BackgroundColor<< game >> lightgrey
}

class Game<<game>> {
    + getWidth()
    + getHeight()
    + start()
}

class KeyEntity<<game>> {
    - keyListeners: ArrayList<KeyListener>
    - game: Animon
    + KeyEntity(game: Animon)
    + onKeyPress(key: String)
    + addKeyListener(keyListener: KeyListener)
}

class Player<<game>> {
    - x: float
    - y: float
    - rightBoundary: int
    - leftBoundary: int
    - upperBoundary: int
    - downBoundary: int
    - spriteResourceIds: int[]
    - spireBitmaps: Bitmap[]
    - game: Animon
    - animonArrayList: ArrayList<Animon>
    + Player(game: Animon)
    + draw(gv: GameView)
    + onRightKey()
    + onLeftKey()
    + onUpKey()
    + onDownKey()
    + checkCollisions()
    + getX()
    + getY()
}

class Tiles<<game>> {
    - width: int
    - height: int
    - game: Animon
    + Tiles(game: Animon)
    + draw(gv: GameView)
    + getWidth()
    + getHeight()
}

class Layer<<game>>{ 
    - width: int
    - height: int
    - json: String
    - tiles: int[][]
    - spriteResourceIds: int[]
    - spriteBitmaps: Bitmap[]
    - game: Animon
    + Layer(game: Animon)
    + draw(gv: GameView)
}

class Activity<<game>> {
    - game: Animon
    - gameView: GameView
    + onCreate(savedInstanceState: Bundle)
    + onSaveInstanceState(Bundle: outState)
    + onResume()
    + onPause()
}

class Collisions<<game>> {
    - noMove: int[][]
    + Collisions()
    + checkPos(x: int, y: int) : boolean
}

class Animon<<game>> {
    - name: String
    - level: int
    - spriteResource: int
    - movesArrayList: ArrayList<Move>
    + Animon(name: String, level: int)
    + addMove(move: Move)
}

class Move<<game>> {
    - name: String
    - damage: int
    - succeedChance: int
    + Move(name: String, damage: int, succeedChance: int)
}

class RepeatListener<<lib>> {
    - handler: Handler
    - initialInterval: int
    - normalInterval: int
    - clickListener: OnClickListener
    - touchedView: View
    - handlerRunnable: Runnable
    + RepeatListener(initialInterval: int, normalInterval: int, clickListener OnClickListener)
    + onTouch(view: View, motionEvent: MotionEvent)
}

class MainActivity {
    ~ onCreate(savedInstanceState: Bundle)
    ~ onResume()
    ~ onPause()
}

class Entity<<lib>> {
    + draw(GameView)
    + tick()
    + handleTouch(Touch)
    + getLayer(): int
}

class GameModel<<lib>> {
    - actualWidth
    - actualHeight
    + getWidth()
    + getHeight()
    + start()
    + addEntityEntity)
    + removeEntity(Entity)
    + getEntities(X.class): List<X>
}

class GameView<<lib>> {
    + setGame(GameModel)
    + setPaused(boolean)
    + drawBitmap(...)
    + getCanvas(): Canvas
}

GameModel o-> "*" Entity

GameView --> GameModel

Game -|> GameModel

Entity <|-- Player
Entity <|-- KeyEntity
Entity <|-- Tiles

Move --> Animon
Layer --> Tiles
Animon --> Player
Collisions -> Player

MainActivity --> Activity
Activity --> Game
Activity -> GameView
Activity --> RepeatListener 

class android.View {
    + onDraw()
}
class android.Activity {
    + onCreate()
    + onResume()
    + onPause()
}
android.Activity <|-- MainActivity
android.View <|-- GameView

```

