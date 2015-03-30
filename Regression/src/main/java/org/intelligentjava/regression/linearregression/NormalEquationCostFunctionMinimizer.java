package org.intelligentjava.regression.linearregression;

import org.intelligentjava.regression.costfunction.CostFunctionMinimizer;
import org.jblas.DoubleMatrix;
import org.jblas.Solve;

/**
 * @author Ignas
 *
 */
public class NormalEquationCostFunctionMinimizer implements CostFunctionMinimizer {
	
	/**
	 * @see org.intelligentjava.machinelearning.costfunction.ign.ai.ml.CostFunctionMinimizer#minimizeCostFunction(org.jblas.DoubleMatrix, org.jblas.DoubleMatrix)
	 */
	public DoubleMatrix minimizeCostFunction(DoubleMatrix features, DoubleMatrix values) {
		DoubleMatrix featureTranspose = features.transpose();
		DoubleMatrix featureMultiplyFeatureTranspose = featureTranspose.mmul(features);
		DoubleMatrix inverse = Solve.solve(featureMultiplyFeatureTranspose, DoubleMatrix.eye(featureMultiplyFeatureTranspose.rows));
		DoubleMatrix featureTransposeMultiplyValues = featureTranspose.mmul(values);
		return inverse.mmul(featureTransposeMultiplyValues);
	}

}
