package ca.mcgill.ecse420.a1;

public class parallelMultiply implements Runnable {
  final double [][] a;
  final double [][] b;
  final double [][] result;
  final int n;
  final int i;
  final int j;

  public parallelMultiply(int n, int i, int j, double[][] a, double[][] b, double[][] result) {
    this.n=n;
    this.i=i;
    this.j=j;
    this.a=a;
    this.b=b;
    this.result=result;
  }
  
  @Override
  public void run() {
    for(int k = 0; k < n; k++) {
      result[i][j] += a[i][k] * b[k][j];
    }
  }
  
}
