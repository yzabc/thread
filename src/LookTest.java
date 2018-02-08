import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:传统线程synchronized与1.5之后lock区别
 *
 * @author DELL
 * @create 2017-12-23 23:56
 */
public class LookTest {

    public static void main(String[] args) {
        new LookTest().init();
    }

    public  void init(){
        Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("zhangsan");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("lisi");
                }
            }
        }).start();
    }
    static   class Outputer {
        Lock lock = new ReentrantLock();
        public void output(String name){
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
            finally {
                lock.unlock();
            }
        }
        public synchronized void output2(String name){
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
        public static synchronized void output3(String name){
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}