package ca.mcgill.ecse420.a3;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.stream.DoubleStream;

public class VectorParallelAdd implements Callable<double[]>{
  double[] a, b, result;
  static ExecutorService exec;
  
  // constructor
  public VectorParallelAdd( double[] a, double[] b, ExecutorService exec) {
  this.a = a;
  this.b = b;
  this.exec = exec;
  }
  
  // call() returns result for future.get()
  public double[] call() throws Exception {
    int n = a.length;
    result = new double[n];
    
    if (n == 1) {
      // base case one element in each array
      result[0] = a[0] + b[0];
      
    } else {
      // split vectors into halves
      double[] a1 = Arrays.copyOfRange( a, 0, n/2 );
      double[] a2 = Arrays.copyOfRange( a, n/2, n );
      double[] b1 = Arrays.copyOfRange( b, 0, n/2 );
      double[] b2 = Arrays.copyOfRange( b, n/2, n );
      
      // create new VectorAdd jobs with array halves, pass to same thread pool to execute
      Future<double[]> f1 = exec.submit( new VectorParallelAdd( a1, b1, exec ) );
      Future<double[]> f2 = exec.submit( new VectorParallelAdd( a2, b2, exec ) );
      
      // get array results when completed
      while (!f1.isDone() || !f2.isDone()) {
      }
      double[] c1 = f1.get();
      double[] c2 = f2.get();
      
      // concatenate two halves of the result
      result = DoubleStream.concat(Arrays.stream(c1), Arrays.stream(c2)).toArray();
      
    }
    return result;
  }
}
