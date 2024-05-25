package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Super_Sword extends Entity {

    public OBJ_Super_Sword(GamePanel gPanel) {
        super(gPanel);

        type = type_sword;
        name = "Super Sword";
        try {
            down1 = new Image("/object/pickaxe.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // attackArea.setWidth(GamePanel.WORLD_WIDTH);
        // attackArea.setHeight(GamePanel.WORLD_HEIGHT);

        attackValue = 10;
        attackArea.setWidth(500);
        attackArea.setHeight(500);

    }

}
