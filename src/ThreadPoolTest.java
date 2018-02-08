import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:线程池
 *
 * @author DELL
 * @create 2018-01-02 21:28
 */
public class  ThreadPoolTest {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(3); //新建指定数量线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();  // 新建任务对应数量线程
          ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 新建单一线程，永远保持有一个

        for (int i = 1; i <=10 ; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j =1; j <=10 ; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" is loop of "+j+" for task of "+task);
                    }
                }
            });
        }
        System.out.println(" all tasks have committed!");
//        threadPool.shutdown();
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing");
            }
        },6,2,TimeUnit.SECONDS);
    }
}
