package main;

import object.OBJ_Key;
import object.OBJ_Chest;
import object.OBJ_Door;

public class AssetSetter {
    
    GamePanel gPanel;

    public AssetSetter(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void setObject() {
        
        gPanel.obj[0] = new OBJ_Key();
        gPanel.obj[0].worldX = 23 * gPanel.TILE_SIZE;
        gPanel.obj[0].worldY = 7 * gPanel.TILE_SIZE;

        gPanel.obj[1] = new OBJ_Key();
        gPanel.obj[1].worldX = 23 * gPanel.TILE_SIZE;
        gPanel.obj[1].worldY = 40 * gPanel.TILE_SIZE;

        gPanel.obj[2] = new OBJ_Key();
        gPanel.obj[2].worldX = 37 * gPanel.TILE_SIZE;
        gPanel.obj[2].worldY = 7 * gPanel.TILE_SIZE;

        gPanel.obj[3] = new OBJ_Door();
        gPanel.obj[3].worldX = 10 * gPanel.TILE_SIZE;
        gPanel.obj[3].worldY = 11 * gPanel.TILE_SIZE;

        gPanel.obj[4] = new OBJ_Door();
        gPanel.obj[4].worldX = 8 * gPanel.TILE_SIZE;
        gPanel.obj[4].worldY = 28 * gPanel.TILE_SIZE;

        gPanel.obj[5] = new OBJ_Door();
        gPanel.obj[5].worldX = 12 * gPanel.TILE_SIZE;
        gPanel.obj[5].worldY = 22 * gPanel.TILE_SIZE;

        gPanel.obj[6] = new OBJ_Chest();
        gPanel.obj[6].worldX = 10 * gPanel.TILE_SIZE;
        gPanel.obj[6].worldY = 7 * gPanel.TILE_SIZE;

    }
}
