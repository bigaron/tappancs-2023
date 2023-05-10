package prototype.src.Players;

import java.io.FileWriter;
import java.io.IOException;

import static prototype.src.Modifier.Slippery;

/**
 * A szabotőrt reprezentáló osztály, a játékos leszármazottja.
 */
public class Saboteur extends Player{
    private static int counter = 0;

    Saboteur() {
        ++counter;
        ID = "saboteur" + counter;
    }

    public void Save(FileWriter writer, boolean state) {
        try {
            if(state) {
                writer.write("saboteur+" + ID);
            } else {
                writer.write("saboteur+" + ID + "+" + elem.getID() + "+" + pipe.getID() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MakeSlippery() {
        elem.ChangeSurface(Slippery);
    }
}
