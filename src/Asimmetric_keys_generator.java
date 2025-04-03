import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asimmetric_keys_generator {
    private int P;
    private int Q;
    private int abs_N;
    private int eulerFunc;
    private int open_exp_E;
    private int secret_exp_D;
    public int secret_key;
    public int open_key;

    public Asimmetric_keys_generator() {
        do {
            P = generatePrimeNumber();
            Q = generatePrimeNumber();
        } while (P == Q);

        abs_N = P * Q;
        eulerFunc = getEulerFunction(P, Q);
        open_exp_E = getOpenExpE(eulerFunc);
        secret_exp_D = getSecretExpD(open_exp_E, eulerFunc);
    }

    public int generatePrimeNumber() {
        Random rand = new Random();
        int num;
        do {
            num = rand.nextInt(1000) + 1;
        } while (!isPrime(num));
        return num;
    }

    public boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getEulerFunction(int p, int q) {
        return (p - 1) * (q - 1);
    }

    public int getOpenExpE(int eulerFunc) {
        List<Integer> e_numbers = getMutuallySimpleNumbers(eulerFunc);
        Random rand = new Random();
        return e_numbers.get(rand.nextInt(e_numbers.size()));
    }

    public int getSecretExpD(int e, int eulerFunc) {
        int d = 0;
        for (int i = 1; i < eulerFunc; i++) {
            if ((e * i) % eulerFunc == 1) {
                d = i;
                break;
            }
        }
        return d;
    }

    public List<Integer> getMutuallySimpleNumbers(int e) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 2; i < 1000; i++) {
            if (getNOD(e, i) == 1) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    public int getNOD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int getAbs_N() {
        return abs_N;
    }

    public int getOpen_exp_E() {
        return open_exp_E;
    }

    public int getSecret_exp_D() {
        return secret_exp_D;
    }
}
