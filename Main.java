package geneticalgorithm;
import javax.swing.*;
import java.awt.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneticAlgo geneticAlgo;
        geneticAlgo = new GeneticAlgo(100);
        int generation = 1;
          //Create chess board
        JFrame jFrame = new JFrame("Chess board");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLayout(new BorderLayout());;
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        ChessBoard board = new ChessBoard();
        JLabel jLabel = new JLabel("Label");
        jLabel.setVisible(true);
        jLabel.setText("Generation: " + generation);
        board.add(jLabel, BorderLayout.EAST);
        board.setFittestChromosome(geneticAlgo.getFittestChromosome());
        jFrame.add(board, BorderLayout.CENTER);
        jFrame.pack();

        while (geneticAlgo.getFittestChromosome().getFitness() != 0) {
            geneticAlgo.naturalSelection();

            jFrame.remove(board);
            jFrame.revalidate();
            jFrame.repaint();
            board.setFittestChromosome(geneticAlgo.getFittestChromosome());
            jLabel.setText("Generation: " + generation);
            board.add(jLabel);
            jFrame.add(board,BorderLayout.CENTER);
            jFrame.pack();
            //Exception Handling to check for error
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // Do nothing
            }
            generation++;
        }
        System.out.print("Done");
    }
    
    //
    
}
