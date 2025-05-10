public class CubeED extends CubeIO {
    public static String encryption(String message, String key){
        StringBuilder result = new StringBuilder();
        StringBuilder keyToBin = new StringBuilder();
        String[] keyToWinArray = getHexRepresentation(key);
        int blockCount = 0; //изначально 0

        for (String keyToWin : keyToWinArray){
            String winToBin = hexToBinary(keyToWin);
            keyToBin.append(addZeros(new StringBuilder(winToBin), 512));
        }

        String[] roundKeys = keyOfRound(new StringBuilder(keyToBin), blockCount);
        return result.toString();
    }

    public static String[] keyOfRound(StringBuilder keyToBin, int roundCount){
        //на вход подается ключ keyToBin длиной 512 бит
        //и количество блоков сообщения как количество раундов шифрования
        String[] roundKeys = new String[roundCount];
        for (int round = 0; round < roundCount; round++){
            
        }

        return roundKeys;
    }

    // Метод для добавления нулей до заданной длины.
    public static String addZeros(StringBuilder sb, int count) {
        int length = sb.length(); // Получаем текущую длину строки.
        sb.reverse(); // Реверсируем строку, чтобы добавлять нули в начале.
        while (length < count) {
            sb.append('0'); // Добавляем нули до достижения нужной длины.
            length++; // Увеличиваем длину.
        }
        return String.valueOf(sb.reverse()); // Возвращаем строку обратно в исходном порядке.
    }

    // Метод для преобразования шестнадцатеричной строки в двоичную.
    public static String hexToBinary(String hex) {
        if (hex.startsWith("0x")) { // Удаляем префикс "0x", если он есть.
            hex = hex.substring(2);
        }
        int decimalValue = Integer.parseInt(hex, 16); // Преобразуем шестнадцатеричное значение в десятичное.
        return Integer.toBinaryString(decimalValue); // Преобразуем целое число в двоичное представление.
    }
}
