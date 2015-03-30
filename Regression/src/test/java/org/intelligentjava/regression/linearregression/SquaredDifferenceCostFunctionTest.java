package org.intelligentjava.regression.linearregression;

import java.io.IOException;

import org.jblas.DoubleMatrix;
import org.junit.Assert;
import org.junit.Test;

public class SquaredDifferenceCostFunctionTest {
	
	@Test
	public void testComputeCostOneFeature() throws IOException {
		SquaredDifferenceCostFunction costFunction = new SquaredDifferenceCostFunction();
		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile(getClass().getClassLoader().getResource("linearregression/ex1data1.txt").getFile());
		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0));
		DoubleMatrix values = dataMatrix.getColumn(1);
		DoubleMatrix theta = DoubleMatrix.zeros(2, 1);
		double cost = costFunction.computeCost(features, values, theta);
		Assert.assertEquals(cost, 32.072733877455654, 0.000000000000001);
	}

}
