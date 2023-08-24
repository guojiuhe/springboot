package guojiuhe.controller;

import java.util.HashMap;

public class MainDemo {

	public static void main(String[] args) {
		
		long l = 20220122l;
		System.out.println(l / 100 * 100 + 1);
	}
	
	public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        for (int i = 0; i < 46341; i ++) {
            if (i*i <=x && (i+1) *(i+1) > x) {
                return i;
            }
        }
        return -1;
    }
}
