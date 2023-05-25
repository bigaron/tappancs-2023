package prototype.src.View;

import prototype.src.Elements.Pump;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PumpView extends ElementView {

    BufferedImage notWorkingPumpImage;

    public PumpView(Pump referencedPump) {
        referencedElement = referencedPump;
        x = 300;
        y = 100;

        try {
            File path = new File(new File("prototype", "src"), "pump_working.png");
            image = ImageIO.read(path);

            File path2 = new File(new File("prototype", "src"), "pump_notworking.png");
            notWorkingPumpImage = ImageIO.read(path2);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        /*
        if(referencedElement.getWorking()) {
            g.drawImage(image, x, y, null);
        } else {
            g.drawImage(notWorkingPumpImage, x, y, null);
        }

         */
        g.drawImage(image, x, y, null);     //for testing only
        g.drawImage(notWorkingPumpImage, x+100, y, null);     //for testing only
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void rotate() {
        //TODO
    }
}
