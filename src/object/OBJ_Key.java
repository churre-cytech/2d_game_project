package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gPanel;

    public OBJ_Key(GamePanel gPanel) {
        super(gPanel);
        
        name = "Key";

        try {
            down1 = new Image("/object/key.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
