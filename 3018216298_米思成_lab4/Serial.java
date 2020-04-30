package lab4_Concurrency;

import java.math.BigDecimal;

public class Serial {
	
	public static void main(String[] args) {
		 MatrixGenerator mx = new MatrixGenerator();
		 int i = mx.i;
		 int j = mx.j;
		 int k = mx.k;
		 double[][] M = new double[i][k];
		 
		 for (int x = 0; x<i; x++) {
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

	}

}
