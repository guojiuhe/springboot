package guojiuhe.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadDemo demo = new ThreadDemo();
		//demo.createThread_1();
		//demo.createThread_2();
		//demo.createThread_3();
		//demo.createThread_4();
		//demo.createThreadPool_1();
		demo.CountdownLatchTest();
	}

	// 创建线程的5种方法
	// 创建线程第1种方法，继承Thread，重写run
	public void createThread_1() {
		MyThread1 t1 = new MyThread1();
		t1.setName("线程1");
		t1.start(); // start 是启动线程， run是直接执行方法
		MyThread1 t2 = new MyThread1();
		t2.setName("线程2");
	    t2.start();
	    
		System.out.println("主线程结束");
	}
	// 创建线程第2种方法，实现runnable实现run，利用实现类构建thread
	public void createThread_2() throws InterruptedException {
		MyThread2 mThread = new MyThread2();
		Thread t1 = new Thread(mThread);
		t1.setName("线程1");
		t1.start(); // start 是启动线程， run是直接执行方法
		//t1.join(); // join 之后线程按顺序执行 线程1 -> 线程2 -> 主线程
		Thread t2 = new Thread(mThread);
		t2.setName("线程2");
	    t2.start();
	    //t2.join();
		System.out.println("主线程结束");
	}
	// 创建线程第3种方法，实现Callable，类似方法runnable，有返回值
	public void createThread_3() {
		MyThread3 mThread = new MyThread3();
		FutureTask futureTask = new FutureTask(mThread);
		Thread t1 = new Thread(futureTask); // 这里参数是FutureTask
		t1.setName("线程1");
		t1.start();
		
		try {
			Integer sum = (Integer)futureTask.get();
			System.out.println("sum is " + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("主线程结束");
	}
	// 方法4 匿名类 runnable简略写法 较常用
	public void createThread_4() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		});
		thread1.setName("线程1");
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
			}
		});
		thread2.setName("线程2");
		thread2.start();
	}
	
	// 方法5 线程池
	@SuppressWarnings("unchecked")
	public void createThreadPool_1() throws InterruptedException, ExecutionException {
		// 创建任务
	    Runnable runnable1 = new Runnable() {
	        @Override
	        public void run() {
	            System.out.println(Thread.currentThread().getName() + "开始执行方法1");
	        }
	    };
	    Runnable runnable2 = new Runnable() {
	        @Override
	        public void run() {
	            System.out.println(Thread.currentThread().getName() + "开始执行方法2");
	        }
	    };

		ExecutorService threadPool_1 = Executors.newFixedThreadPool(5); // 创建固定数目的线程池
		threadPool_1.submit(runnable1);// 2种执行方式 execute & submit, 区别返回值
		threadPool_1.submit(runnable2);
		threadPool_1.execute(runnable1);
		threadPool_1.execute(runnable2);
		
		ExecutorService threadPoo2 = Executors.newCachedThreadPool(); // 可缓存的线程池，线程60s内不会立即终止
		ExecutorService threadPoo3 = Executors.newSingleThreadExecutor(); // 单线程
		ScheduledExecutorService threadPoo4 = Executors.newScheduledThreadPool(5); // 可周期，延时
		threadPoo4.schedule(runnable1,3, TimeUnit.SECONDS);
		// 为什么不建议Executors？ 要么线程数大，要么阻塞队列大，OOM
		
		//阻塞队列
	    BlockingQueue<Runnable> workQueue = null;
		workQueue = new ArrayBlockingQueue<Runnable>(5);//基于数组的先进先出（FIFO）队列，支持公平锁和非公平锁，有界
		//workQueue = new LinkedBlockingQueue<Runnable>();//基于链表的先进先出（FIFO）队列，默认长度为 Integer.MaxValue 有OOM危险，有界
		//workQueue = new LinkedBlockingDeque<Runnable>(); //一个由链表结构组成的,双向阻塞队列，有界
		//2 无界队列
		//workQueue = new PriorityBlockingQueue<Runnable>(); //支持优先级排序的无限队列，默认自然排序，可以实现 compareTo()方法指定排序规则，不能保证同优先级元素的顺序，无界。
		//workQueue = new DelayQueue(); //一个使用优先级队列（PriorityQueue）实现的无界延时队列，在创建时可以指定多久才能从队列中获取当前元素。只有延时期满后才能从队列中获取元素。
		//workQueue = new LinkedTransferQueue<Runnable>(); //一个由链表结构组成的,无界阻塞队列
		//3 同步移交队列
		//workQueue = new SynchronousQueue<Runnable>();//无缓冲的等待队列，队列不存元素，每个put操作必须等待take操作，否则无法添加元素，支持公平非公平锁，无界

		ThreadFactory factory = Executors.defaultThreadFactory(); // 可无，自定义线程工厂，可命名
		RejectedExecutionHandler rejectedExecutionHandler = null; // 4 种拒绝策略
		rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();//默认，队列满了丢任务抛出异常
		//rejectPolicy = new ThreadPoolExecutor.DiscardPolicy();//队列满了丢任务不异常
		//rejectPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();//将最早进入队列的任务删，之后再尝试加入队列
		//rejectPolicy = new ThreadPoolExecutor.CallerRunsPolicy();//如果添加到线程池失败，那么主线程会自己去执行该任务
		int corePoolSize=2;
	    int maximumPoolSize=5;
	    int keepAliveTime=20; 
		ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, factory, rejectedExecutionHandler);

        //执行有返回值线程
		List<Future<String>> futures = new ArrayList<Future<String>>();
	    for(int i=0;i<10;i++) {
	        Future<String> future = (Future<String>) executorService.submit(runnable1);
	        futures.add(future);
	    }
	    for(int i=0;i<futures.size();i++){
	        String result = futures.get(i).get();
	        System.out.println(i+" result = "+result);
	    }
	    
        //执行无返回值线程
	    for(int i=0;i<10;i++) {
	    	executorService.execute(runnable2);
	    }
	}
	
	public void CountdownLatchTest() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		final CountDownLatch count = new CountDownLatch(5);
		for (int i = 0; i < 5; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
						Thread.sleep(1000);
						System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
						count.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		count.await();
		System.out.println("主线程结束");
		

		Semaphore sem = new Semaphore(2); // 限制只有2个线程可以访问
		  for (int i = 0; i < 3; i++) {
		        new Thread(() -> {
		            try {
		                // 默认使用一个许可.
		                sem.acquire();
		                System.out.println(Thread.currentThread() + " I get it.");
		                TimeUnit.SECONDS.sleep(3);
		                System.out.println(Thread.currentThread() + " I release it.");
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            } finally {
		                sem.release();
		            }
		        }).start();
		  }

	}
	
	// 线程池执行的任务需要实现runnable或callable
	// Future 接口 FutureTask 是实现类
	// ThreadFactory 是用来创建线程的，默认即可，继承该类可重命名
	// ThreadLocal 创建线程副本变量
	// Java内存模型（JMM）规定所有变量都存在主内存，每个线程有自己的工作内存，工作内存变量为主存拷贝，这就设计了并发问题
	// volatile 保证可见性（读：强制读主存，写：其他线程可见。大家都知道谁改了这个值），禁止指令重排
	// Synchronized 偏向锁 轻量级锁 重量级锁 自旋发生在重量级锁阶段（发生在轻量级锁阶段的说法是不对的）
	// 刚获取锁：偏向 ； 有其他线程来获取锁（无竞争）：轻量级，之前的线程已经释放锁 ； 有竞争 ： 重量级（自旋获取锁）
	// ReentrantLock 可中断 可立即返回是否获取锁 可等待 支持公平锁 显示加锁解锁
	// 原子类 AtomicInteger
	// AQS Abstract Queued Synchronizer ReentrantLock基于AQS state + 队列
	

}
