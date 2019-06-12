package ca.mcgill.ecse420.a3;

import java.util.concurrent.Callable;

public class MatrixVectorSequentialMultiply implements Callable<double[]> {
    final double [][] a;
    final double [] b;
    final double [] result;
    final int n;

    public MatrixVectorSequentialMultiply(double[][] a, double[] b) {
      this.n = b.length;
      this.a = a;
      this.b = b;
      this.result = new double[n];
    }
    
    public double[] call() throws Exception {
      return multiply();
    }
    
    public double[] multiply() {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          result[i] += a[i][j] * b[j];
        }
      }
      return result;
    }
}
