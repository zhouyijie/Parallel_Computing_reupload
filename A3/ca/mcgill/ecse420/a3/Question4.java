package ca.mcgill.ecse420.a3;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question4 {
  static int size, numThread;
  static double[][] a;
  static double[] b;
  static ExecutorService exec;
  
  public static void main(String[] args) {
    size = 2000;
    numThread = Runtime.getRuntime().availableProcessors();;
    exec = Executors.newFixedThreadPool(numThread);
    a = createMatrix(size);
    b = createVector(size);
    
    System.out.println("Number of threads: " + numThread);
        
    verify(testSequential(a, b), testParallel(a, b));
  }
  
  public static double[] testSequential(double[][] matrix, double[] vector) {
    
    //sequential multiplication test
    System.out.println("Sequential test starting...");
    
    long startTime = System.currentTimeMillis();
    
    MatrixVectorSequentialMultiply sequential = new MatrixVectorSequentialMultiply( matrix, vector );
    double[] result = sequential.multiply();
    
    long endTime = System.currentTimeMillis();
    
    System.out.println("Sequential Multiplication completed in " +
                      (endTime - startTime) + " milliseconds");
    return result;
  }
  
  public static double[] testParallel(double[][] matrix, double[] vector) {
    
    //parallel multiplication test
    System.out.println("Parallel test starting...");
    
    long startTime = System.currentTimeMillis();
    
    double[] result = null;
    
    MatrixVectorParallelMultiply multiplier = new MatrixVectorParallelMultiply( a, b, exec );
    Future<double[]> future = exec.submit( multiplier );

    try {
      
      result = future.get();
      
    } catch( Exception e ) {
      
      e.printStackTrace();
      
      System.out.println(e);
    }
    
    long endTime = System.currentTimeMillis();
    
    System.out.println("Parallel Multiplication completed in " +
                      (endTime - startTime) + " milliseconds");
    
    exec.shutdown();
    
    while(!exec.isTerminated()) {
      
    }
    
    return result;
  }
  
  private static double[][] createMatrix(int a) {
    // create random matrix
    
    double[][] matrix = new double[a][a];
    
    for (int i = 0; i < a; i++) {
      for (int j = 0; j < a; j++) {
        matrix[i][j] = (int) (Math.random()*10);
      }           
    }
    
    System.out.println("Generated Matrix of size " + a + " x " + a);
    
    return matrix;
  }
  
  private static double[] createVector(int a) {
    // create random vector
    
    double[] vector = new double[a];
    
    for (int i = 0; i < a; i++) {
      vector[i] = (int) (Math.random()*10);           
    }
    
    System.out.println("Generated Vector of size " + a );
    
    return vector;
  }
  
  
  public static void verify(double[] a, double[] b) {
  //compare results to verify algorithm
    
    System.out.println("Result vector 1: " + Arrays.toString(a));
    System.out.println("Result vector 2: " + Arrays.toString(b));

    if (Arrays.equals(a, b)) {
      System.out.println("Results are equal");
    }
  } 
  
}
