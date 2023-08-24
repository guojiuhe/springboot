package guojiuhe.demo.leecode;

public class _0005_luoXuanJuzhen {

	public static void main(String[] args) {
		/*
		 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。 
		 * 示例: 
		 * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
		 */
		
	}

	public int[][] generateMatrix(int n) {
		/*
		 * n = 5
		 * 01 02 03 04 05
		 * 16 17 18 19 06
		 * 15 24 25 20 07
		 * 14 23 22 21 08
		 * 13 12 11 10 09
		 * 
		 * 00 01 02 03 04
		 * 10 11 12 13 14
		 * 20 21 22 23 24
		 * 30 31 32 33 34
		 * 40 41 42 43 44
		 */  
		
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;

        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右 
        	// 第一次循环 loop = 1, i =0 , 当j = 4 时跳出循环 00 01 02 03 
        	// 第二次循环 loop = 2, start = 1, j = 1, j < 3, 11 12
        	// 第三次循环 loop = 3, start = 2, j = 2, j < 2 无输出
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下 
            // 第一次循环 此时 j = 4, 当 i= 4时跳出循环 04 14 24 34
        	// 第二次循环  j = 3, i = 1, i < 3, 13 23
        	// 第三次循环 i = 2， i < 2, 无输出
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            // 第一次循环 j = 4 , i = 4, 当j=0时跳出循环 44 34 24 14
        	// 第二次循环  j = 3, i = 3, j >=2 , 33 32
        	// 第三次循环 i = 2， j = 2, j > 3 无输出
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            // 第一次循环 j = 0; i = 4, 当i=0时跳出循环 40 30 20 10
        	// 第二次循环  j = 1, i = 3, j >=2 , 31 21
        	// 第三次循环 i = 2， j = 2, i > 3 无输出
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            // 循环结束, i = 0, j = 0
            start++;
        }

        if (n % 2 == 1) {
        	// 奇数情况 填补 22
            res[start][start] = count;
        }      
        return res;
    }
}
