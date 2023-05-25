package prototype.src.View;

import prototype.src.Players.Saboteur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaboteurView extends PlayerView {

    public SaboteurView(Saboteur saboteurReference) {
        referencedPlayer = saboteurReference;
        x = 200;    //TODO update necessary
        y = 200;
        try {
            File path = new File(new File("prototype", "src"), "gru2.png");
            image = ImageIO.read(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void update() {
        //TODO  we have to update x y coordinates according to the referencedPlayer object
    }
}
