package main;

import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import object.OBJ_Axe;
import object.OBJ_Bronze_Coin;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Heart;

public class AssetSetter {
    
    GamePanel gPanel;

    public AssetSetter(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public void setObject() {

        // OBJECT FOR MAP 0

        int mapNum = 0;
        int i = 0;

        gPanel.obj[mapNum][i] = new OBJ_Door(gPanel);
        gPanel.obj[mapNum][i].worldX = 12 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 22 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Door(gPanel);
        gPanel.obj[mapNum][i].worldX = 23 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 43 * GamePanel.TILE_SIZE;
        i++;


        gPanel.obj[mapNum][i] = new OBJ_Door(gPanel);
        gPanel.obj[mapNum][i].worldX = 8 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 22 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Door(gPanel);
        gPanel.obj[mapNum][i].worldX = 10 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 11 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Key(gPanel);
        gPanel.obj[mapNum][i].worldX = 23 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 7 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Chest(gPanel);
        gPanel.obj[mapNum][i].worldX = 10 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 7 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Key(gPanel);
        gPanel.obj[mapNum][i].worldX = 35 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 41 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Key(gPanel);
        gPanel.obj[mapNum][i].worldX = 37 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 9 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Shield_Wood(gPanel);
        gPanel.obj[mapNum][i].worldX = 31 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 13 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Heart(gPanel);
        gPanel.obj[mapNum][i].worldX = 35 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 27 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Axe(gPanel);
        gPanel.obj[mapNum][i].worldX = 33 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 7 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Potion_Red(gPanel);
        gPanel.obj[mapNum][i].worldX = 17 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 16 * GamePanel.TILE_SIZE;
        i++;

        gPanel.obj[mapNum][i] = new OBJ_Bronze_Coin(gPanel);
        gPanel.obj[mapNum][i].worldX = 15 * GamePanel.TILE_SIZE;
        gPanel.obj[mapNum][i].worldY = 13 * GamePanel.TILE_SIZE;
        i++;


        // OBJS FOR MAP 1
        mapNum = 1;
        i = 0;

    }

    public void setNPC() {

        // NPC FOR MAP 0

        int mapNum = 0;
        int i = 0;

        gPanel.npc[mapNum][i] = new NPC_OldMan(gPanel);
        gPanel.npc[mapNum][i].worldX = GamePanel.TILE_SIZE * 22;
        gPanel.npc[mapNum][i].worldY = GamePanel.TILE_SIZE * 21;
        i++;

        // NPC FOR MAP 1
        mapNum = 1;
        i = 0;

        gPanel.npc[mapNum][i] = new NPC_Merchant(gPanel);
        gPanel.npc[mapNum][i].worldX = GamePanel.TILE_SIZE * 23;
        gPanel.npc[mapNum][i].worldY = GamePanel.TILE_SIZE * 7;
        i++;

    }

    public void setMonster() {

        // MONSTER FOR MAP 0

        int mapNum = 0;
        int i = 0;

        gPanel.monster[mapNum][i] = new MON_GreenSlime(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 20;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 37;
        i++;

        gPanel.monster[mapNum][i] = new MON_GreenSlime(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 26;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 40;
        i++;

        gPanel.monster[mapNum][i] = new MON_GreenSlime(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 34;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 8;
        i++;

        
        gPanel.monster[mapNum][i] = new MON_GreenSlime(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 30;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 16;
        i++;


        gPanel.monster[mapNum][i] = new MON_GreenSlime(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 40;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 12;
        i++;


        gPanel.monster[mapNum][i] = new MON_Orc(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 33;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 38;
        i++;

        gPanel.monster[mapNum][i] = new MON_Orc(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 38;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 41;
        i++;

        gPanel.monster[mapNum][i] = new MON_Orc(gPanel);
        gPanel.monster[mapNum][i].worldX = GamePanel.TILE_SIZE * 10;
        gPanel.monster[mapNum][i].worldY = GamePanel.TILE_SIZE * 31;
        i++;




        // MONSTER FOR MAP 1

    }
}
