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

    // CURSOR
    public int slotCol = 0;
    public int slotRow = 0;

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
            drawInventory();
        }
    }

    public void drawInventory() {

        // FRAME
        int frameX = GamePanel.TILE_SIZE * 10;
        int frameY = GamePanel.TILE_SIZE * 2;
        int frameWidth = GamePanel.TILE_SIZE * 7;
        int frameHeight = GamePanel.TILE_SIZE * 5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        // CURSOR
        int cursorX = slotXStart + (GamePanel.TILE_SIZE * slotCol);
        int cursorY = slotYStart + (GamePanel.TILE_SIZE * slotRow);
        int cursorWidth = GamePanel.TILE_SIZE;
        int cursorHeight = GamePanel.TILE_SIZE;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

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
        final int lineHeight = 32;

        // NAMES
        gc.fillText("Life", textX, textY);
        textY += 40;
        gc.fillText("Strength", textX, textY);
        textY += 40;
        gc.fillText("Dexterity", textX, textY);
        textY += 40;
        gc.fillText("Attack", textX, textY);
        textY += 40;
        gc.fillText("Defense", textX, textY);
        textY += 40;
        gc.fillText("Current Weapon", textX, textY);
        textY += 40;
        gc.fillText("Current Shield", textX, textY);
        textY += 40;


        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // RESET textY
        textY = frameY + GamePanel.TILE_SIZE;
        String value;

        value = String.valueOf(gPanel.player.life);
        gc.fillText(value, tailX, textY);
        textY += 40;

        value = String.valueOf(gPanel.player.strength);
        gc.fillText(value, tailX, textY);
        textY += 40;

        value = String.valueOf(gPanel.player.dexterity);
        gc.fillText(value, tailX, textY);
        textY += 40;

        value = String.valueOf(gPanel.player.attack);
        gc.fillText(value, tailX, textY);
        textY += 40;

        value = String.valueOf(gPanel.player.attack);
        gc.fillText(value, tailX, textY);
        // textY += 40;

        gc.drawImage(gPanel.player.currentWeapon.down1, tailX - 15, textY + 10, 40, 40);
        textY += 40;

        gc.drawImage(gPanel.player.currentShield.down1, tailX - 15, textY + 10, 40, 40);

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
}
