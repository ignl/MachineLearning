package org.intelligentjava.regression.costfunction;

import org.jblas.DoubleMatrix;

/**
 * 
 * @author Ignas
 *
 */
public interface CostFunctionMinimizer {

	/**
	 * Find theta values with whom cost function would have lowest value.
	 * 
	 * @param features Features matrix (or x1, x2, x3...)
	 * @param values Values (or y for each x1, x2, x3...)
	 * 
	 * @return Matrix with theta values for minimal cost function.
	 */
	public DoubleMatrix minimizeCostFunction(DoubleMatrix features, DoubleMatrix values);
	
}
