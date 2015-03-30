package org.intelligentjava.regression.linearregression;

import org.jblas.DoubleMatrix;

/**
 * @author Ignas
 *
 */
public class Predictor {
	
	/** */
	private DoubleMatrix mu;
	
	/** */
	private DoubleMatrix sigma;
	
	/** Flag for predictor to know if features were normalized. */
	private boolean denormalize;
	
	/**
	 * Constructor.
	 */
	public Predictor() {
		super();
		denormalize = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param mu
	 * @param sigma
	 */
	public Predictor(DoubleMatrix mu, DoubleMatrix sigma) {
		super();
		this.mu = mu;
		this.sigma = sigma;
		denormalize = true;
	}

	/**
	 * Predict value from Linear regression.
	 * 
	 * @param featureValues feature values for which output is predicted.
	 * @param thetas
	 *            Theta values for linear regression function. Those values
	 *            comes from minimizing cost function algorithm.
	 * @return Prediction.
	 */
	public double predict(DoubleMatrix featureValues, DoubleMatrix thetas) {
		DoubleMatrix featureToPredict = null;
		if (denormalize) {
			featureToPredict = featureValues.sub(mu).div(sigma);
			for (int i = 0; i < featureToPredict.rows; i++) { // TODO better way?
				featureToPredict.put(i, 0, 1); // set 1's in first column - no need to denormalize them
			}
		} else {
			featureToPredict = featureValues;
		}
		return featureToPredict.mmul(thetas).get(0);
	}

}
