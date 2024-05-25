package main;

import entity.Entity;

public class EventHandler {

    GamePanel gPanel;

    EventRectangle eventRectangle[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gPanel) {
        this.gPanel = gPanel;

        eventRectangle = new EventRectangle[GamePanel.MAX_MAP][GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];

        int col = 0;
        int row = 0;
        int map = 0;

        while (map < GamePanel.MAX_MAP && col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW) {
            eventRectangle[map][col][row] = new EventRectangle();
            eventRectangle[map][col][row].setX(23);
            eventRectangle[map][col][row].setY(23);
            eventRectangle[map][col][row].setWidth(2);
            eventRectangle[map][col][row].setHeight(2);
            eventRectangle[map][col][row].eventRectDefaultX = (int) eventRectangle[map][col][row].getX();
            eventRectangle[map][col][row].eventRectDefaultY = (int) eventRectangle[map][col][row].getY();

            col++;
            if (col == GamePanel.MAX_WORLD_COL) {
                col = 0;
                row++;

                if (row == GamePanel.MAX_WORLD_ROW) {
                    row = 0;
                    map++;
                }
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
            if (hit(0, 26, 16, "ANY") == true) {damagePit(26, 16, gPanel.dialogueState);}
            else if (hit(0, 23, 7, "ANY") == true) {healingPool(23, 7, gPanel.dialogueState);}
            else if (hit(0, 17, 45, "ANY") == true) {teleport(1, 23, 11);}
            else if (hit(1, 23, 11, "ANY") == true) {teleport(0, 17, 45);}

        }

    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        
        boolean hit = false;

        if (map == gPanel.currentMap) {

            gPanel.player.solidArea.setX(gPanel.player.worldX + gPanel.player.solidArea.getX());
            gPanel.player.solidArea.setY(gPanel.player.worldY + gPanel.player.solidArea.getY());
    
            eventRectangle[map][col][row].setX(col * GamePanel.TILE_SIZE + eventRectangle[map][col][row].getX());
            eventRectangle[map][col][row].setY(row * GamePanel.TILE_SIZE + eventRectangle[map][col][row].getY());
    
            if (gPanel.player.solidArea.getBoundsInLocal().intersects(eventRectangle[map][col][row].getBoundsInLocal()) 
            && eventRectangle[map][col][row].eventDone == false ) {
                if (gPanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("ANY")) {
                    hit = true;
    
                    previousEventX = gPanel.player.worldX;
                    previousEventY = gPanel.player.worldY;
                }
            }
    
            gPanel.player.solidArea.setX(gPanel.player.solidAreaDefaultX);
            gPanel.player.solidArea.setY(gPanel.player.solidAreaDefaultY);
            eventRectangle[map][col][row].setX(eventRectangle[map][col][row].eventRectDefaultX);
            eventRectangle[map][col][row].setY(eventRectangle[map][col][row].eventRectDefaultY);
        }

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

    public void teleport(int map, int col, int row) {

        gPanel.currentMap = map;
        gPanel.player.worldX = GamePanel.TILE_SIZE * col;
        gPanel.player.worldY = GamePanel.TILE_SIZE * row;
        previousEventX = gPanel.player.worldX;
        previousEventY = gPanel.player.worldY;
        canTouchEvent = false;
    }

}
