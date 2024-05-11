package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import object.SuperObject;
import tile.TileManager;
import entity.Player;

public class GamePanel extends Canvas
{

    // SCREEN SETTINGS
    public static final int TILE_SIZE = 48;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    // WORLD SETTINGS
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;
    public final int WORLD_WIDTH = MAX_WORLD_COL * TILE_SIZE;
    public final int WORLD_HEIGHT = MAX_WORLD_ROW * TILE_SIZE;

    private GraphicsContext gc;
    private AnimationTimer gameLoop;

    private ArrayList<String> inputList = new ArrayList<String>();

    public Player player = new Player(this);

    TileManager tileManager = new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);

    public SuperObject obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel()
    {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        gc = getGraphicsContext2D();

        // set up event handlers for key presses and releases
        setFocusTraversable(true); // enable keyboard input for the canvas
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent event)
    {
        String keyName = event.getCode().toString();
        if (!inputList.contains(keyName)) {
            inputList.add(keyName);
        }
    }

    private void handleKeyReleased(KeyEvent event)
    {
        String keyName = event.getCode().toString();
        inputList.remove(keyName);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameLoop()
    {
        AnimationTimer gameLoop = new AnimationTimer()
        {

            @Override
            public void handle(long now)
            {
                update();
                render();
            }
            
        };
        gameLoop.start();
    }

    private void update()
    {
        player.update(inputList);
    }

    private void render() {
        // clear canvas
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // draw background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        tileManager.render(gc);
        
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].render(gc, this);
            }
        }

        player.render(gc);
    }

    
}