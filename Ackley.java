package benchmarkFunction;
import io.jenetics.DoubleGene;
import io.jenetics.MeanAlterer;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import io.jenetics.engine.EvolutionStatistics;
import static io.jenetics.engine.Limits.bySteadyFitness;
import io.jenetics.util.DoubleRange;
import libs.Point;

public class Ackley implements Functions {
	//Declaration of Variables
	private int a;
	private double b;
	private double c;
	private double d;
          //

	private static final double A = 10;
	private static final double R = 32.768;
	private static final int N = 2;        




//Ackley Class with parameters a, b, c, d
	public Ackley(int a, double b, double c, double d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	@Override
	public double f(Point x) {
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < x.dim; i++) {
			sum1 += Math.pow(x.p[i], 2);
			sum2 += Math.cos(c*x.p[i]);
		}
		return a + Math.exp(1) + d - a*Math.exp(-b*Math.sqrt(sum1/x.dim)) - Math.exp(sum2/x.dim);
	}


        //
	public static void main(final String[] args) {
		final Engine<DoubleGene, Double> engine = Engine
			.builder(
				Ackley::fitness,
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
