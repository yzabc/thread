/**
 * 描述:传统线程
 *
 * @author DELL
 * @create 2017-12-23 23:56
 */
public class TraditionalThreadSynchronized {

    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
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
                    outputer.output2("zhangsan");
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
                    outputer.output3("lisi");
                }
            }
        }).start();
    }
    static   class Outputer {
        public void output(String name){
            int len = name.length();
            synchronized (Outputer.class){
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
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