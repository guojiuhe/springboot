package guojiuhe.demo.thread;

public class MyThread1 extends Thread{
	@Override
    public void run() {
		for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
