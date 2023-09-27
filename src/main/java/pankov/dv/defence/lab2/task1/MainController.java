package pankov.dv.defence.lab2.task1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MainController {

    public static void main(String[] args) {
        while(true) {
            var values = InputValuesDto.builder().x(4).a(4).c(4).m(10).build();
            var listOfRandNumbers = generateListOfNumbers(values);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите строку:");
            String inputString = scanner.nextLine();
            System.out.println("Выберете операцию: 0 - Шифровка, 1 - Дешивровка");
            String result = switch (scanner.nextInt()) {
                case 0 -> coding(inputString, listOfRandNumbers);
                case 1 -> decoding(inputString, listOfRandNumbers);
                default -> "Неверное значение";
            };

            System.out.println(result);
        }
    }

    private static String coding(String inputString, List<Integer> listOfRandNumbers) {
        int pointer = 0;
        StringBuilder result = new StringBuilder();
        while (pointer < inputString.length()) {
            int letterIndex = inputString.charAt(pointer);
            letterIndex += listOfRandNumbers.get(pointer % listOfRandNumbers.size());
            if (letterIndex >= 256) letterIndex -= 256;
            result.append((char)letterIndex);
            pointer++;
        }
        return result.toString();
    }

    private static String decoding(String inputString, List<Integer> listOfRandNumbers) {
        int pointer = 0;
        StringBuilder result = new StringBuilder();
        while (pointer < inputString.length()) {
            int letterIndex = inputString.charAt(pointer);
            letterIndex -= listOfRandNumbers.get(pointer % listOfRandNumbers.size());
            if (letterIndex < 0) letterIndex += 256;
            result.append(Character.toChars(letterIndex));
            pointer++;
        }
        return result.toString();
    }

    private static List<Integer> generateListOfNumbers(InputValuesDto valueDto) {
        List<Integer> randNumbers = new ArrayList<>();
        randNumbers.add(calculateNextValue(valueDto, valueDto.getX()));
        for (int i = 0; i < 100; i++) {
            Integer nextValue = calculateNextValue(valueDto, randNumbers.get(i));
            randNumbers.add(nextValue);
        }
        return randNumbers;
    }

    private static Integer calculateNextValue(InputValuesDto valueDto, Integer prevValue) {
        return (valueDto.getA() * prevValue + valueDto.getC()) % valueDto.getM();
    }
}
