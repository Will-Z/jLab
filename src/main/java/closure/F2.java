package closure;

/**
 * @author will
 * @date 2019/12/14
 */
public class F2 {
    private int num = 0;
    public F2(){}

    public F2(int num) {
        this.num = num;
    }
    public F2 apply(int a) {
        return new F2(num + a * a);
    }

    public int get() {
        return num;
    }
}
