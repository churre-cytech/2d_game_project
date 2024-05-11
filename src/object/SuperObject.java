package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class SuperObject {

        public Image image;
        public String name;
        public boolean collision = false;
        public int worldX;
        public int worldY;
        public Rectangle solidArea = new Rectangle(0, 0, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE); // define for all objects but if you want
                                                                                                    // for each object -> OBJ_xxx
        public int solidAreaDefaultX = 0;
        public int solidAreaDefaultY = 0;


        public void render(GraphicsContext gc, GamePanel gPanel) {

            int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
            int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;

            if (worldX + gPanel.TILE_SIZE > gPanel.player.worldX - gPanel.player.screenX &&
                worldX - gPanel.TILE_SIZE < gPanel.player.worldX + gPanel.player.screenX &&
                worldY + gPanel.TILE_SIZE > gPanel.player.worldY - gPanel.player.screenY && 
                worldY - gPanel.TILE_SIZE < gPanel.player.worldY + gPanel.player.screenY) 
                {
                    gc.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

                }
        }
}
