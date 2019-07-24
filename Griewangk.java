package benchmarkFunction;
//Importing Java packages
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import static io.jenetics.engine.Limits.bySteadyFitness;

import io.jenetics.DoubleGene;
import io.jenetics.MeanAlterer;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.DoubleRange;
import java.util.Scanner;
//Griewangk Class
public class Griewangk {
	private static final double A = 10;
	private static final double R = 5.12;
	private static final int N = 2;

	private static double fitness(final double[] x) {
//            public double function(double[] x){    
            double sum1 = 0.0;
            double sum2 = 0.0;
                //System.out.println("Enter the element in the Array of x");
                //Scanner input = new Scanner (System.in);
                //i = input.nextDouble();
                // Test Case for 32 input for x
              //  x = new double[10];
            int j;
                for (j=0; j<=32; j++){
                    Scanner input = new Scanner(System.in);
                    x[j] =  input.nextDouble();
                }

                //Finding the sum for the numbers of element in the Array for input x(1,...,n)
                //where x.length = n(i.e length of the Array)
                for (int i = 0 ; i < x.length ; i ++) {
                sum1 += Math.pow(x[i], 2);
                sum2 += (Math.cos(2*Math.PI*x[i]));
                }
                return (-20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / ((double )x.length))) -
                Math.exp(sum2 / ((double )x.length)) + 20.0 + Math.exp(1));
                }
        
	public static void main(final String[] args) {
		final Engine<DoubleGene, Double> engine;
            engine = Engine
                    .builder(
                            RastriginFunction::fitness,
                            // Codec for 'x' vector.
                            Codecs.ofVector(DoubleRange.of(-R, R), N))
                    .populationSize(500)
                    .optimize(Optimize.MINIMUM)
                    .alterers(
                            new Mutator<>(0.03),
                            new MeanAlterer<>(0.6))
                    .build();

		final EvolutionStatistics<Double, ?>
			statistics = EvolutionStatistics.ofNumber();

		final Phenotype<DoubleGene, Double> best = engine.stream()
			.limit(bySteadyFitness(7))
			.peek(statistics)
			.collect(toBestPhenotype());

		System.out.println(statistics);
		System.out.println(best);
	}
}

//