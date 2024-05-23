package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    int value = 5;

    public OBJ_Potion_Red(GamePanel gPanel) {
        super(gPanel);

        this.gPanel = gPanel;

        type = type_consumable;
        name = "Red Potion";
        try {
            down1 = new Image("/object/potion_red.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        description = "Restored life : " + value;
    }

    public void use (Entity entity) {

        gPanel.gameState = gPanel.dialogueState;
        gPanel.ui.currentDialogue = "You drink the " + name + " !\n"
                                    + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if (gPanel.player.life > gPanel.player.maxLife) {
            gPanel.player.life = gPanel.player.maxLife;
        }
    }

}
