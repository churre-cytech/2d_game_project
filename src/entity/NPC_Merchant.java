package entity;

import javafx.scene.image.Image;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gPanel) {
        super(gPanel);

        type = type_npc;
        direction = "DOWN";
        speed = 1;

        getImage();
        setDialogues();
        setItems();
    }

    public void getImage() {

        try {
            up1 = new Image("/npc/merchant_down_1.png");
            up2 = new Image("/npc/merchant_down_2.png");
            down1 = new Image("/npc/merchant_down_1.png");
            down2 = new Image("/npc/merchant_down_2.png");
            left1 = new Image("/npc/merchant_down_1.png");
            left2 = new Image("/npc/merchant_down_2.png");
            right1 = new Image("/npc/merchant_down_1.png");
            right2 = new Image("/npc/merchant_down_2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void setDialogues() {
        dialoguesLines[0] = "Vous m'avez trouvé, j'ai ce qu'il faut pour vous !";
    }
    
    public void setItems() {

        inventory.add(new OBJ_Potion_Red(gPanel));
        inventory.add(new OBJ_Key(gPanel));
        inventory.add(new OBJ_Axe(gPanel));
        inventory.add(new OBJ_Sword_Normal(gPanel));
        inventory.add(new OBJ_Shield_Blue(gPanel));
        inventory.add(new OBJ_Shield_Blue(gPanel));

    }

    public void speak() {
        super.speak();

        gPanel.gameState = gPanel.tradeState;
        gPanel.ui.npc = this;
    }

}
