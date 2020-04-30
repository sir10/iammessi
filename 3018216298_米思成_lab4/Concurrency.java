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
	    mt.print();
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
	
	//输出总结果
	public void print() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("总计算结果：");
		for (int m = 0; m<i; m++) {
			for(int n = 0; n<k; n++) {
				System.out.print(M[m][n]);
				System.out.print("  ");
			}
			System.out.println();
		}
	}
	
}