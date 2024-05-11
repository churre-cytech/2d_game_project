package entity;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Player extends Entity {

    GamePanel gPanel;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gPanel)
    {
        this.gPanel = gPanel;

        screenX = gPanel.SCREEN_WIDTH/2 - (gPanel.TILE_SIZE/2);
        screenY = gPanel.SCREEN_HEIGHT/2 - (gPanel.TILE_SIZE/2);

        solidArea = new Rectangle();
        solidArea.setX(8);
        solidArea.setY(16);
        solidArea.setWidth(32);
        solidArea.setHeight(32); 

        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;
        speed = 7;
        direction = "down";
    }

    public void getPlayerImage() 
    {
        try {
            up1 = new Image("/player/boy_up_1.png");
            up2 = new Image("/player/boy_up_2.png");
            down1 = new Image("/player/boy_down_1.png");
            down2 = new Image("/player/boy_down_2.png");
            left1 = new Image("/player/boy_left_1.png");
            left2 = new Image("/player/boy_left_2.png");
            right1 = new Image("/player/boy_right_1.png");
            right2 = new Image("/player/boy_right_2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ArrayList<String> inputList)
    {

        if (inputList.contains("LEFT") || inputList.contains("RIGHT") || inputList.contains("UP") || inputList.contains("DOWN")) 
        {
            if (inputList.contains("LEFT")) {
                direction = "LEFT";
            }
            if (inputList.contains("RIGHT")) {
                direction = "RIGHT";
            }
            if (inputList.contains("UP")) {
                direction = "UP";
            }
            if (inputList.contains("DOWN")) {
                direction = "DOWN";
            }
    

            // CHECK TILE COLLISION
            collisionOn = false;
            gPanel.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gPanel.cChecker.checkObject(this, true);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                
                switch (direction) {
                    case "UP":
                        worldY -= speed;
                    break;
                    case "DOWN":
                        worldY += speed;
                    break;
                    case "LEFT":
                        worldX -= speed;
                    break;
                    case "RIGHT":
                        worldX += speed;
                    break;
                    default:
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) 
            {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }

        // System.out.println(direction);
        System.out.println(inputList);

    }

    public void render(GraphicsContext gc) 
    {
        Image image = down1;

        switch (direction) {
            case "UP":
                if (spriteNum == 1) 
                    image = up1;
                if (spriteNum == 2) 
                    image = up2;
                break;
            case "DOWN":
                if (spriteNum == 1) 
                    image = down1;
                if (spriteNum == 2) 
                    image = down2;
                break;
            case "RIGHT":
                if (spriteNum == 1) 
                    image = right1;
                if (spriteNum == 2) 
                    image = right2;
                break;
            case "LEFT":
                if (spriteNum == 1) 
                    image = left1;
                if (spriteNum == 2) 
                    image = left2;
                break;
            default:
                break;
        }

        gc.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

}
