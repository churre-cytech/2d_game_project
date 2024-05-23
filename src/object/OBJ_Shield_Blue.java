package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

    public OBJ_Shield_Blue(GamePanel gPanel) {
        super(gPanel);

        type = type_shield;
        name = "Blue Shield";
        try {
            down1 = new Image("/object/shield_blue.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        defenseValue = 2;
    }

}
