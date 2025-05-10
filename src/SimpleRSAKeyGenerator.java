import java.math.BigInteger;
import java.security.SecureRandom;

public class SimpleRSAKeyGenerator {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 512; // Длина ключа в битах
    private SecureRandom random;

    public SimpleRSAKeyGenerator() {
        random = new SecureRandom();
        generateKeys();
    }

    private void generateKeys() {
        // Генерация двух простых чисел p и q
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);

        n = p.multiply(q); // n = p * q
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // φ(n)

        // Выбор e
        e = BigInteger.valueOf(65537); // Часто используемое значение для e

        // Вычисление d
        d = e.modInverse(phi); // d = e^(-1) mod φ(n)
    }

    public BigInteger getPublicKey() {
        return e; // Открытый ключ
    }

    public BigInteger getPrivateKey() {
        return d; // Закрытый ключ
    }

    public BigInteger getN() {
        return n; // Модуль
    }

    public static void main(String[] args) {
        SimpleRSAKeyGenerator rsa = new SimpleRSAKeyGenerator();
        System.out.println("Public Key (e): " + rsa.getPublicKey());
        System.out.println("Private Key (d): " + rsa.getPrivateKey());
        System.out.println("Modulus (n): " + rsa.getN());
    }
}