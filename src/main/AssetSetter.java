package main;

import object.OBJ_Key;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Chest;
import object.OBJ_Door;

public class AssetSetter {
    
    GamePanel gPanel;

    public AssetSetter(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void setObject() {
        
        gPanel.obj[0] = new OBJ_Key(gPanel);
        gPanel.obj[0].worldX = 23 * GamePanel.TILE_SIZE;
        gPanel.obj[0].worldY = 7 * GamePanel.TILE_SIZE;

        gPanel.obj[1] = new OBJ_Key(gPanel);
        gPanel.obj[1].worldX = 23 * GamePanel.TILE_SIZE;
        gPanel.obj[1].worldY = 40 * GamePanel.TILE_SIZE;

        gPanel.obj[2] = new OBJ_Key(gPanel);
        gPanel.obj[2].worldX = 37 * GamePanel.TILE_SIZE;
        gPanel.obj[2].worldY = 7 * GamePanel.TILE_SIZE;

        gPanel.obj[3] = new OBJ_Door(gPanel);
        gPanel.obj[3].worldX = 10 * GamePanel.TILE_SIZE;
        gPanel.obj[3].worldY = 11 * GamePanel.TILE_SIZE;

        gPanel.obj[4] = new OBJ_Door(gPanel);
        gPanel.obj[4].worldX = 8 * GamePanel.TILE_SIZE;
        gPanel.obj[4].worldY = 28 * GamePanel.TILE_SIZE;

        gPanel.obj[5] = new OBJ_Door(gPanel);
        gPanel.obj[5].worldX = 12 * GamePanel.TILE_SIZE;
        gPanel.obj[5].worldY = 22 * GamePanel.TILE_SIZE;

        gPanel.obj[6] = new OBJ_Chest(gPanel);
        gPanel.obj[6].worldX = 10 * GamePanel.TILE_SIZE;
        gPanel.obj[6].worldY = 7 * GamePanel.TILE_SIZE;

    }

    public void setNPC() {
        gPanel.npc[0] = new NPC_OldMan(gPanel);
        gPanel.npc[0].worldX = GamePanel.TILE_SIZE * 21;
        gPanel.npc[0].worldY = GamePanel.TILE_SIZE * 21;

    }

    public void setMonster() {
        gPanel.monster[0] = new MON_GreenSlime(gPanel);
        gPanel.monster[0].worldX = GamePanel.TILE_SIZE * 20;
        gPanel.monster[0].worldY = GamePanel.TILE_SIZE * 37;

        gPanel.monster[1] = new MON_GreenSlime(gPanel);
        gPanel.monster[1].worldX = GamePanel.TILE_SIZE * 26;
        gPanel.monster[1].worldY = GamePanel.TILE_SIZE * 40;
    }
}
