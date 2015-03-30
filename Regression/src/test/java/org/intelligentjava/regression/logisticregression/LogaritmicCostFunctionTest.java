package org.intelligentjava.regression.logisticregression;

import java.io.IOException;

import org.intelligentjava.regression.logisticregression.LogaritmicCostFunction;
import org.jblas.DoubleMatrix;
import org.junit.Assert;
import org.junit.Test;

public class LogaritmicCostFunctionTest {
	
	@Test
	public void testComputeCost() throws IOException {
		LogaritmicCostFunction costFunction = new LogaritmicCostFunction();
		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile(getClass().getClassLoader().getResource("logisticregression/ex2data1.txt").getFile());
		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0)), dataMatrix.getColumn(1));
		DoubleMatrix values = dataMatrix.getColumn(2);
		DoubleMatrix theta = DoubleMatrix.zeros(3);
		double cost = costFunction.computeCost(features, values, theta);
		Assert.assertEquals(cost, 0.6931471805599458, 0.0000001);
	}
	
	@Test
	public void testSigmoid() {
		DoubleMatrix z = DoubleMatrix.zeros(2, 2);
		z.put(0, 1, -2);
		z.put(1, 0, 2);
		z.put(1, 1, 20);
		LogaritmicCostFunction costFunction = new LogaritmicCostFunction();

		DoubleMatrix sigmoid = costFunction.sigmoid(z);
		Assert.assertEquals(z.rows, sigmoid.rows);
		Assert.assertEquals(z.columns, sigmoid.columns);
		Assert.assertEquals(sigmoid.get(0, 0), 0.5, 0.0000001);
		Assert.assertEquals(sigmoid.get(0, 1), 0.11920292202211755, 0.0000001);
		Assert.assertEquals(sigmoid.get(1, 0), 0.8807970779778823, 0.0000001);
		Assert.assertEquals(sigmoid.get(1, 1), 0.9999999979388463, 0.0000001);
	}
	
	@Test
	public void testSigmoidParameterVector() {
		DoubleMatrix z = DoubleMatrix.zeros(3, 1);
		z.put(0, 0, -2);
		z.put(1, 0, 2);
		z.put(2, 0, 20);
		LogaritmicCostFunction costFunction = new LogaritmicCostFunction();
		
		DoubleMatrix sigmoid = costFunction.sigmoid(z);
		Assert.assertEquals(z.rows, sigmoid.rows);
		Assert.assertEquals(z.columns, sigmoid.columns);
		Assert.assertEquals(sigmoid.get(0, 0), 0.11920292202211755, 0.0000001);
		Assert.assertEquals(sigmoid.get(1, 0), 0.8807970779778823, 0.0000001);
		Assert.assertEquals(sigmoid.get(2, 0), 0.9999999979388463, 0.0000001);
	}

}
