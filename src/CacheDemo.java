import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述:伪缓存demo
 *
 * @author DELL
 * @create 2018-01-03 14:48
 */
public class CacheDemo {
    private Map<String,Object> cache = new HashMap<String,Object>();

    public static void main(String[] args) {

    }
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public Object getData(String key) {
        readWriteLock.readLock().lock();
        Object value =null;
        try {
            value = cache.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaa"; //实际失去queryDB
                    }
                }finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        }finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}