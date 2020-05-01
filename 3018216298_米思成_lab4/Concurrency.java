package lab4_Concurrency;

import java.math.BigDecimal;

public class Concurrency {

	public static void main(String[] args) throws InterruptedException {
		
		//这里分为两个线程分别计算奇数行和偶数行的矩阵乘法
	    MatrixThread mt = new MatrixThread();
	    Thread t1 = new Thread(mt, "线程1");
	    Thread t2 = new Thread(mt, "线程2");
	    t1.start();
	    t2.start();
	    double[][] MC = mt.print();
	    
	    //使用断言判断结果正确性
	    double[][] mx1 = mt.ReturnMx1();
	    double[][] mx2 = mt.ReturnMx2();
	    double[][] MS =  Serial.SerialCalculate(mx1, mx2);
	    assert (MxEqual(MS,MC)):"经断言判断，串行计算结果与并发计算结果不同";
	    System.out.println("经断言判断，串行计算结果与并发计算结果相同");
	}
	
	//判断串行和并发计算结果是否相同
	public static boolean MxEqual(double[][] MS, double[][] MC) {
		for (int i = 0; i<MS.length; i++) {
			for(int j = 0; j<MS[0].length; j++) {
				if (MS[i][j] != MC[i][j])
					return false;
			}
		}
		return true;	
	}

}

class MatrixThread implements Runnable {

	MatrixGenerator mx = new MatrixGenerator();
	int i = mx.i;
	int j = mx.j;
	int k = mx.k;
	double[][] M = new double[i][k];
	
	public void Calculate() {
		//计算奇数行的矩阵乘法
		if (Thread.currentThread().getName().equals("线程1")) {
			synchronized(this) {
				System.out.println(Thread.currentThread().getName() + " 奇数行 计算结果：");
		        for (int x = 0; x<i; x=x+2) {
			        for(int y = 0; y<k; y++) {
				        M[x][y] = 0;
				        for(int z=0; z<j ; z++){
					        M[x][y] += mx.mx1[x][z] * mx.mx2[z][y];
				        }
				    //保留两位小数
				    BigDecimal bg = new BigDecimal(M[x][y]);
				    M[x][y] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    System.out.print(M[x][y] + "  ");
			        }
			        System.out.println();
		         }
		        System.out.println();
	        }
		}
		
		//计算偶数行的矩阵乘法
		if (Thread.currentThread().getName().equals("线程2")) {
			synchronized(this) {
				System.out.println(Thread.currentThread().getName() + " 偶数行 计算结果：");
				for (int x = 1; x<i; x=x+2) {
					for(int y = 0; y<k; y++) {
						M[x][y] = 0;
						for(int z=0; z<j ; z++){
							M[x][y] += mx.mx1[x][z] * mx.mx2[z][y];
						}
						//保留两位小数
						BigDecimal bg = new BigDecimal(M[x][y]);
						M[x][y] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						System.out.print(M[x][y] + "  ");
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
	
	
	@Override
	public void run() {
		Calculate();
	
	}
	
	//输出并发计算总结果
	public double[][] print() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("并发计算结果：");
		for (int m = 0; m<i; m++) {
			for(int n = 0; n<k; n++) {
				System.out.print(M[m][n]);
				System.out.print("  ");
			}
			System.out.println();
		}
		System.out.println();
		return M;
	}
	
	
	public double[][] ReturnMx1() {
		return mx.mx1;
	}
	public double[][] ReturnMx2() {
		return mx.mx2;
	}
}