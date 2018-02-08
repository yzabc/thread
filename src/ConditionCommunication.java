import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:Condition示例 仅仅用lock为互斥 使用condition后会既是互斥又会协调
 * 即为 condition可用await和single方法替代了原有的wait和notify方法，实现了交替进行
 *
 * @author DELL
 * @create 2018-01-03 15:45
 */
public class ConditionCommunication {
    /**
     * @param args
     */
    public static void main(String[] args) {

        final Business business = new Business();
        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for(int i=1;i<=50;i++){
                            business.sub(i);
                        }

                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            business.main(i);
        }

    }
   static class Business {
        Lock lock = new ReentrantLock();
        private boolean bShouldSub = true;
        Condition condition = lock.newCondition();
        public  void sub(int i){
            lock.lock();
            try {
                while (!bShouldSub) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = false;
//                this.notify();
                condition.signal();
            }finally {
                lock.unlock();
            }
        }

        public  void main(int i) {
            lock.lock();
            try {
                while (bShouldSub) {
                    try {
//                        this.wait();
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = true;
//                this.notify();
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
