package monster;

import java.util.Random;

import entity.Entity;
import javafx.scene.image.Image;
import main.GamePanel;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gPanel) {
        super(gPanel);

        name = "Green Slime";
        type = 2;
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;

        solidArea.setX(3);
        solidArea.setY(18);
        solidArea.setWidth(42);
        solidArea.setHeight(30);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
        
        getImage();
    }

    public void getImage() {
        try {
            up1 = new Image("/monster/greenslime_down_1.png");
            up2 = new Image("/monster/greenslime_down_2.png");
            down1 = new Image("/monster/greenslime_down_1.png");
            down2 = new Image("/monster/greenslime_down_2.png");
            left1 = new Image("/monster/greenslime_down_1.png");
            left2 = new Image("/monster/greenslime_down_2.png");
            right1 = new Image("/monster/greenslime_down_1.png");
            right2 = new Image("/monster/greenslime_down_2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAction() {

        actionLockCounter++;
        
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up number between 1 to 100
    
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

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gPanel.player.direction;
    }

    
}
