package main;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import object.OBJ_Heart;

public class UI {

    GamePanel gPanel;
    GraphicsContext gc;

    Image heart_full, heart_half, heart_blank;

    public String currentDialogue = "";

    public int subState;
    public int commandNum;

    // CURSOR
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    public Entity npc;

    public UI(GamePanel gPanel) {

        this.gPanel = gPanel;

        Entity heart = new OBJ_Heart(gPanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void render(GraphicsContext gc) {

        this.gc = gc;

        drawPlayerLife();

        if (gPanel.gameState == gPanel.playState) {

        } else if (gPanel.gameState == gPanel.pauseState) {
            drawPauseScreen();
        } else if (gPanel.gameState == gPanel.dialogueState) {
            drawDialogueScreen();
        } else if (gPanel.gameState == gPanel.characterState) {
            drawCharacterScreen();
            drawInventory(gPanel.player, true);
        } else if (gPanel.gameState == gPanel.tradeState) {
            drawTradeScreen();
        } else if (gPanel.gameState == gPanel.gameOverState) {
            drawGameOverScreen();
        } else if (gPanel.gameState == gPanel.victoryState) {
            drawVictoryScreen();
        }
    }

    public void drawVictoryScreen() {

        // VICTORY
        gc.setFill(Color.WHITE);

        String textString = "VICTORY";
        Font font = Font.font("Arial", 56);
        Text text = new Text(textString);
        text.setFont(font);

        double textWidth = text.getLayoutBounds().getWidth();

        int y = GamePanel.SCREEN_HEIGHT/2;
        int x = (int)(GamePanel.SCREEN_WIDTH/2 - textWidth/2);

        gc.setFont(font);
        gc.fillText(textString, x, y);

        // RETRY
        y += GamePanel.TILE_SIZE * 3;

        gc.setFill(Color.WHITE);

        textString = "RETRY";
        font = Font.font("Arial", 40);
        text = new Text(textString);
        text.setFont(font);

        textWidth = text.getLayoutBounds().getWidth();
        x = (int)(GamePanel.SCREEN_WIDTH/2 - textWidth/2);

        gc.setFont(font);
        gc.fillText(textString, x, y);
        if (commandNum == 0) {
            gc.fillText(">", x - 40 , y);
        }
    }


    public void drawGameOverScreen() {

        // GAME OVER
        gc.setFill(Color.WHITE);

        String textString = "GAME OVER";
        Font font = Font.font("Arial", 56);
        Text text = new Text(textString);
        text.setFont(font);

        double textWidth = text.getLayoutBounds().getWidth();

        int y = GamePanel.SCREEN_HEIGHT/2;
        int x = (int)(GamePanel.SCREEN_WIDTH/2 - textWidth/2);

        gc.setFont(font);
        gc.fillText(textString, x, y);

        // RETRY
        y += GamePanel.TILE_SIZE * 3;

        gc.setFill(Color.WHITE);

        textString = "RETRY";
        font = Font.font("Arial", 40);
        text = new Text(textString);
        text.setFont(font);

        textWidth = text.getLayoutBounds().getWidth();
        x = (int)(GamePanel.SCREEN_WIDTH/2 - textWidth/2);

        gc.setFont(font);
        gc.fillText(textString, x, y);
        if (commandNum == 0) {
            gc.fillText(">", x - 40 , y);
        }

    }

    public void drawTradeScreen() {

        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_receive(); break;
            case 2: trade_give(); break;
            default: break;
        }
        gPanel.keyHandler.enterPressed = false;
    }

    public void trade_select() {
        drawDialogueScreen();

        // DRAW SELECT WINDOW
        int x = GamePanel.TILE_SIZE * 12;
        int y = GamePanel.TILE_SIZE * 6;
        int width = GamePanel.TILE_SIZE * 3;
        int height = GamePanel.TILE_SIZE * 4;
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));

        // BUY
        x += GamePanel.TILE_SIZE - 10 ;
        y += GamePanel.TILE_SIZE;
        gc.fillText("Recevoir", x, y);
        if (commandNum == 0) {
            gc.fillText(">", x-24, y);
            if (gPanel.keyHandler.enterPressed  == true) {
                subState = 1;
            }
        }
        y += GamePanel.TILE_SIZE;

        // SELL 
        gc.fillText("Donner", x, y);
        if (commandNum == 1) {
            gc.fillText(">", x-24, y);
            if (gPanel.keyHandler.enterPressed  == true) {
                subState = 2;
            }
        }
        y += GamePanel.TILE_SIZE;

        // LEAVE 
        gc.fillText("Quitter", x, y);
        if (commandNum == 2) {
            gc.fillText(">", x-24, y);
            if (gPanel.keyHandler.enterPressed  == true) {
                commandNum = 0;
                gPanel.gameState = gPanel.dialogueState;
                currentDialogue = "Au revoir !";
            }
        }
        y += GamePanel.TILE_SIZE;
    }

    public void trade_receive() {

        // DRAW PLAYER INVENTORY
        drawInventory(gPanel.player, false);

        // DRAW PLAYER INVENTORY
        if (npc != null) {
            drawInventory(npc, true);
        } else {
            drawSubWindow(GamePanel.TILE_SIZE * 2, GamePanel.TILE_SIZE * 2, GamePanel.TILE_SIZE * 7, GamePanel.TILE_SIZE * 5);
            gc.setFill(Color.WHITE);
            gc.fillText("Empty", GamePanel.TILE_SIZE * 2 + 48, GamePanel.TILE_SIZE * 2 + 48);
        }

        int itemIndex = getIndexItemOnSlot(npcSlotCol, npcSlotRow);

        if (itemIndex < npc.inventory.size()) {

            if (gPanel.keyHandler.enterPressed == true) {

                if (gPanel.player.inventory.size() > gPanel.player.maxInventorySize) {
                    subState = 0;
                    gPanel.gameState = gPanel.dialogueState;
                    currentDialogue = "Votre inventaire est plein, bonne journée !";
                } else {
                    gPanel.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }

        }
        


    }

    public void trade_give() {

        // DRAW PLAYER INVENTORY
        drawInventory(gPanel.player, true);

        int itemIndex = getIndexItemOnSlot(playerSlotCol, playerSlotRow);

        // DEBUG -> itemIndex issue
        // System.out.println(itemIndex);
        // System.out.println("SIZE" + gPanel.player.inventory.size());

        if (itemIndex < gPanel.player.inventory.size()) {

            if (gPanel.keyHandler.enterPressed == true) {

                if (gPanel.player.inventory.get(itemIndex) == gPanel.player.currentWeapon ||
                gPanel.player.inventory.get(itemIndex) == gPanel.player.currentShield) {
    
                    commandNum = 0;
                    subState = 0;
                    gPanel.gameState = gPanel.dialogueState;
                    currentDialogue = "Vous équipez cet item !";
                } else {
                    gPanel.player.inventory.remove(itemIndex);
                }
            }        
        }

    }

    public void drawInventory(Entity entity, boolean cursor) {

        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;

        if (entity == gPanel.player) {
            frameX = GamePanel.TILE_SIZE * 10;
            frameY = GamePanel.TILE_SIZE * 2;
            frameWidth = GamePanel.TILE_SIZE * 7;
            frameHeight = GamePanel.TILE_SIZE * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = GamePanel.TILE_SIZE * 2;
            frameY = GamePanel.TILE_SIZE * 2;
            frameWidth = GamePanel.TILE_SIZE * 7;
            frameHeight = GamePanel.TILE_SIZE * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        // DRAW PLAYER'S ITEM
        for (int i = 0; i < entity.inventory.size(); i++) {

            if (entity.inventory.get(i) == entity.currentWeapon ||
                entity.inventory.get(i) == entity.currentShield)
            {
                gc.setFill(Color.YELLOW);
                gc.fillRoundRect(slotX, slotY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, 10, 10);
            }

            gc.drawImage(entity.inventory.get(i).down1, slotX, slotY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

            slotX += GamePanel.TILE_SIZE;

            if (i == 5 || i == 10) {
                slotX = slotXStart;
                slotY += GamePanel.TILE_SIZE;
            }
        }

        // CURSOR
        if (cursor == true) {
            int cursorX = slotXStart + (GamePanel.TILE_SIZE * slotCol);
            int cursorY = slotYStart + (GamePanel.TILE_SIZE * slotRow);
            int cursorWidth = GamePanel.TILE_SIZE;
            int cursorHeight = GamePanel.TILE_SIZE;
    
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        }
    }

    public void drawCharacterScreen() {
        
        // CREATE A FRAME
        final int frameX = GamePanel.TILE_SIZE;
        final int frameY = GamePanel.TILE_SIZE * 2;
        final int frameWidth = GamePanel.TILE_SIZE * 6;
        final int frameHeight = GamePanel.TILE_SIZE * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));

        int textX = frameX + 20;
        int textY = frameY + GamePanel.TILE_SIZE;
        final int lineHeight = 40;

        // NAMES
        gc.fillText("Life", textX, textY);
        textY += lineHeight;
        gc.fillText("Strength", textX, textY);
        textY += lineHeight;
        gc.fillText("Dexterity", textX, textY);
        textY += lineHeight;
        gc.fillText("Coin", textX, textY);
        textY += lineHeight;
        gc.fillText("Attack", textX, textY);
        textY += lineHeight;
        gc.fillText("Defense", textX, textY);
        textY += lineHeight;
        gc.fillText("Key(s)", textX, textY);
        textY += lineHeight;
        gc.fillText("Current Weapon", textX, textY);
        textY += lineHeight;
        gc.fillText("Current Shield", textX, textY);
        textY += lineHeight;

        // TESTING
        gc.fillText("attackArea.width", textX, textY);
        textY += lineHeight;
        gc.fillText("attackArea.height", textX, textY);
        textY += lineHeight;




        // VALUES
        int tailX = (frameX + frameWidth) - 40;
        // RESET textY
        textY = frameY + GamePanel.TILE_SIZE;
        String value;

        value = String.valueOf(gPanel.player.life);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.strength);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.dexterity);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.coin);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.attack);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.defense);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.hasKey);
        gc.fillText(value, tailX, textY);
        textY += lineHeight;

        if (gPanel.player.currentWeapon == null) {
            gc.fillText("No weapon", tailX, textY);
            textY += lineHeight;
        } else {
            gc.drawImage(gPanel.player.currentWeapon.down1, tailX - 15, textY - 30, 40, 40);
            textY += lineHeight;
        }

        if (gPanel.player.currentShield == null) {
            gc.fillText("No shield", tailX, textY);
            textY += lineHeight;
        } else {
            gc.drawImage(gPanel.player.currentShield.down1, tailX - 15, textY - 30, 40, 40);
            textY += lineHeight;
        }

        value = String.valueOf(gPanel.player.attackArea.getWidth());
        gc.fillText(value, tailX - 15, textY);
        textY += lineHeight;

        value = String.valueOf(gPanel.player.attackArea.getHeight());
        gc.fillText(value, tailX - 15, textY );
        

    }

    public void drawDialogueScreen() {

        Font font = Font.font("Arial", 30);

        // WINDOW
        int x = GamePanel.TILE_SIZE * 2;
        int y = GamePanel.TILE_SIZE / 2; 
        int width = GamePanel.SCREEN_WIDTH - (GamePanel.TILE_SIZE * 4);
        int height = GamePanel.TILE_SIZE * 5;

        drawSubWindow(x, y, width, height);

        x += GamePanel.TILE_SIZE;
        y += GamePanel.TILE_SIZE;

        gc.setFont(font);
        gc.setFill(Color.WHITE);


        for (String line : currentDialogue.split("\n")) {
            gc.fillText(line, x, y);
            y += GamePanel.TILE_SIZE;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        
        Color color = Color.rgb(0, 0, 0, 0.80);
        gc.setFill(color);
        gc.fillRoundRect(x, y, width, height, 35, 35);

        color = Color.rgb(255, 255, 255);
        gc.setStroke(color);
        gc.setLineWidth(5);
        gc.strokeRoundRect(x, y, width, height, 25, 25);
    }

    public void drawPauseScreen() {

        gc.setFill(Color.WHITE);

        String textString = "PAUSED";
        Font font = Font.font("Arial", 40);
        Text text = new Text(textString);
        text.setFont(font);

        double textWidth = text.getLayoutBounds().getWidth();

        int y = GamePanel.SCREEN_HEIGHT/2;
        int x = (int)(GamePanel.SCREEN_WIDTH/2 - textWidth/2);

        gc.setFont(font);
        gc.fillText(textString, x, y);

    }

    public void drawPlayerLife() {

        int x = GamePanel.TILE_SIZE/2;
        int y = GamePanel.TILE_SIZE/2;
        int i = 0;

        // DRAW BLANK HEARTS
        while (i < gPanel.player.maxLife/2) {
            gc.drawImage(heart_blank, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            i++;
            x += GamePanel.TILE_SIZE;
        }

        // RESET
        x = GamePanel.TILE_SIZE / 2;
        y = GamePanel.TILE_SIZE / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gPanel.player.life) {
            gc.drawImage(heart_half, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            i++;
            if (i < gPanel.player.life) {
                gc.drawImage(heart_full, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            }
            i++;
            x += GamePanel.TILE_SIZE;
        }

    }

    public int getIndexItemOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 6);
        return itemIndex;
    }
}
