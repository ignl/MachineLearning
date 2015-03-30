package org.intelligentjava.regression.costfunction;

import org.jblas.DoubleMatrix;

/**
 * Cost function interface. Cost function is used to calculate the difference
 * between actual result and predicted result.
 * 
 * @author Ignas
 * 
 */
public interface CostFunction {

	/**
	 * @param features Features (or x1, x2, x3 ...  values).
	 * @param values Function values (or y).
	 * @param theta Theta values vector. 
	 * 
	 * @return Cost of prediction.
	 */
	public double computeCost(DoubleMatrix features, DoubleMatrix values, DoubleMatrix theta);

}
