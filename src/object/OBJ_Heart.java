package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gPanel;

    public OBJ_Heart(GamePanel gPanel) {
        super(gPanel);

        name = "Heart";

        try {
            image = new Image("/object/heart_full.png");
            image2 = new Image("/object/heart_half.png");
            image3 = new Image("/object/heart_blank.png");            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
