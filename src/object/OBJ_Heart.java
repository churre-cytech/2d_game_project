package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gPanel;

    public OBJ_Heart(GamePanel gPanel) {
        super(gPanel);

        this.gPanel = gPanel;

        type = type_pickUpOnly;
        name = "Heart";
        try {
            down1 = new Image("/object/heart_full.png");
            image = new Image("/object/heart_full.png");
            image2 = new Image("/object/heart_half.png");
            image3 = new Image("/object/heart_blank.png");            
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemValue = 1;
    }

    public void use(Entity entity) {

        System.out.println("Life + " + itemValue);

        // Same as OBJ_Potion_RED -> maybe put this code at the end of update() in player class
        entity.life += itemValue;
        if (entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
    }
}
