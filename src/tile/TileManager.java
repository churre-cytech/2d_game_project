package tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;

public class TileManager {

    GamePanel gPanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gPanel) {
        this.gPanel = gPanel;

        tile = new Tile[10];
        mapTileNum = new int[gPanel.WORLD_WIDTH][gPanel.WORLD_HEIGHT];

        getTileImage();
        loadMap("/map/world01.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = new Image("/tile/grass.png");

            tile[1] = new Tile();
            tile[1].image = new Image("/tile/wall.png");
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = new Image("/tile/water.png");
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = new Image("/tile/earth.png");

            tile[4] = new Tile();
            tile[4].image = new Image("/tile/tree.png");
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = new Image("/tile/sand.png");

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while (col < gPanel.MAX_WORLD_COL && row < gPanel.MAX_WORLD_ROW) {
                String line = br.readLine();
                while (col < gPanel.MAX_WORLD_COL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gPanel.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(GraphicsContext gc) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gPanel.MAX_WORLD_COL && worldRow < gPanel.MAX_WORLD_ROW) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gPanel.TILE_SIZE;
            int worldY = worldRow * gPanel.TILE_SIZE;
            int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
            int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;

            if (worldX + gPanel.TILE_SIZE > gPanel.player.worldX - gPanel.player.screenX &&
                worldX - gPanel.TILE_SIZE < gPanel.player.worldX + gPanel.player.screenX &&
                worldY + gPanel.TILE_SIZE > gPanel.player.worldY - gPanel.player.screenY && 
                worldY - gPanel.TILE_SIZE < gPanel.player.worldY + gPanel.player.screenY) 
                {
                    gc.drawImage(tile[tileNum].image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

                }


            worldCol++;

            if (worldCol == gPanel.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
