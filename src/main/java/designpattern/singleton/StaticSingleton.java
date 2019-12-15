package designpattern.singleton;

/**
 * 最优单例
 *
 * getInstance()方法中没有锁, 高并发下性能优越;
 * 且能够实现延迟加载
 *
 * @author will
 * @date 2019/12/15
 */
public class StaticSingleton {
    private StaticSingleton() {}

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
