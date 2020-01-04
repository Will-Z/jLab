package jvmlab.init;

/**
 * @author will
 * @date 2020/1/3
 */
public class Ca {
    public Ca() {
        System.out.println("construct A");
    }

    { System.out.println("I'm A class"); }

    static { System.out.println("static A"); }

}
