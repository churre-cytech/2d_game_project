package object;

import javafx.scene.image.Image;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {
        
        name = "Door";

        try {
            image = new Image("/objects/door.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
