package designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理 实现InvocationHandler接口
 *
 * @author will
 * @date 2019/12/13
 */
public class DynamicProxyDemo1 {

    public interface IUserDao {
        void save();
    }

    public static class UserDao implements IUserDao {
        @Override
        public void save() {
            System.out.println("saving data");
        }
    }

    public static class ProxyFactory {
        private Object target;
        public ProxyFactory (Object target) {
            this.target = target;
        }

        public Object getProxyInstance() {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                            target.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("start..................");
                        Object returnValue = method.invoke(target, args);
                        System.out.println("end--------------------");
                        return null;
                    });
        }
    }

    public static void main(String[] args) {
        IUserDao target = new UserDao();
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("target class: " + target.getClass());
        System.out.println("proxy class: " + proxy.getClass());
        proxy.save();
    }

}
