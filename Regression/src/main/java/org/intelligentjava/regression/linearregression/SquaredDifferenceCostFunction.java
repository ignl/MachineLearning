package org.intelligentjava.regression.linearregression;

import org.intelligentjava.regression.costfunction.CostFunction;
import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

/**
 * Calculate cost function for hypothesis y = theta1*x1 + theta2*x2 + theta3*x3... Chosen cost function formula:
 * J(theta1, theta2...) = 1/2*m * sum(h(x) - y)^2.
 * This cost function is usually used in linear regression.
 * 
 * @author Ignas
 *
 */
public class SquaredDifferenceCostFunction implements CostFunction {
	
	/**
	 * @see org.intelligentjava.machinelearning.costfunction.ign.ai.ml.CostFunction#computeCost(org.jblas.DoubleMatrix, org.jblas.DoubleMatrix, org.jblas.DoubleMatrix)
	 */
	public double computeCost(DoubleMatrix features, DoubleMatrix values, DoubleMatrix theta) {
		int size = values.getRows();
		DoubleMatrix predictions = features.mmul(theta);
		DoubleMatrix sub = predictions.sub(values);
		DoubleMatrix sqrErrors = MatrixFunctions.pow(sub, 2);
		return sqrErrors.sum() / (2*size);
	}

}
