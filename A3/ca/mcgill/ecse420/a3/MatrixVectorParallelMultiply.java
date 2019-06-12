package ca.mcgill.ecse420.a3;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.stream.DoubleStream;

public class MatrixVectorParallelMultiply implements Callable<double[]>{
  double[][] a;
  double[] b, result;
  int n, m;
  static ExecutorService exec;
  
  // constructor
  public MatrixVectorParallelMultiply( double[][] a, double[] b, ExecutorService exec) {
  this.a = a;
  this.b = b;
  this.exec = exec;
  n = a.length;
  m = a[0].length;
  result = new double[n];
  
  }
  
//call() returns result for future.get()
 public double[] call() throws Exception {
   
   if (n == 1 && m == 1) {
     
     // base case one element in each vector 
     result[0] = a[0][0] * b[0];
    
   } else if (n == 1 && m == 2) {
     
     // base case 1 x 2 vector multiply with 2 x 1 vector
     result[0] = a[0][0] * b[0] + a[0][1] * b[1];
   
   } else if (n == 2 && m == 1) {
     
     // base case 2 x 1 vector multiply with 1 x 1 vector
     result[0] = a[0][0] * b[0];
     result[1] = a[1][0] * b[0];
     
   } else {
     
     // split matrix into 4, vector into 2
     double[][] a11 = deepCopy( a, 0, n/2, 0, m/2 );
     double[][] a12 = deepCopy( a, 0, n/2, m/2, m );
     double[][] a21 = deepCopy( a, n/2, n, 0, m/2 );
     double[][] a22 = deepCopy( a, n/2, n, m/2, m );
     double[] b11 = Arrays.copyOfRange( b, 0, m/2 );
     double[] b21 = Arrays.copyOfRange( b, m/2, m );
     
     // create new MatrixVectorParallelMultiply jobs with matrix quarters and vector halves, pass to same thread pool to execute
     Future<double[]> f1 = exec.submit( new MatrixVectorSequentialMultiply( a11, b11 ) );
     Future<double[]> f2 = exec.submit( new MatrixVectorSequentialMultiply( a12, b21 ) );
     Future<double[]> f3 = exec.submit( new MatrixVectorSequentialMultiply( a21, b11 ) );
     Future<double[]> f4 = exec.submit( new MatrixVectorSequentialMultiply( a22, b21 ) );
     
     // get array results when completed
     while (!f1.isDone() || !f2.isDone() || !f3.isDone() || !f4.isDone()) {
     }
     
     // Add multiplication vector results
//     Future<double[]> f5 = exec.submit( new VectorParallelAdd( f1.get(), f2.get(), exec ) );
//     Future<double[]> f6 = exec.submit( new VectorParallelAdd( f3.get(), f4.get(), exec ) );

//     while (!f5.isDone() || !f6.isDone()) {
//     }
     
     double[] c11 = f1.get();
     double[] c12 = f2.get();
     double[] c21 = f3.get();
     double[] c22 = f4.get();
     double[] c1 = new double[c11.length];
     double[] c2 = new double[c21.length];
     
     for (int i = 0; i < c11.length; i++) {
       c1[i] = c11[i] + c12[i];
     }
     for (int j = 0; j < c21.length; j++) {
       c2[j] = c21[j] + c22[j];      
     }
     
     // concatenate two halves of the result
     result = DoubleStream.concat(Arrays.stream(c1), Arrays.stream(c2)).toArray();
     
   }
   return result;
 }
 
 private double[][] deepCopy (double[][] in, int startRow, int endRow, int startCol, int endCol) {
   
   double[][] out = new double[endRow - startRow][endCol - startCol];
   
   for(int i = startRow; i < endRow; i++)
     for(int j = startCol; j < endCol; j++)
       out[i - startRow][j - startCol] = in[i][j];
  
   return out;
 }
}
