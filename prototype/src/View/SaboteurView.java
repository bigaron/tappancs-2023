package prototype.src.View;

import prototype.src.Elements.Element;
import prototype.src.Game;
import prototype.src.Players.Saboteur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaboteurView extends PlayerView {
    public static int counter = 0;

    public SaboteurView(Saboteur saboteurReference) {
        ++counter;
        referencedPlayer = saboteurReference;
        x = 200;
        y = 200;
        try {
            File path = new File(new File(new File("prototype", "src"), "images"), "gru2.png");
            image = ImageIO.read(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if(Game.activePlayer == referencedPlayer) {
            g.setColor(Color.red);
            g.drawRect(x - 5, y - 5, image.getWidth() + 5, image.getHeight() + 5);
        }
        g.drawImage(image, x, y, null);
    }

    public void update() {

    }

    @Override
    public void calculateCoords(int x, int y) {
        Element elem = referencedPlayer.getElement();
        ElementView elementView = elem.getView();
        setX(elementView.getX() - elementView.getWidth() / 2);
        setY(elementView.getY() - elementView.getHeight() / 2 - image.getHeight());
    }
}
