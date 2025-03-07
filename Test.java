import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Test {
    private static final int CUBE_SIZE = 8;
    private static final int ROTATION_STEPS = 3;

    public static void main(String[] args) {
        String inputText = getInputTextFromConsole();
        String[][][] cube = initializeCube(inputText);
        int[] rotationAngles = generateRotationAngles();

        System.out.println("Исходный куб:");
        printCube(cube);

        String[][][] encryptedCube = encryptCube(cube, rotationAngles);

        System.out.println("Зашифрованный куб:");
        printCube(encryptedCube);
    }

    private static String getInputTextFromConsole() {
        System.out.print("Введите текст для шифрования: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Ошибка при вводе текста: " + e.getMessage());
            return "";
        }
    }

    private static String[][][] initializeCube(String inputText) {
        String[][][] cube = new String[CUBE_SIZE][CUBE_SIZE][CUBE_SIZE];
        byte[] bytes = inputText.getBytes(StandardCharsets.ISO_8859_1);
        int byteIndex = 0;
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                for (int k = 0; k < CUBE_SIZE; k++) {
                    if (byteIndex < bytes.length) {
                        String binaryString = Integer.toBinaryString(bytes[byteIndex++] & 0xFF);
                        cube[i][j] = splitString(String.format("%8s", binaryString).replace(' ', '0'));
                    } else {
                        cube[i][j] = splitString("00000000");
                    }
                }
            }
        }
        return cube;
    }



    private static int[] generateRotationAngles() {
        int[] rotationAngles = new int[CUBE_SIZE * CUBE_SIZE * CUBE_SIZE];
        Random random = new Random();
        for (int i = 0; i < rotationAngles.length; i++) {
            rotationAngles[i] = random.nextInt(ROTATION_STEPS) * 120;
        }
        return rotationAngles;
    }

    private static String[][][] encryptCube(String[][][] cube, int[] rotationAngles) {
        String[][][] encryptedCube = new String[CUBE_SIZE][CUBE_SIZE][CUBE_SIZE];
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                for (int k = 0; k < CUBE_SIZE; k++) {
                    int rotationAngle = rotationAngles[i * CUBE_SIZE * CUBE_SIZE + j * CUBE_SIZE + k];
                    encryptedCube[i][j][k] = cube[i][j][k];
                    if ((i + j + k) % 2 == 0) {
                        rotateEdgesAndTriangles(encryptedCube, i, j, k, rotationAngle);
                    } else {
                        // Углы куба остаются на месте
                        processCorner(encryptedCube, i, j, k);
                    }
                }
            }
        }
        return encryptedCube;
    }

    private static void processCorner(String[][][] cube, int i, int j, int k) {
        // Пункт 1: Значение в углу остается неизменным
        String temp = cube[i][j][k];

        // Пункт 2: Ребра, прилегающие к углу, поворачиваются на 120 градусов
        // Только для клеток с четными координатами
        if ((i % 2 == 0) && (j % 2 == 0)) {
            cube[i][j][0] = cube[i][0][k];
            cube[i][0][k] = cube[0][j][k];
            cube[0][j][k] = temp;
        } else {
            cube[i][j][0] = temp;
        }

        if ((i % 2 == 0) && (k % 2 == 0)) {
            cube[i][0][0] = cube[0][j][0];
            cube[0][j][0] = cube[0][0][k];
            cube[0][0][k] = temp;
        } else {
            cube[i][0][0] = temp;
        }


        // Пункт 3: Грани, прилегающие к углу, поворачиваются на 120 градусов
        // Только для клеток с четными координатами
        if ((j % 2 == 0) && (k % 2 == 0)) {
            cube[i][0][0] = cube[0][j][0];
            cube[0][j][0] = cube[0][0][k];
            cube[0][0][k] = temp;
        } else {
            cube[i][0][0] = temp;
        }
    }


    private static void rotateEdgesAndTriangles(String[][][] cube, int i, int j, int k, int rotationAngle) {
        String temp = cube[i][j][0];
        cube[i][j][0] = cube[i][0][k];
        cube[i][0][k] = cube[0][j][k];
        cube[0][j][k] = temp;

        temp = cube[i][0][0];
        cube[i][0][0] = cube[0][j][0];
        cube[0][j][0] = cube[0][0][k];
        cube[0][0][k] = temp;
    }

    private static void printCube(String[][][] cube) {
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                for (int k = 0; k < CUBE_SIZE; k++) {
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String[] splitString(String str) {
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

//    public static char getCharacterAtIndex(String str, int index) {
//        // Проверка, что индекс находится в пределах строки
//        if (index < 0 || index >= str.length()) {
//            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + (str.length() - 1));
//        }
//
//        return str.charAt(index); // Возвращаем символ под указанным индексом
//    }

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

    public static String hexToBinary(String hex) {
        // Удаляем префикс "0x", если он есть.
        if (hex.startsWith("0x")) {
            hex = hex.substring(2);
        }

        // Преобразуем шестнадцатеричное значение в десятичное.
        int decimalValue = Integer.parseInt(hex, 16);
        // Преобразуем целое число в двоичное представление.
        return Integer.toBinaryString(decimalValue);
    }

    private static String bytesToBinary(byte[] bytes) {
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