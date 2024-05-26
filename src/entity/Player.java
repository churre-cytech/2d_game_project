package entity;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GameKeyHandler;
import main.GamePanel;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;

    public boolean attackCanceled = false;

    public Player(GamePanel gPanel)
    {
        super(gPanel);

        screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
        screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);

        solidArea = new Rectangle();
        solidArea.setX(8);
        solidArea.setY(16);
        solidArea.setWidth(32);
        solidArea.setHeight(32); 

        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues()
    {
        // SPAWN MAP 0
        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;

        // SPAWN MAP 1
        // worldX = GamePanel.TILE_SIZE * 23;
        // worldY = GamePanel.TILE_SIZE * 11;

        speed = 6;
        direction = "DOWN";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gPanel);
        currentShield = new OBJ_Shield_Wood(gPanel);
        attack = getAttack();
        defense = getDefense();
        hasKey = 0;
    }

    public void setDefaultPositions() {

        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;
        direction = "DOWN";
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }

    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Potion_Red(gPanel));
        inventory.add(new OBJ_Potion_Red(gPanel));
    }

    public void selectItem() {
        
        int indexItem = gPanel.ui.getIndexItemOnSlot(gPanel.ui.playerSlotCol, gPanel.ui.playerSlotRow);

        if (indexItem < inventory.size()) {

            Entity selectedItem = inventory.get(indexItem);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(indexItem);
            }
        }
    }
    
    public int getAttack() {
        // return attack = strength * currentWeapon.attackValue;
        attackArea = currentWeapon.attackArea;
        return attack = currentWeapon.attackValue;
    }

    public int getDefense() {
        // return defense = dexterity * currentShield.defenseValue;
        return defense = currentShield.defenseValue;
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

    public void getPlayerAttackImage() {
        try {
            attackUp1 = new Image("/player/boy_attack_up_1.png");
            attackUp2 = new Image("/player/boy_attack_up_2.png");
            attackDown1 = new Image("/player/boy_attack_down_1.png");
            attackDown2 = new Image("/player/boy_attack_down_2.png");
            attackLeft1 = new Image("/player/boy_attack_left_1.png");
            attackLeft2 = new Image("/player/boy_attack_left_2.png");
            attackRight1 = new Image("/player/boy_attack_right_1.png");
            attackRight2 = new Image("/player/boy_attack_right_2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(GameKeyHandler keyHandler)
    {
        if (attacking == true) {
            attacking();
        }
        else if (keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveLeft() || keyHandler.isMoveRight() || keyHandler.isEnterPressed()) 
        {
            if (keyHandler.isMoveUp()) {
                direction = "UP";
            } else if (keyHandler.isMoveDown()) {
                direction = "DOWN";
            } else if (keyHandler.isMoveLeft()) {
                direction = "LEFT";
            } else if (keyHandler.isMoveRight()) {
                direction = "RIGHT";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gPanel.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gPanel.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gPanel.cChecker.checkEntity(this, gPanel.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gPanel.cChecker.checkEntity(this, gPanel.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gPanel.eventHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyHandler.enterPressed == false) {
                
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

            if (keyHandler.enterPressed == true && attackCanceled == false) {
                attacking = true;
                spriteCounter = 0;
            }

            // RESET ENTER
            attackCanceled = false;
            keyHandler.enterPressed = false;            

            spriteCounter++;
            if (spriteCounter > 12) 
            {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            // GAME OVER BY DEATH
            if (life <= 0) {
                gPanel.gameState = gPanel.gameOverState;
            }
        }

        // This need to be outside of the key if statement !
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void attacking() {
        
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        } else if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = (int) solidArea.getWidth();
            int solidAreaHeight = (int) solidArea.getHeight();

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "UP":
                    worldY -= attackArea.getHeight();                    
                    break;
                case "DOWN":
                    worldY += attackArea.getHeight();                    
                    break;
                case "LEFT":
                    worldX -= attackArea.getWidth();                    
                    break;
                case "RIGHT":
                    worldX += attackArea.getWidth();                    
                    break;
                default:
                    break;
            }

            // attackArea becomes solidArea
            solidArea.setWidth(attackArea.getWidth());
            solidArea.setHeight(attackArea.getHeight());

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gPanel.cChecker.checkEntity(this, gPanel.monster);
            damageMonster(monsterIndex);


            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.setWidth(solidAreaWidth);
            solidArea.setHeight(solidAreaHeight);


        } else if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void contactMonster(int monsterIndex) {

        if (monsterIndex != 999) {

            int damage = gPanel.monster[gPanel.currentMap][monsterIndex].attack - defense;
            if (damage < 0) {
                damage = 0;
            }
            
            if (invincible == false) {
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {

        
        if (i != 999) {


            if (gPanel.monster[gPanel.currentMap][i].invincible == false) {

                int damage = attack - gPanel.monster[gPanel.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gPanel.monster[gPanel.currentMap][i].life -= damage;
                gPanel.monster[gPanel.currentMap][i].invincible = true;
                gPanel.monster[gPanel.currentMap][i].damageReaction();

                if (gPanel.monster[gPanel.currentMap][i].life <= 0) {
                    gPanel.monster[gPanel.currentMap][i].alive = false;
                }

                System.out.println("PV de " + gPanel.monster[gPanel.currentMap][i].name + " : " + gPanel.monster[gPanel.currentMap][i].life + ".");
                System.out.println("Inventaire" + gPanel.monster[gPanel.currentMap][i].name + " obj : " + gPanel.monster[gPanel.currentMap][i].inventory);
            }
        } else {
        }
    }


    public void interactNPC(int i) {

        if (gPanel.keyHandler.isEnterPressed() == true) {

            if (i != 999) {

                // DEBUG
                // System.out.println("You are hitting some NPC, press enter to speak with him !");
                // System.out.println(i);

                attackCanceled = true;
                gPanel.gameState = gPanel.dialogueState;
                gPanel.npc[gPanel.currentMap][i].speak();
            } else {
                    attacking = true;
            }
        }
    }

    public void pickUpObject(int i) {

        String objName;
        String text;
        
        if (i != 999) { 

            if (gPanel.obj[gPanel.currentMap][i].type == type_pickUpOnly) {
                gPanel.obj[gPanel.currentMap][i].use(this);
                gPanel.obj[gPanel.currentMap][i] = null;
            } else if (gPanel.obj[gPanel.currentMap][i].type == type_fixItem) {
                objName = gPanel.obj[gPanel.currentMap][i].name;
                switch (objName) {
                    case "Door":
                        if (hasKey > 0) {
                            gPanel.obj[gPanel.currentMap][i] = null;
                            hasKey--;
                            text = "Porte ouverte avec succès !";
                        } else {
                            text = "Vous avez besoin d'une clé !";
                        }
                        gPanel.gameState = gPanel.dialogueState;
                        gPanel.ui.currentDialogue = text;
                        break;
                    case "Chest":
                        text = "Vous êtes face à un coffre,\nVous avez gagné !";
                        gPanel.gameState = gPanel.dialogueState;
                        gPanel.ui.currentDialogue = text;
                        gPanel.gameState = gPanel.victoryState;
                        break;
                    default:
                        break;
                }
            } else {

                if (inventory.size() != maxInventorySize) {
                    inventory.add(gPanel.obj[gPanel.currentMap][i]);
                    text = "Vous avez ramassé " + gPanel.obj[gPanel.currentMap][i].name + " !";
                    System.out.println(text);
                    gPanel.obj[gPanel.currentMap][i] = null;
                } else {
                    text = "Votre inventaire est plein !";
                    gPanel.gameState = gPanel.dialogueState;
                    gPanel.ui.currentDialogue = text;
                }
            }
        }
}

    public void render(GraphicsContext gc) 
    {
        Image image = down1;

        switch (direction) {
            case "UP":
                if (attacking == false) {
                    image = spriteNum == 1 ? up1 : up2;
                } else {
                    image = spriteNum == 1 ? attackUp1 : attackUp2;
                }
                break;
            case "DOWN":
                if (attacking == false) {
                    image = spriteNum == 1 ? down1 : down2;
                } else {
                    image = spriteNum == 1 ? attackDown1 : attackDown2;
                }
                break;
            case "LEFT":
                if (attacking == false) {
                    image = spriteNum == 1 ? left1 : left2;
                } else {
                    image = spriteNum == 1 ? attackLeft1 : attackLeft2;
                }
                break;
            case "RIGHT":
                if (attacking == false) {
                    image = spriteNum == 1 ? right1 : right2;
                } else {
                    image = spriteNum == 1 ? attackRight1 : attackRight2;
                }
                break;
            default:
                break;
        }

        // EFFECT WHEN YOU ARE INVINCIBLE
        double originalAlpha = gc.getGlobalAlpha();
        if (invincible == true) {
            gc.setGlobalAlpha(0.3);
        }
        gc.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        gc.setGlobalAlpha(originalAlpha);


        // DEBUG
        // gc.setFont(new Font("Arial", 20));
        // gc.setFill(Color.WHITE);
        // gc.fillText("Invincible : " + invincibleCounter, 10, 400);
    }

    

}
