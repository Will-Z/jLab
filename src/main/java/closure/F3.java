package closure;

/**
 * @author will
 * @date 2019/12/14
 */
public class F3 {

        public F3 apply(final int v) {
            return new F3() {
                @Override
                public int get() {
                    return F3.this.get() + v * v;
                }
            };
        }

        public int get() {
            return 0;
        }
}
