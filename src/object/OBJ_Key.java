package object;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gPanel;

    public OBJ_Key(GamePanel gPanel) {
        super(gPanel);

        this.gPanel = gPanel;
        
        type = type_pickUpOnly;
        name = "Key";
        try {
            down1 = new Image("/object/key.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void use(Entity entity) {

        entity.hasKey++;
        
        String text = "Clé récupérée, vous avez " + entity.hasKey + " clé(s) !";
        System.out.println(text);

        gPanel.gameState = gPanel.dialogueState;
        gPanel.ui.currentDialogue = text;

        // entity.inventory.add(this); 
    }
}
