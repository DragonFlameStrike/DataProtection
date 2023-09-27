package pankov.dv.defence.lab2.task2;

import lombok.SneakyThrows;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class MainController {

    @SneakyThrows
    public static void main(String[] args) {
        String file = "D:\\Program Files\\JetBrains\\IntelliJ IDEA 2021.3.1\\IdeaProjects\\defence\\src\\main\\java\\pankov\\dv\\defence\\lab2\\task2\\test.txt";

        AES aes1 = new AES(toHex(parseFileToString(file)), toHex("keyskeyskeyskeyskeys"));
        aes1.print();
        String encryptResult = aes1.encrypt();
        System.out.println("Результат шифрования: "+ encryptResult);

        aes1.setText(encryptResult);
        aes1.print();
        String decryptResult = aes1.decrypt();
        System.out.println("Результат дешифрования: "+ decryptResult);
    }

    private static String parseFileToString(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(StandardCharsets.UTF_16)));
    }
}
