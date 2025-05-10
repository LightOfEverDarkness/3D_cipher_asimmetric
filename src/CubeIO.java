import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CubeIO {
    public static String getInputTextFromConsole() {
        System.out.print("Введите текст для шифрования: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Ошибка при вводе текста: " + e.getMessage());
            return "";
        }
    }

    public static String getInputFromFile(String path) {
        StringBuilder input = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.ISO_8859_1))){
            String inputLine;
            while ((inputLine = br.readLine()) != null){
                input.append(inputLine);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return input.toString();
    }

    public static String[] splitString(String str) {
        // Проверка, что строка имеет длину 8 символов
        if (str.length() != 8) {
            throw new IllegalArgumentException("Строка должна содержать ровно 8 символов.");
        }

        String[] array = new String[8];

        // Заполнение массива символами из строки
        for (int i = 0; i < 8; i++) {
            array[i] = String.valueOf(str.charAt(i));
        }

        return array;
    }

    public static String[] getHexRepresentation(String input) {
        // Кодируем строки в байты с использованием кодировки Windows-1251
        byte[] encodedBytes = input.getBytes(Charset.forName("Windows-1251"));
        // Создаем массив для хранения шестнадцатеричных значений.
        String[] hexArray = new String[encodedBytes.length];

        for (int i = 0; i < encodedBytes.length; i++) {
            // Заполняем массив шестнадцатеричными значениями.
            hexArray[i] = String.format("0x%02X", encodedBytes[i]);
            /*
               0x - префикс, указывающий на то, что число будет представлено в шестнадцатеричном формате;
               % - начало спецификатора формата;
               02 - две цифры, указывающие на то, что число должно быть выведено с ведущими нулями,
                    если оно состоит из менее чем двух цифр. Например, 1 будет выведено как 01;
               X - указывает на то, что число должно быть представлено в шестнадцатеричном формате
                   с использованием заглавных букв (A, B, C и т.д.).
            */
        }

        // Возвращаем массив.
        return hexArray;
    }

    public static String hexToBinary(String[] hex) {
        StringBuilder binaryString = new StringBuilder();
        for (String hexValue : hex) {
            if (hexValue.startsWith("0x")) {
                binaryString.append(String.format("%8s", Integer.toBinaryString(Integer.parseInt(hexValue.substring(2), 16))).replace(" ", "0"));
            }
        }
        return binaryString.toString();
    }

    public static String bytesToBinary(byte[] bytes) {
        // Строка для хранения двоичного представления.
        StringBuilder binary = new StringBuilder(bytes.length * 8);

        for (byte b : bytes) {
            // Преобразуем байт в двоичное представление.
            binary.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        // Возвращаем результат.
        return binary.toString();
    }
}
