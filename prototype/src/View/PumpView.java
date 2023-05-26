package prototype.src.View;

import prototype.src.Elements.Cistern;
import prototype.src.Elements.Pipe;
import prototype.src.Elements.Pump;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PumpView extends ElementView {

    BufferedImage notWorkingPumpImage;
    private AffineTransform at = new AffineTransform();
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

    public void rotator(double fi){
        double cos = Math.abs(Math.cos(fi)), sin = Math.abs(Math.sin(fi));
        int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        BufferedImage rotatedIm = new BufferedImage(w, h, image.getType());
        at.translate(w/2, h/2);
        at.rotate(fi, 0,0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedIm);
        image = rotatedIm;
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
            if(pipe.getID().equals(pump.getOutput().getID())) rotator(fi);
            pipeView.calculateCoords((int)(x + Math.cos(fi) * basicPipeDistance), (int)(y - Math.sin(fi) * basicPipeDistance));
        }
    }
}
