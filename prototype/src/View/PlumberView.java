package prototype.src.View;

import prototype.src.Players.Plumber;
import prototype.src.Players.Saboteur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlumberView extends PlayerView {

    public PlumberView(Plumber plumberReference) {
        referencedPlayer = plumberReference;
        x = 400;    //TODO update necessary
        y = 400;
        try {
            File path = new File(new File(new File("prototype", "src"), "images"), "engineer1.png");
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
