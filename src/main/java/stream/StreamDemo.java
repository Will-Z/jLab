package stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author will
 * @date 2020/1/1
 */
public class StreamDemo {
    public static void main(String[] args) {

        // 产生5个随机数
//        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        long count = Stream.of("a", "b", "", "cc").filter(x -> x != "").count();
        System.out.println(count);
    }
}
