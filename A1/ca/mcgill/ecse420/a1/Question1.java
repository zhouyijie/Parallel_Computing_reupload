package ca.mcgill.ecse420.a1;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class Question1 extends Thread {
  static int size, numThread;
  
  public static void main(String[] args) {
    
    sequentialVsParallel();
    
    timeVsThreads();
    
    timeVsSize();
    
  }
  
  private static void sequentialVsParallel() {
    //Question 1.3 testing sequential Vs parallel methods
    //with 4 threads and 2000 x 2000 matrices
    
    double[][] a, b;
    numThread = 4;
    size = 2000;
    
    System.out.println("Matrices size = " + size + " x " + size);
    System.out.println("Number of threads = " + numThread);
    a = createMatrix(size);
    b = createMatrix(size);
    
    //perform sequential test
    long sequentialTime = testSequential(a, b);
    
    //perform parallel test
    long parallelTime = testParallel(a, b);
  }
  
  private static void timeVsThreads() {
    //Question 1.4 testing and ploting execution time Vs number of threads
    
    double[][] a, b;
    size = 2000;
    
    System.out.println("Matrices size = " + size + " x " + size);
    
    a = createMatrix(size);
    b = createMatrix(size);
    
    //perform sequential test
    long sequentialTime = testSequential(a, b);
    
    for (numThread = 1; numThread <= 9; numThread ++) {
      //calculate execution time with increasing number of threads
      //add time values to dataset
      
      System.out.println("Number of threads = " + numThread);
      long parallelTime = testParallel(a, b);

    }
  }
  
  private static void timeVsSize() {
    //Question 1.5 testing and ploting execution time Vs size of matrix
    //with increasing matrices size and 4 threads
    
    double[][] a, b;
    numThread = 4;
    
    for (size = 200; size <= 2000; size +=50) {
      //calculate execution time with increasing size of matrices
      //add time values to dataset
      
      System.out.println("Matrices size = " + size + " x " + size);
      System.out.println("Number of threads = " + numThread);
      
      a = createMatrix(size);
      b = createMatrix(size);
      
      //perform sequential test
      long sequentialTime = testSequential(a, b);
      
      //perform parallel test
      long parallelTime = testParallel(a, b);
    }
  }
  
  private static double[][] createMatrix(int a) {
    // create random matrix
 
    System.out.println("Generating matrix" );
    
    double[][] matrix = new double[a][a];
    
    for (int i = 0; i < a; i++) {
      for (int j = 0; j < a; j++) {
        matrix[i][j] = (int) (Math.random()*10);
      }           
    }
    
    return matrix;
  }
  
  public static long testSequential(double[][] matrix1, double[][] matrix2) {
    //sequential multiplication test
    System.out.println("Sequential test starting...");
    
    long startTime = System.currentTimeMillis();
    double[][] result = sequentialMultiplyMatrix(matrix1,matrix2);
    long endTime = System.currentTimeMillis();
    System.out.println("Sequential Multiplication completed in " +
                      (endTime - startTime) + " milliseconds");
    
    return endTime - startTime;
  }
  
  public static long testParallel(double[][] matrix1, double[][] matrix2) {
    //parallel multiplication test
    System.out.println("Parallel test starting...");
    
    long startTime = System.currentTimeMillis();
    double[][] result = parallelMultiplyMatrix(matrix1,matrix2);    
    long endTime = System.currentTimeMillis();
    System.out.println("Parallel Multiplication completed in " +
                      (endTime - startTime) + " milliseconds");
    
    return endTime - startTime;
  }
  
  public boolean verify(double[][] a, double[][] b) {
  //compare results to verify algorithm
    
    return Arrays.deepEquals(a, b);
  }
  
  public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {
    //a sequential matrix multiplication method 
    
    double[][] result = new double[a.length][b[0].length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < a[0].length; k++) {
          result[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    
    return result;
  }
  
  public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
    //a parallel matrix multiplication method 

    ExecutorService pool = Executors.newFixedThreadPool(numThread);
    double[][] result = new double[a.length][b[0].length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        pool.submit(new parallelMultiply(a[0].length, i, j, a, b, result));
      }
    }
    
    pool.shutdown();
    
    while(!pool.isTerminated()) {
    
    }
    return result;
  }
  
}
