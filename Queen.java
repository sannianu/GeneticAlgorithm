package geneticalgorithm;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author oyewole abdul kabir
 */
public class Queen {
    private BufferedImage img = null;
    private static final int width = 70;
    private static final int height = 70;

    public Queen() {
        try{
            this.img = ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\GeneticAlgorithm\\images\\queen.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics g, int x, int y){
        g.drawImage(this.img, x, y, null);
    }

}