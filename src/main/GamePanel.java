package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tile.TileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Entity;
import entity.Player;

public class GamePanel extends Canvas
{

    // SCREEN SETTINGS
    public static final int TILE_SIZE = 48;
    public static final int MAX_SCREEN_COL = 18;
    public static final int MAX_SCREEN_ROW = 14;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = MAX_WORLD_COL * TILE_SIZE;
    public static final int WORLD_HEIGHT = MAX_WORLD_ROW * TILE_SIZE;

    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    public UI ui = new UI(this);

    public final GameKeyHandler keyHandler = new GameKeyHandler(this);
    public EventHandler eventHandler = new EventHandler(this);

    public Player player = new Player(this);

    TileManager tileManager = new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);

    // OBJECT
    public Entity obj[] = new Entity[20];
    public AssetSetter aSetter = new AssetSetter(this);

    // NPC
    public Entity npc[] = new Entity[10];

    // MONSTER
    public Entity monster[] = new Entity[10];

    // FOR THE DRAWING ORDER
    ArrayList<Entity> entityList = new ArrayList<Entity>();

    // GAME STATES
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel()
    {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        gc = getGraphicsContext2D();

        setFocusTraversable(true);
        addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
    }


    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = playState;
    }

    public void startGameLoop()
    {
        gameLoop = new AnimationTimer()
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

    private void update() {

        // PLAY STATE
        if (gameState == playState) {

            player.update(keyHandler);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true) {
                        monster[i].update();
                    } else {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
        }

        // PAUSE STATE
        if (gameState == pauseState) {
            // TODO
        }
        
    }

    private void render() {

        // CLEANING MAP
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        tileManager.render(gc);

        // MANAGING THE ENTITIES FOR THE RENDER ORDER
        entityList.add(player);

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                entityList.add(npc[i]);
            }
        }

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                entityList.add(monster[i]);
            }
        }

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                entityList.add(obj[i]);
            }
        }

        // SORT
        Collections.sort(entityList, new Comparator<Entity>() {

            @Override
            public int compare(Entity e1, Entity e2) {
                
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
            
        });

        // DRAW ENTITIES
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).render(gc);
        }
        // EMPTY ENTITIES LIST
        entityList.clear();

        ui.render(gc);

        // DEBUG
        if (keyHandler.showTextDebug == true) {

            gc.setFont(new Font("Arial", 24));
            gc.setFill(Color.WHITE);

            int x = 10;
            int y = 400;
            int lineHeight = 40;

            gc.fillText("WorldX : " + player.worldX , x, y);
            y += lineHeight;
            gc.fillText("WorldY : " + player.worldY , x, y);
            y += lineHeight;
            gc.fillText("Col : " + (int) (player.worldX + player.solidArea.getX()) / TILE_SIZE, x, y);
            y += lineHeight;
            gc.fillText("Row : " + (int) (player.worldY + player.solidArea.getY()) / TILE_SIZE, x, y);
            y += lineHeight;
        }
    }

    
}