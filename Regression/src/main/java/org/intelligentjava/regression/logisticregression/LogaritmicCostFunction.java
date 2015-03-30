package org.intelligentjava.regression.logisticregression;

import org.intelligentjava.regression.costfunction.CostFunction;
import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

/**
 * LogaritmicCostFunction used for calculating cost of classification (classifies only true or false (1/0)). 
 * It calculates sigmoid function (which assigns probability that certain element is true (1)).
 * 
 * @author Ignas
 *
 */
public class LogaritmicCostFunction implements CostFunction {

	/**
	 * @see org.ign.ai.ml.CostFunction#computeCost(org.jblas.DoubleMatrix, org.jblas.DoubleMatrix, org.jblas.DoubleMatrix)
	 */
	public double computeCost(DoubleMatrix features, DoubleMatrix values, DoubleMatrix theta) {
		int size = values.rows;
		DoubleMatrix ones = DoubleMatrix.ones(values.columns, values.rows);
		// -1 .* y .* log(sigmoid(X*theta))
		DoubleMatrix first = values.mul(-1.0).mul(MatrixFunctions.log(sigmoid(features.mmul(theta))));
		// (1 - y) .* (log(1 - sigmoid(X*theta)))
		DoubleMatrix second = ones.sub(values).mul(MatrixFunctions.log(ones.sub(sigmoid(features.mmul(theta)))));
		return (first.sub(second)).sum() / size;
	}
	
	/**
	 * Sigmoid function. Formula: g = 1 ./ (1 + (exp(-1 .* z)));
	 * 
	 * @param z Matrix, for which elements sigmoid function is calculated.
	 * @return Matrix with elements from sigmoid function.
	 */
	protected DoubleMatrix sigmoid(DoubleMatrix z) {
		
		DoubleMatrix ones = DoubleMatrix.ones(z.rows, z.columns);
		
		return ones.div(ones.add(MatrixFunctions.exp(z.mul(-1.0))));
	}

}
