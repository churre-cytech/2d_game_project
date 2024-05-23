package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Bronze_Coin extends Entity {

    public OBJ_Bronze_Coin(GamePanel gPanel) {
        super(gPanel);

        this.gPanel = gPanel;

        type = type_pickUpOnly;
        name = "Bronze Coin";
        try {
            down1 = new Image("/object/coin_bronze.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemValue = 1;
    }

    public void use(Entity entity) {
        
        gPanel.player.coin += itemValue;
        System.out.println("Coin : " + gPanel.player.coin);
    }
    
}
