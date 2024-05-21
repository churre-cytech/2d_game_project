package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.GamePanel;

public class Entity {

    public GamePanel gPanel;

    public int worldX;
    public int worldY;

    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "DOWN";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    String dialoguesLines[] = new String[20];
    int dialogueIndex = 0;

    // CHARACTER STATUS
    public boolean alive = true;
    public boolean dying = false;

    // CHARACTER ATTRIBUTES
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int life;
    public int maxLife;
    public int attack;
    public int defense;
    public int strength;
    public int dexterity;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    // ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;

    // COMBAT
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean attacking = false;

    // FROM SUPEROBJECT
    public Image image, image2, image3;
    public boolean collision = false;


    public Entity(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void render(GraphicsContext gc) {

        Image image = null;

        int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
        int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;

        if (worldX + GamePanel.TILE_SIZE > gPanel.player.worldX - gPanel.player.screenX &&
            worldX - GamePanel.TILE_SIZE < gPanel.player.worldX + gPanel.player.screenX &&
            worldY + GamePanel.TILE_SIZE > gPanel.player.worldY - gPanel.player.screenY && 
            worldY - GamePanel.TILE_SIZE < gPanel.player.worldY + gPanel.player.screenY) 
            {

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
        

                // EFFECT ENTITY INVINCIBLE
                double originalAlpha = gc.getGlobalAlpha();
                if (invincible == true) {
                    gc.setGlobalAlpha(0.3);
                }
                gc.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                gc.setGlobalAlpha(originalAlpha);


                // DEBUG
                // gc.setFont(new Font("Arial", 20));
                // gc.setFill(Color.WHITE);
            }
    }

    public void setAction() {}
    public void damageReaction() {}

    public void speak() {
        if (dialoguesLines[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gPanel.ui.currentDialogue = dialoguesLines[dialogueIndex];
        dialogueIndex++;

        switch (gPanel.player.direction) {
            case "UP":
                direction = "DOWN";                
                break;
            case "DOWN":
                direction = "UP";                
                break;
            case "LEFT":
                direction = "RIGHT";
                break;            
            case "RIGHT":
                direction = "LEFT";
                break;
            default:
                break;
        }
    }
    
    public void update() {

        setAction();

        collisionOn = false;
        gPanel.cChecker.checkTile(this);
        gPanel.cChecker.checkObject(this, false);
        gPanel.cChecker.checkEntity(this, gPanel.npc);
        gPanel.cChecker.checkEntity(this, gPanel.monster);
        boolean contactPlayer = gPanel.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {


            if (gPanel.player.invincible == false) {
                // We can give damages
                int damage = attack - gPanel.player.defense;
                if (damage < 0) {
                    damage = 0;
                }
                    
                gPanel.player.life -= damage;
                gPanel.player.invincible = true;
            }
        }

        // IF COLLISION IS FALSE, ENTITY CAN MOVE
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

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }


    
}
