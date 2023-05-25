package prototype.src.View;

import prototype.src.Elements.Source;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SourceView extends ElementView {

    int sourcePixelDiameter = 120;

    public SourceView(Source referencedSource) {
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
}
