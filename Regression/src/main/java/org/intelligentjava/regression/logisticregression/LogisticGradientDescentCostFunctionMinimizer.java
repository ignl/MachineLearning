package org.intelligentjava.regression.logisticregression;

import org.intelligentjava.regression.costfunction.CostFunctionMinimizer;
import org.jblas.DoubleMatrix;

/**
 * This method uses Gradient descent algorithm. It uses number of small
 * steps (iterations) and with each step use new theta values which
 * results in smaller cost function value. After a while it comes to 
 * local minima (minimum cost function).
 * 
 * @author Ignas
 * 
 */
public class LogisticGradientDescentCostFunctionMinimizer implements CostFunctionMinimizer {
	
	private double alpha;
	private double iterations;
	
	/**
	 * Constructor.
	 * 
	 * @param alpha Alpha parameter which configures how small each grandient descent is.
	 * @param iterations Number of iterations for algorithm.
	 */
	public LogisticGradientDescentCostFunctionMinimizer(double alfa, double iterations, boolean normalizeFeatures) {
		super();
		this.alpha = alfa;
		this.iterations = iterations;
	}

	/**
	 * @see org.ign.ai.ml.CostFunctionMinimizer#minimizeCostFunction(org.jblas.DoubleMatrix, org.jblas.DoubleMatrix)
	 */
	public DoubleMatrix minimizeCostFunction(DoubleMatrix features, DoubleMatrix values) {
		int size = values.rows;
		DoubleMatrix theta = DoubleMatrix.zeros(features.columns, 1);
		for (int i = 0; i < iterations; i++) {
			DoubleMatrix featuresTranspose = features.transpose();
			DoubleMatrix hx = new LogaritmicCostFunction().sigmoid(features.mmul(theta));
			DoubleMatrix delta = (featuresTranspose.mmul(hx).sub(featuresTranspose.mmul(values))).div(size);
			// theta = theta - (1 / (m)) * (X' * hx - X' * y)
			theta = theta.sub(delta.mul(alpha));
		}
		return theta;
	}
	
}
