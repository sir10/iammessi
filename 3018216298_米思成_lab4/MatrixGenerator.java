package lab4_Concurrency;

import java.math.BigDecimal;

public class MatrixGenerator {

	public final static int MAX = 10;  //定义矩阵可能的最大维度
	
	public int i = (int) (1 + (Math.random() * MAX));
	public int j = (int) (1 + (Math.random() * MAX));
	public int k = (int) (1 + (Math.random() * MAX));
	public double[][] mx1 = new double[i][j];
	public double[][] mx2 = new double[j][k];
	
	public MatrixGenerator(){
		//生成第一个矩阵
		for (int m = 0; m<i; m++) {
			for(int n = 0; n<j; n++) {
				mx1[m][n] = (-2) + (Math.random() * (3+2));
				//保留两位小数
				BigDecimal bg = new BigDecimal(mx1[m][n]);
	            mx1[m][n] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
		}
		
		//生成第二个矩阵
		for (int m = 0; m<j; m++) {
			for(int n = 0; n<k; n++) {
				mx2[m][n] = (-2) + (Math.random() * (3+2));
				//保留两位小数
				BigDecimal bg = new BigDecimal(mx2[m][n]);
	            mx2[m][n] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
		}
	}
	
}
