package prototype.src.View;

import prototype.src.Elements.Cistern;
import prototype.src.Elements.Pipe;
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
        x = 200;
        y = 200;

        try {
            File path = new File(new File(new File("prototype", "src"), "images"), "pump_working.png");
            image = ImageIO.read(path);

            File path2 = new File(new File(new File("prototype", "src"), "images"), "pump_notworking.png");
            notWorkingPumpImage = ImageIO.read(path2);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {

        if(referencedElement.getWork()) {
            g.drawImage(image, x, y, null);
        } else {
            g.drawImage(notWorkingPumpImage, x, y, null);
        }
    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void rotate() {
        //TODO
    }

    @Override
    public void calculateCoords(int x, int y) {
        if(visited) return;

        visited = true;
        this.x = x;
        this.y = y;
        Pump pump = (Pump) referencedElement;
        int neighborCount = pump.getNeighborSize();
        for(int i = 0; i < neighborCount; ++i) {
            Pipe pipe = (Pipe)pump.GetNeighbor(i);
            PipeView pipeView = (PipeView) pipe.getView();
            double fi = 2 * i * Math.PI / neighborCount;
            pipeView.calculateCoords((int)(x + Math.cos(fi) * basicPipeDistance), (int)(y + Math.sin(fi) * basicPipeDistance));
        }
    }
}
