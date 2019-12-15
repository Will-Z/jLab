package designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *      目标类要实现业务接口
 *      代理类实现InvocationHandler接口
 *
 * @author will
 * @date 2019/12/13
 */
public class DynamicProxyDemo {

    public interface ICook {
        void dealWithFood();
        void cook();
    }

    public static class CookManager implements ICook {
        @Override
        public void dealWithFood() {
            System.out.println("food has been dealed with");
        }

        @Override
        public void cook() {
            System.out.println("cook food");
        }
    }

    public static class DynamicProxyHandler implements InvocationHandler {
        ICook realCookManager;

        DynamicProxyHandler(ICook realCookManager) {
            this.realCookManager = realCookManager;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("invoke start........................");
            System.out.println(method.getName());
            method.invoke(realCookManager, args);
            System.out.println("invoke end--------------------------");
            return null;
        }
    }

    public static void main(String[] args) {
        CookManager cookManager = new CookManager();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(cookManager);
        ICook iCook = (ICook) Proxy.newProxyInstance(DynamicProxyHandler.class.getClassLoader(),
                                                        CookManager.class.getInterfaces(), dynamicProxyHandler);
        System.out.println(iCook.getClass().getName());
        iCook.dealWithFood();
        iCook.cook();

    }

}
