package entity;

import java.util.Random;

import javafx.scene.image.Image;
import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gPanel) {
        super(gPanel);

        direction = "DOWN";
        speed = 1;

        getImage();
        setDialogues();
    }

    public void getImage() {

        try {
            up1 = new Image("/npc/oldman_up_1.png");
            up2 = new Image("/npc/oldman_up_2.png");
            down1 = new Image("/npc/oldman_down_1.png");
            down2 = new Image("/npc/oldman_down_2.png");
            left1 = new Image("/npc/oldman_left_1.png");
            left2 = new Image("/npc/oldman_left_2.png");
            right1 = new Image("/npc/oldman_right_1.png");
            right2 = new Image("/npc/oldman_right_2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void setDialogues() {
        
        dialoguesLines[0] = "Bienvenue, jeune aventurier !";
        dialoguesLines[1] = "Tu es venu chercher le trésor c'est bien cela ?";
        dialoguesLines[2] = "Je suis un grand sorcier, mais ne t'inquiète pas\nje ne te ferai pas de mal...";
        dialoguesLines[3] = "Je te souhaite bon courage dans ta quête !";
    }

    public void setAction() {

        actionLockCounter++;
        
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up number between 1 to 100
    
            if (i <= 25) {
                direction = "UP";
            }
            if (i > 25 && i <= 50) {
                direction = "DOWN";
            }
            if (i > 50 && i <= 75) {
                direction = "LEFT";
            }
            if (i > 75 && i <= 100) {
                direction = "RIGHT";
            }

            actionLockCounter = 0;
        }
    }


    public void speak() {

        super.speak();
    }

}
