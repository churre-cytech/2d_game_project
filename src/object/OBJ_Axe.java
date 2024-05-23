package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gPanel) {
        super(gPanel);

        type = type_axe;
        name = "Axe";
        try {
            down1 = new Image("/object/axe.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        attackValue = 2;
        attackArea.setWidth(50);
        attackArea.setHeight(50);
    }
    
}
