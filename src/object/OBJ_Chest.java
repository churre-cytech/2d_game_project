package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gPanel;

    public OBJ_Chest(GamePanel gPanel) {
        super(gPanel);
        
        name = "Chest";

        try {
            down1 = new Image("/object/chest.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
