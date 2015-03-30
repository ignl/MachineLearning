package org.intelligentjava.regression.logisticregression;

import java.io.IOException;

import org.jblas.DoubleMatrix;
import org.junit.Test;

public class LogisticGradientDescentCostFunctionMinimizerTest {

	@Test
	public void testMinimizeCostFunction() throws IOException {
		LogisticGradientDescentCostFunctionMinimizer minimizer = new LogisticGradientDescentCostFunctionMinimizer(0.001, 1000000, false);
		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile(getClass().getClassLoader().getResource("logisticregression/ex2data2.txt").getFile());
		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0)), dataMatrix.getColumn(1));
		DoubleMatrix values = dataMatrix.getColumn(2);
		
		DoubleMatrix theta = minimizer.minimizeCostFunction(features, values);
		
		LogaritmicCostFunction costFunction = new LogaritmicCostFunction();
//		Assert.assertEquals(costFunction.computeCost(features, values, theta), 0.203498, 0.0000001);
//		Assert.assertEquals(theta.get(0, 0), -0.1, 0.0000001);
//		Assert.assertEquals(theta.get(1, 0), -12.009217, 0.0000001);
//		Assert.assertEquals(theta.get(2, 0), -11.262842, 0.0000001);
	}
	
}
