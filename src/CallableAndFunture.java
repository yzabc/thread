import java.util.concurrent.*;

/**
 * 描述:Callable返回执行结果
 *
 * @author DELL
 * @create 2018-01-02 22:09
 */
public class CallableAndFunture {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future=
        threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });
        System.out.println("等待结果");
        try {
            System.out.println("拿到结果"+ future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}