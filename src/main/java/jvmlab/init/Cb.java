package jvmlab.init;

/**
 * @author will
 * @date 2020/1/3
 */
public class Cb extends Ca{
    public Cb() {
        System.out.println("construct B");
    }

    { System.out.println("I'm B class"); }

    static { System.out.println("static B"); }

    public static void main(String[] args) {
        System.out.println("------------main start--------------");
        new Cb();
        System.out.println("------------main end--------------");

    }
}
