package benchmarkFunction;

import libs.Point;
public class Rastrigin implements Functions {

	private double A;
	private double n;
	
	public Rastrigin(double A, double n) {
		this.A = A;
		this.n = n;
	}
	
	/* (non-Javadoc)
	 * @see libs.functions.Functions#f(libs.Point)
	 */
	@Override
	public double f(Point x) {
		double sum = 0;
		for (int i = 0; i < x.dim; i++) {
			sum += Math.pow(x.p[i], 2) - A*Math.cos(2*Math.PI*x.p[i]);
		}
		return A*n + sum;
	}

}
