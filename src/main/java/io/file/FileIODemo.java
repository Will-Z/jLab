package io.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * nio包下的Files可以让文件读写很方便
 *
 * @author will
 * @date 2019/12/22
 */
public class FileIODemo {
    private static final String CONTENT = "耿耿星河 天下千秋\n" + "test test";

    private void writeFile(String filePath) throws IOException {
        Files.write(Paths.get(filePath), CONTENT.getBytes(StandardCharsets.UTF_8));
    }

    private void readFile(String filePath) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        System.out.println(new String(data, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws Exception {
        FileIODemo instance = new FileIODemo();
        String filePath = "/Users/will/Programming/playground/jLab/src/main/java/io/file/test.txt";
//        instance.writeFile(filePath);
        instance.readFile(filePath);

    }

}
