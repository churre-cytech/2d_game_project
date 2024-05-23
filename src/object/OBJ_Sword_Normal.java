package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gPanel) {
        super(gPanel);

        type = type_sword;
        name = "Normal Sword";
        try {
            down1 = new Image("/object/sword_normal.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        attackValue = 1;
        attackArea.setWidth(36);
        attackArea.setHeight(36);

    }

}
