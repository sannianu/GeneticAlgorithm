package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

    
/**Index is row on chess board
*value for each index is the column on chess board.
 
 * @author oyewole abdul kabir
 */
public class Chromosome {
  private int fitness;
    private final int[] genes;
    public final int LENGTH = 8;

    public Chromosome() {
        this.genes = new int[this.LENGTH];
        this.fitness = -100;
    }

    public void generateChromosome () {
        Random random = new Random();
        for (int i = 0; i < this.LENGTH; i++) {
            this.genes[i] = random.nextInt(this.LENGTH);
        }
    }

    public void calculateFitness () {
        int conflicts = 0;
        for (int i = 0; i < this.LENGTH; i++) {
            conflicts += getVerticalHorizontalConflicts(i) + getDiagonalConflicts(i);
        }
        this.fitness = -1 * conflicts;
    }

    public int getFitness() {
        return this.fitness;
    }

    public int getGenes (int index) {
        return this.genes[index];
    }

    public void setGenes (int index, int gene) {
        this.genes[index] = gene;
    }

    public void displayChromosome () {
        System.out.println(Arrays.toString(this.genes));
    }

    private int getVerticalHorizontalConflicts (int index) {
        int conflicts = 0;
        for (int i = 0; i < this.LENGTH; i++) {
            if (i == index)
                continue;
            if (this.genes[i] == this.genes[index])
                conflicts++;
        }
        return conflicts;
    }

    private int getDiagonalConflicts (int index) {
        int conflicts = 0;
        int queenRow = index;
        int queenCol = this.genes[index];
        int offset = (queenRow >= queenCol) ? queenCol : queenRow;
        int topLeftRow = queenRow - offset;
        int topLeftCol = queenCol - offset;
        while (topLeftCol < this.LENGTH && topLeftRow < this.LENGTH
                && topLeftCol >= 0 && topLeftRow >= 0) {
            if (topLeftCol == this.genes[topLeftRow] &&
                    topLeftCol != queenCol && topLeftRow != queenRow)
                conflicts++;
            topLeftCol++;
            topLeftRow++;
        }

        offset = (queenRow >= (this.LENGTH - queenCol - 1)) ? (this.LENGTH - queenCol - 1) : queenRow;
        int topRightRow = queenRow - offset;
        int topRightCol = queenCol + offset;
        while (topRightCol < this.LENGTH && topRightRow < this.LENGTH
                && topRightCol >= 0 && topRightRow >= 0) {
            if (topRightCol == this.genes[topRightRow] &&
                    topRightCol != queenCol && topRightRow != queenRow)
                conflicts++;
            topRightCol--;
            topRightRow++;
        }

        return conflicts;
    }
  
}
