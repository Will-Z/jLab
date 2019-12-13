package designpattern.proxy.dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author will
 * @date 2019/12/13
 */
public class DynamicProxyDemo2 {
    public static class UserDao {
        public void save() {
            System.out.println("saving data");
        }
    }

    public static class ProxyFactory implements MethodInterceptor {
        private Object target;
        public ProxyFactory(Object target) {
            this.target = target;
        }

        public Object getProxyInstance() {
            Enhancer en = new Enhancer();
            en.setSuperclass(target.getClass());
            en.setCallback(this);
            return en.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("start.........");
            Object returnValue = method.invoke(target, objects);
            System.out.println("end-----------");
            return null;
        }
    }

    public static void main(String[] args) {
        UserDao target = new UserDao();
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("target class: " + target.getClass());
        System.out.println("proxy class: " + proxy.getClass());
        proxy.save();
    }
}
