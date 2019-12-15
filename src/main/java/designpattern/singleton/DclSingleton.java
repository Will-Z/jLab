package designpattern.singleton;

/**
 * Double Check Lazy 单例
 *
 * 优点: 线程安全 延迟加载
 * 缺点: 不够优雅
 *
 * @author will
 * @date 2019/12/15
 */
public class DclSingleton {
    private static volatile DclSingleton instance;
    private DclSingleton() {}

    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized(DclSingleton.class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }
}
