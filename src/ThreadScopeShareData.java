import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 描述:线程数据共享
 *
 * @author DELL
 * @create 2017-12-25 20:44
 */
public class ThreadScopeShareData {
    private static int data = 0;
    private static Map <Thread,Integer> threadData = new HashMap<Thread,Integer>();
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println("**************************************************************************");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data  = new Random().nextInt();
                    x.set(data);
                    System.out.println(Thread.currentThread().getName()+"has put data:"+data);
                    threadData.put(Thread.currentThread(),data);

                    MyThreadScopeData instance = MyThreadScopeData.getInstance();
                    instance.setName("name"+data);
                    instance.setAge("age"+data);
                    MyThreadScopeData instance2 = MyThreadScopeData.getInstance();

                    new A().get();
                    new B().get();
                    System.out.println("---------------------------------------------------------------------");
                }
            }).start();
        }

    }
    static class A{
        int data = threadData.get(Thread.currentThread());
        public void get(){
            System.out.println("A from :"+Thread.currentThread().getName()+"  A get data:"+data);

            MyThreadScopeData instance = MyThreadScopeData.getInstance();
            MyThreadScopeData instance2 = MyThreadScopeData.getInstance();
            System.out.println("A FROM"+ Thread.currentThread().getName()
            + " getMyData:" + instance.getName()+","+
            instance.getAge()+"实例2"+instance2.getAge());
        }
    }

    static class B{
        int data = threadData.get(Thread.currentThread());
        public void get (){
            System.out.println("B from"+Thread.currentThread().getName()+" B get data:"+data);

            MyThreadScopeData instance = MyThreadScopeData.getInstance();
            System.out.println("B FROM"+ Thread.currentThread().getName()
                    + " getMyData:" + instance.getName()+","+
                    instance.getAge());
        }
    }
    static  class MyThreadScopeData{
        private MyThreadScopeData (){}
        public static MyThreadScopeData getInstance(){
            MyThreadScopeData instance = map.get();//每个线程首先会从map中取实例
            if (instance == null) {
                System.out.println("每个线程进来都走这里");
                instance = new MyThreadScopeData();
                map.set(instance);
            }
            return instance;
        }
        private static ThreadLocal <MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }


}