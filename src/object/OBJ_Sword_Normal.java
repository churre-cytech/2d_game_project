package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gPanel) {
        super(gPanel);

        name = "Normal Sword";
        down1 = new Image("/object/sword_normal.png");
        attackValue = 1;
    }

}
