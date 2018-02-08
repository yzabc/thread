import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:增强for
 *
 * @author DELL
 * @create 2018-01-04 20:21
 */
public class ListTest {
    public static void main(String[] args) {
        User user = new User("张三",1);
        User user2 = new User("李四",2);
        User user3 = new User("wangwu",3);
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        users.add(user3);
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getAge()==1){
                iterator.remove();
            }else {
                System.out.println(user.getName());
            }
        }
//        for(User u : users){
//            if (u.getAge() == 2) {
//                users.remove(u);
//            }
//            System.out.println(u);
////            System.out.println(u.getName());
//        }
    }
    static class User{
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}