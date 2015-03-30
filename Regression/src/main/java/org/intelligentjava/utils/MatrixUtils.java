package org.intelligentjava.utils;

import org.jblas.DoubleMatrix;

/**
 * @author Ignas
 *
 */
public class MatrixUtils {
	
	 public static void printMatrix(DoubleMatrix matrix) {
		 for (int i = 0; i < matrix.rows; i++) {
			 for (int j = 0; j < matrix.columns; j++) {
				 System.out.print(matrix.get(i, j) + " ");
			 }
			 System.out.println();
		 }
	 }

}
