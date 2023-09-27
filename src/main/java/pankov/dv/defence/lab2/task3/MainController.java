package pankov.dv.defence.lab2.task3;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class MainController {
    public static void main(String[] args) {
        String line = "Hello world!";

        SHA256 sha256 = new SHA256(line);
        System.out.println("Моя реализация:" + sha256.getHash());

        String sha256hex = Hashing.sha256()
                .hashString(line, StandardCharsets.UTF_8)
                .toString();
        System.out.println("Библиотечная реализация:" + sha256hex);
    }
}
