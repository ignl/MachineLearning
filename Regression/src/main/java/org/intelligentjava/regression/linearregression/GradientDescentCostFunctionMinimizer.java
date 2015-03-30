package org.intelligentjava.regression.linearregression;

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
public class GradientDescentCostFunctionMinimizer implements CostFunctionMinimizer {
	
	private double alpha;
	private double iterations;
	private boolean normalizeFeatures;
	
	private DoubleMatrix mu;
	private DoubleMatrix sigma;
	
	/**
	 * Constructor.
	 * 
	 * @param alpha Alpha parameter which configures how small each grandient descent is.
	 * @param iterations Number of iterations for algorithm.
	 */
	public GradientDescentCostFunctionMinimizer(double alfa, double iterations, boolean normalizeFeatures) {
		super();
		this.alpha = alfa;
		this.iterations = iterations;
		this.normalizeFeatures = normalizeFeatures;
	}

	/**
	 * @see org.intelligentjava.machinelearning.costfunction.ign.ai.ml.CostFunctionMinimizer#minimizeCostFunction(org.jblas.DoubleMatrix, org.jblas.DoubleMatrix)
	 */
	public DoubleMatrix minimizeCostFunction(DoubleMatrix features, DoubleMatrix values) {
		DoubleMatrix featuresToUse = null;
		if (normalizeFeatures) {
			featuresToUse = normalizeFeatures(features);
		} else {
			featuresToUse = features;
		}
		int size = values.rows;
		DoubleMatrix theta = DoubleMatrix.zeros(features.columns, 1);
		for (int i = 0; i < iterations; i++) {
			DoubleMatrix featuresTranspose = featuresToUse.transpose();
			DoubleMatrix delta = (featuresTranspose.mmul(featuresToUse).mmul(theta).sub(featuresTranspose.mmul(values))).div(size);
			theta = theta.sub(delta.mul(alpha));
		}
		return theta;
	}
	
	/**
	 * Normalize features. TODO comment
	 * 
	 * @param features Features matrix.
	 * 
	 * @return Normalized features matrix.
	 */
	private DoubleMatrix normalizeFeatures(DoubleMatrix features) {
		int sampleSize = features.rows;
		int differentFeatures = features.columns;
		DoubleMatrix normalizedFeatures = new DoubleMatrix(sampleSize, differentFeatures);
		normalizedFeatures.putColumn(0, features.getColumn(0));
		mu = new DoubleMatrix(1, differentFeatures);
		sigma = new DoubleMatrix(1, differentFeatures);
		mu.put(0, 0, 1d);
		sigma.put(0, 0, 1d);
		// run through all columns (except 1 one, because it contains only ones)
		for (int feature = 1; feature < differentFeatures; feature++) {
		  double mean = features.getColumn(feature).mean();
		  double standardDeviation = calculateStandartDeviation(features.getColumn(feature), mean);
		  mu.put(0, feature, mean);
		  sigma.put(0, feature, standardDeviation);
		  // run through all rows and normalize each sample
		  for (int sample = 0; sample < sampleSize; sample++) {
			  normalizedFeatures.put(sample, feature, (features.get(sample, feature) - mean) / standardDeviation);
		  }

		}
		return normalizedFeatures;
	}
	
	/**
	 * Same deviation function as in octave std().
	 */
	private double calculateStandartDeviation(DoubleMatrix feature, double mean) {
		int sampleSize = feature.rows;
		double squaredSum = 0;
		for (int i = 0; i < sampleSize; i++) {
			double featureValue = feature.get(i, 0);
			double difference = featureValue - mean;
			squaredSum += difference * difference;
		}
		return Math.sqrt(squaredSum / (sampleSize - 1));
	}

	public DoubleMatrix getMu() {
		return mu;
	}

	public DoubleMatrix getSigma() {
		return sigma;
	}
	
}
