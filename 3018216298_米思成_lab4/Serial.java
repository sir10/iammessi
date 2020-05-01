package lab4_Concurrency;

import java.math.BigDecimal;

public class Serial {
	
	public static double[][] SerialCalculate(double[][] mx1, double[][] mx2){
		 int i = mx1.length;
		 int j = mx2.length;
		 int k = mx2[0].length;
		 double[][] M = new double[i][k];
		 
		 System.out.println("串行计算结果：");
		 for (int x = 0; x<i; x++) {
			for(int y = 0; y<k; y++) {
				M[x][y] = 0;
				for(int z=0; z<j ; z++){
					M[x][y] += mx1[x][z] * mx2[z][y];
				}
				//保留两位小数
				BigDecimal bg = new BigDecimal(M[x][y]);
				M[x][y] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				System.out.print(M[x][y] + "  ");
			}
			System.out.println();
		}
		return M;
	}
	
//	public static void main(String[] args) {
//		 MatrixGenerator mx = new MatrixGenerator();
//		 SerialCalculate(mx.mx1, mx.mx2);
//	}
}
