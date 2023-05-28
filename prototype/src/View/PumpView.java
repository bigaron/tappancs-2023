package prototype.src.View;

import prototype.src.Elements.Cistern;
import prototype.src.Elements.Pipe;
import prototype.src.Elements.Pump;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PumpView extends ElementView {

    public static int counter = 0;
    BufferedImage notWorkingPumpImage;
    BufferedImage imageToDraw;

    private BufferedImage deepCopy(BufferedImage src){
        ColorModel cm = src.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = src.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public PumpView(Pump referencedPump) {
        ++counter;
        referencedElement = referencedPump;
        x = 200;
        y = 200;
        
        try {
            File path = new File(new File(new File("prototype", "src"), "images"), "pump_working.png");
            image = ImageIO.read(path);
            imageToDraw = deepCopy(image);

            File path2 = new File(new File(new File("prototype", "src"), "images"), "pump_notworking.png");
            notWorkingPumpImage = ImageIO.read(path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public int getX() {
        return x + image.getWidth() / 2;
    }

    @Override
    public int getY() {
        return y + image.getHeight() / 2;
    }

    @Override
    public void draw(Graphics g) {
        if(referencedElement.getWork()) {
            g.drawImage(image, x, y, null);
            //g.drawImage(image, x -200, y, null);
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

    public BufferedImage rot(BufferedImage img, double fi){
        int w = img.getWidth(), h = img.getHeight();
        BufferedImage nImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g2d = nImg.createGraphics();
        g2d.rotate(fi, w/2, h/2);
        g2d.drawImage(img, null, 0, 0);
        return nImg;
    }

    @Override
    public void calculateCoords(int x, int y) {
        if(visited) return;

        visited = true;
        this.x = x - image.getWidth() / 2;
        this.y = y - image.getWidth() / 2;
        Pump pump = (Pump) referencedElement;
        int neighborCount = pump.getNeighborSize();
        for(int i = 0; i < neighborCount; ++i) {
            Pipe pipe = (Pipe)pump.GetNeighbor(i);
            PipeView pipeView = (PipeView) pipe.getView();
            double fi = 2 * i * Math.PI / neighborCount;
            int dir = 1;
            if(pump.getOutput() != null && pipe.getID().equals(pump.getOutput().getID())){
                if(!pump.getOutChanged()) continue;
                image = deepCopy(imageToDraw);
                if(Double.compare(fi, 3*Math.PI / 2) == 0 || Double.compare(fi, Math.PI / 2) == 0){
                    dir = -1;
                }
                image = rot(image, dir * fi);
                dir = 1;
            }
            pipeView.calculateCoords((int)(x + Math.cos(fi) * basicPipeDistance), (int)(y - Math.sin(fi) * basicPipeDistance));
        }
    }

   public int[] AttachCoords(Pipe pipe) {
        int[] attachPointXY = new int[2];
        Pump pump = (Pump) referencedElement;
        int pipeIdx = pump.neighbours.indexOf(pipe);
        int pumpNeighborSize = pump.getNeighborSize();
        double fi = 2 * pipeIdx * Math.PI / pumpNeighborSize;
        attachPointXY[0] = (int)(x + image.getWidth() / 2 + (image.getWidth() / 2) * Math.cos(fi));
        attachPointXY[1] = (int)(y + image.getHeight() / 2 - (image.getWidth() / 2) * Math.sin(fi));

        return attachPointXY;
    }
}
