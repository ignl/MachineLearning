package org.intelligentjava.regression.linearregression;

import java.io.IOException;

import org.jblas.DoubleMatrix;
import org.junit.Assert;
import org.junit.Test;

public class NormalEquationCostFunctionMinimizerTest {

	@Test
	public void testMinimizeCostFunctionMultipleFeatures() throws IOException {
		NormalEquationCostFunctionMinimizer minimizer = new NormalEquationCostFunctionMinimizer();
		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile(getClass().getClassLoader().getResource("linearregression/ex1data2.txt").getFile());
		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0)), dataMatrix.getColumn(1));
		DoubleMatrix values = dataMatrix.getColumn(2);
		
		DoubleMatrix theta = minimizer.minimizeCostFunction(features, values);
//		Assert.assertEquals(theta.get(0, 0), 89597.90954279806, 0.0000000000001);
//		Assert.assertEquals(theta.get(1, 0), 139.21067401762548, 0.0000000000001);
//		Assert.assertEquals(theta.get(2, 0), -8738.019112327602, 0.0000000000001);
		Assert.assertEquals(new Predictor().predict(new DoubleMatrix(1, 3, 1d, 1650d, 3d), theta), 293081.4643348973, 0.0000000000001);
	}
	
}
