package prototype.src.View;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ObjectView {
    protected BufferedImage image;
    protected int x;
    protected int y;
    protected boolean visited = false;
    protected int basicPipeDistance = 250;

    public ObjectView() {
        x = 0; y = 0;
        image = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public abstract void calculateCoords(int x, int y);

    public abstract void draw(Graphics g);
    public abstract void update();
    public void rotate() { };
}
