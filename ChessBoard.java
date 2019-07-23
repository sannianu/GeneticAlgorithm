package geneticalgorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;


public class ChessBoard extends JPanel {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;
    private Chromosome fittestChromosome;

    public ChessBoard () {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        for (int row = 0;  row < 9;  row++ ) {
            for (int col = 0;  col < 8;  col++) {
                int x = col * 80;
                int y = row * 80;
                if ((row % 2) == (col % 2))
                    g.setColor(Color.WHITE);
                else g.setColor(Color.GRAY);

                g.fillRect(x, y, 80, 80);
                if (row < 8) {
                    if (this.fittestChromosome.getGenes(row) == col) {
                        Queen queen = new Queen();
                        queen.paint(g, x, y);
                    }
                }
            }
        }
    }

    public void setFittestChromosome (Chromosome chromosome) {
        this.fittestChromosome = chromosome;
    }

}
