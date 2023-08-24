package guojiuhe.demo.thread;

import java.util.concurrent.Callable;

public class MyThread3 implements Callable{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
                Thread.sleep(1000);
            }
        }
        return sum;
	}

}
