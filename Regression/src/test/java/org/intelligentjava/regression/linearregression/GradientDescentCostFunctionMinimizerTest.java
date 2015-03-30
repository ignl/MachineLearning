package org.intelligentjava.regression.linearregression;

import java.io.IOException;

import org.intelligentjava.regression.linearregression.GradientDescentCostFunctionMinimizer;
import org.jblas.DoubleMatrix;
import org.junit.Test;

public class GradientDescentCostFunctionMinimizerTest {

	@Test
	public void testMinimizeCostFunctionOneFeature() throws IOException {
		GradientDescentCostFunctionMinimizer minimizer = new GradientDescentCostFunctionMinimizer(0.01, 1500, false);
		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(5), new DoubleMatrix(new double[]{0, 1, 2, 3, 4}));
		DoubleMatrix values = new DoubleMatrix(new double[]{3, 6, 7, 8, 11});
		
		DoubleMatrix theta = minimizer.minimizeCostFunction(features, values);
		System.out.println((theta.get(0, 0)));
		System.out.println((theta.get(1, 0)));
	}
	
//	@Test
//	public void testMinimizeCostFunctionOneFeature() throws IOException {
//		GradientDescentCostFunctionMinimizer minimizer = new GradientDescentCostFunctionMinimizer(0.01, 1500, false);
//		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile("test/linearregression/ex1data1.txt");
//		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0));
//		DoubleMatrix values = dataMatrix.getColumn(1);
//		
//		DoubleMatrix theta = minimizer.minimizeCostFunction(features, values);
//		Assert.assertEquals(theta.get(0, 0), -3.6302914394044015);
//		Assert.assertEquals(theta.get(1, 0), 1.1663623503355864);
//	}
//
//	@Test
//	public void testMinimizeCostFunctionMultipleFeatures() throws IOException {
//		GradientDescentCostFunctionMinimizer minimizer = new GradientDescentCostFunctionMinimizer(0.01, 100, true);
//		DoubleMatrix dataMatrix = DoubleMatrix.loadCSVFile("test/linearregression/ex1data2.txt");
//		DoubleMatrix features = DoubleMatrix.concatHorizontally(DoubleMatrix.concatHorizontally(DoubleMatrix.ones(dataMatrix.rows), dataMatrix.getColumn(0)), dataMatrix.getColumn(1));
//		DoubleMatrix values = dataMatrix.getColumn(2);
//		
//		DoubleMatrix theta = minimizer.minimizeCostFunction(features, values);
//		Assert.assertEquals(theta.get(0, 0), 215810.6167913787);
//		Assert.assertEquals(theta.get(1, 0), 61384.0312518608);
//		Assert.assertEquals(theta.get(2, 0), 20273.550453378848);
//		Assert.assertEquals(new Predictor(minimizer.getMu(), minimizer.getSigma()).predict(new DoubleMatrix(1, 3, 1d, 1650d, 3d), theta), 184188.79866324988);
//	}
	
}
