package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EventHandler {

    GamePanel gPanel;

    EventRectangle eventRectangle[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gPanel) {
        this.gPanel = gPanel;

        eventRectangle = new EventRectangle[gPanel.MAX_WORLD_COL][gPanel.MAX_WORLD_ROW];

        int col = 0;
        int row = 0;

        while (col < gPanel.MAX_WORLD_COL && row < gPanel.MAX_WORLD_ROW) {
            eventRectangle[col][row] = new EventRectangle();
            eventRectangle[col][row].setX(23);
            eventRectangle[col][row].setY(23);
            eventRectangle[col][row].setWidth(2);
            eventRectangle[col][row].setHeight(2);
            eventRectangle[col][row].eventRectDefaultX = (int) eventRectangle[col][row].getX();
            eventRectangle[col][row].eventRectDefaultY = (int) eventRectangle[col][row].getY();

            col++;
            if (col == gPanel.MAX_WORLD_COL) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {

        // CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
        int xDistance = Math.abs(gPanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gPanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        // DEBUG
        // System.out.println("Distance" + distance);
        // System.out.println("TILE" + GamePanel.TILE_SIZE);
        // System.out.println(canTouchEvent);

        if (distance > GamePanel.TILE_SIZE) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if (hit(26, 16, "ANY") == true) {damagePit(26, 16, gPanel.dialogueState);}
            if (hit(23, 7, "ANY") == true) {healingPool(23, 7, gPanel.dialogueState);}
        }

    }

    public boolean hit(int col, int row, String reqDirection) {
        
        boolean hit = false;

        gPanel.player.solidArea.setX(gPanel.player.worldX + gPanel.player.solidArea.getX());
        gPanel.player.solidArea.setY(gPanel.player.worldY + gPanel.player.solidArea.getY());

        eventRectangle[col][row].setX(col * GamePanel.TILE_SIZE + eventRectangle[col][row].getX());
        eventRectangle[col][row].setY(row * GamePanel.TILE_SIZE + eventRectangle[col][row].getY());

        if (gPanel.player.solidArea.getBoundsInLocal().intersects(eventRectangle[col][row].getBoundsInLocal()) 
        && eventRectangle[col][row].eventDone == false ) {
            if (gPanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("ANY")) {
                hit = true;

                previousEventX = gPanel.player.worldX;
                previousEventY = gPanel.player.worldY;
            }
        }

        gPanel.player.solidArea.setX(gPanel.player.solidAreaDefaultX);
        gPanel.player.solidArea.setY(gPanel.player.solidAreaDefaultY);
        eventRectangle[col][row].setX(eventRectangle[col][row].eventRectDefaultX);
        eventRectangle[col][row].setY(eventRectangle[col][row].eventRectDefaultY);

        return hit;
    }


    public void damagePit(int col, int row, int gameState) {
        gPanel.gameState = gameState;
        gPanel.ui.currentDialogue = "You fall into a pit !";
        gPanel.player.life -= 1;
        // eventRectangle[col][row].eventDone = true;
        canTouchEvent = false;

    }

    public void healingPool(int col, int row, int gameState) {

        if (gPanel.keyHandler.enterPressed == true) {
            gPanel.gameState = gameState;
            gPanel.player.attackCanceled = true;
            gPanel.ui.currentDialogue = "You drink the water.\nYour life has been recovered !";
            gPanel.player.life = gPanel.player.maxLife;
        }
    }

}
