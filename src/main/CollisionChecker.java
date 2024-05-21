package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gPanel;
    
    public CollisionChecker(GamePanel gPanel) {
        this.gPanel = gPanel;
    } 

    public void checkTile(Entity entity) {

        int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.getX());
        int entityRightWorldX = (int) (entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY = (int) (entity.worldY + entity.solidArea.getY());
        int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = entityLeftWorldX/gPanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX/gPanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY/gPanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/gPanel.TILE_SIZE;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "UP":
                entityTopRow = (int) ((entityTopWorldY - entity.speed)/gPanel.TILE_SIZE);
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                
                break;

            case "DOWN":
                entityBottomRow = (int) ((entityBottomWorldY + entity.speed)/gPanel.TILE_SIZE);
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
        
            break;

            case "RIGHT":
                entityRightCol = (int) ((entityRightWorldX + entity.speed)/gPanel.TILE_SIZE);
                tileNum1 = gPanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

            
            break;

            case "LEFT":
                entityLeftCol = (int) ((entityLeftWorldX - entity.speed)/gPanel.TILE_SIZE);
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        
            default:
                break;
        }

    }

    public int checkObject(Entity entity, boolean player) {
            
        int index = 999; 

        for (int i = 0; i < gPanel.obj.length; i++) {

            if (gPanel.obj[i] != null) {
                // get entity solid area position
                entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
                entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

                // get object solid area position 
                gPanel.obj[i].solidArea.setX(gPanel.obj[i].worldX + gPanel.obj[i].solidArea.getX());
                gPanel.obj[i].solidArea.setY(gPanel.obj[i].worldY + gPanel.obj[i].solidArea.getY());

                switch (entity.direction) {
                    case "UP":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        break;
                    case "DOWN":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        break;
                    case "LEFT":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        break;
                    case "RIGHT":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        break;
                    default:
                        break;
                }
                if (entity.solidArea.getBoundsInLocal().intersects(gPanel.obj[i].solidArea.getBoundsInLocal())) {
                    if (gPanel.obj[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }


                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                gPanel.obj[i].solidArea.setX(gPanel.obj[i].solidAreaDefaultX);
                gPanel.obj[i].solidArea.setY(gPanel.obj[i].solidAreaDefaultY);
            }
        }
        
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {

        int index = 999; 

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {
                // get entity solid area position
                entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
                entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

                // get target solid area position 
                target[i].solidArea.setX(target[i].worldX + target[i].solidArea.getX());
                target[i].solidArea.setY(target[i].worldY + target[i].solidArea.getY());

                switch (entity.direction) {
                    case "UP":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        break;
                    case "DOWN":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        break;
                    case "LEFT":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        break;
                    case "RIGHT":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        break;
                    default:
                        break;
                }
                if (entity.solidArea.getBoundsInLocal().intersects(target[i].solidArea.getBoundsInLocal())) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }


                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                target[i].solidArea.setX(target[i].solidAreaDefaultX);
                target[i].solidArea.setY(target[i].solidAreaDefaultY);
            }
        }
        
        return index;
    
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        // get entity solid area position
        entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
        entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

        // get target solid area position 
        gPanel.player.solidArea.setX(gPanel.player.worldX + gPanel.player.solidArea.getX());
        gPanel.player.solidArea.setY(gPanel.player.worldY + gPanel.player.solidArea.getY());

        switch (entity.direction) {
            case "UP":
                entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                break;
            case "DOWN":
                entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                break;
            case "LEFT":
                entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                break;
            case "RIGHT":
                entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                break;

            default:
                break;
        }
        if (entity.solidArea.getBoundsInLocal().intersects(gPanel.player.solidArea.getBoundsInLocal())) {
            entity.collisionOn = true;
            contactPlayer = true;
        }


        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);
        gPanel.player.solidArea.setX(gPanel.player.solidAreaDefaultX);
        gPanel.player.solidArea.setY(gPanel.player.solidAreaDefaultY);

        return contactPlayer;
    }

}
