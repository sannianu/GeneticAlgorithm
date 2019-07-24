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

public class Sphere {
	private static final double A = 10;
	private static final double R = 5.12;
	private static final int N = 12;

	private static double fitness(final double[] x) {
		
		double value = 0.0;
		for (int i = 0; i < N; ++i) {
                value += x[i] * x[i];
		//return value;
                }
                return value;
        }

	public static void main(final String[] args) {
		final Engine<DoubleGene, Double> engine = Engine
			.builder(
                                Sphere::fitness,
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
