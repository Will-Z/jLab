package closure;

/**
 * @author will
 * @date 2019/12/14
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(new F().apply(1).apply(2).apply(3).get());

//        var f = new F();
//        var c = f.apply(1);
//        System.out.println(c.apply(2).apply(3).get());


        var f = new F3();
        var c = f.apply(1);
        System.out.println(c.apply(2).get());

    }
}
