package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gPanel;

    public OBJ_Door(GamePanel gPanel) {
        super(gPanel);

        type = type_fixItem;
        name = "Door";
        try {
            down1 = new Image("/object/door.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        collision = true;
    }


}
