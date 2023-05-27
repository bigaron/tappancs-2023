package prototype.src.View;

import prototype.src.Elements.Cistern;
import prototype.src.Elements.Pipe;
import prototype.src.Elements.Pump;
import prototype.src.Elements.Source;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SourceView extends ElementView {
    public static int counter = 0;

    int sourcePixelDiameter = 120;

    public int getSourcePixelDiameter() {
        return sourcePixelDiameter;
    }

    public int getWidth() {
        return sourcePixelDiameter;
    }

    public int getHeight() {
        return sourcePixelDiameter;
    }

    @Override
    public int getX() {
        return x + sourcePixelDiameter / 2;
    }

    @Override
    public int getY() {
        return y + sourcePixelDiameter / 2;
    }

    public SourceView(Source referencedSource) {
        ++counter;
        referencedElement = referencedSource;
        x = 200;    //TODO update necessary
        y = 200;
        /*try {
            File path = new File(new File("prototype", "src"), "source.png");
            image = ImageIO.read(path);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(142, 166, 4));
        g.fillOval(x, y, sourcePixelDiameter, sourcePixelDiameter);

    }

    @Override
    public void update() {
        //TODO
    }

    @Override
    public void calculateCoords(int x, int y) {
        if(visited) return;

        visited = true;
        this.x = x;
        this.y = y;
        Source source = (Source) referencedElement;
        int neighborCount = source.getNeighborSize();
        for(int i = 0; i < neighborCount; ++i) {
            Pipe pipe = (Pipe)source.GetNeighbor(i);
            PipeView pipeView = (PipeView) pipe.getView();
            double fi = 2 * i * Math.PI / neighborCount;
            pipeView.calculateCoords((int)(x + Math.cos(fi) * basicPipeDistance), (int)(y - Math.sin(fi) * basicPipeDistance));
        }
    }

    public int[] AttachCoords(Pipe pipe) {
        int[] attachPointXY = new int[2];
        Source source = (Source) referencedElement;
        int pipeIdx = source.neighbours.indexOf(pipe);
        int pumpNeighborSize = source.getNeighborSize();
        double fi = 2 * pipeIdx * Math.PI / pumpNeighborSize;
        attachPointXY[0] = (int)(x + sourcePixelDiameter / 2 + (sourcePixelDiameter / 2) * Math.cos(fi));
        attachPointXY[1] = (int)(y + sourcePixelDiameter / 2 - (sourcePixelDiameter / 2) * Math.sin(fi));

        return attachPointXY;
    }
}
