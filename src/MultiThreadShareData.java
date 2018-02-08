/**
 * 描述:多线程加减法
 *
 * @author DELL
 * @create 2018-01-02 17:12
 */
public class MultiThreadShareData {
    public static void main(String[] args) {
        final SharaData1 sharaData1 = new SharaData1();
        new Thread(new Runnable() {
            @Override
            public void run() {
              sharaData1.decrement();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sharaData1.increment();
            }
        }).start();
    }

    class MyRunnable implements Runnable{
        private final SharaData1 data1;

        public MyRunnable(SharaData1 data1){
            this.data1 = data1;
        }

        @Override
        public void run() {
            data1.decrement();
        }
    }
    class MyRunnable2 implements Runnable{
        private final SharaData1 data1;

        public MyRunnable2(SharaData1 data1){
            this.data1 = data1;
        }

        @Override
        public void run() {
            data1.increment();//+1
        }
    }
    static class SharaData1 /*implements Runnable*/{
        /*private int count = 100;
        @Override
        public void run() {
            while(true){
                count--;
            }
        }*/


        private int j = 0;
        public synchronized void increment(){
            j++;
        }
        public synchronized void decrement(){
            j--;
        }


    }

}