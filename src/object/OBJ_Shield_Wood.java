package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gPanel) {
        super(gPanel);

        type = type_shield;
        name = "Wood Shield";
        try {
            down1 = new Image("/object/shield_wood.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        defenseValue = 1;
    }

}
