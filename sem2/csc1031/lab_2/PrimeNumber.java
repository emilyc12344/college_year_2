import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(countPrimes(n));
    }

    public static int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] notPrime = new boolean[n + 1];
        notPrime[0] = true;
        notPrime[1] = true;

        // Only check odd numbers after 2
        int count = 1; // Count 2 as prime

        // Special handling for even numbers
        for (int i = 4; i <= n; i += 2) {
            notPrime[i] = true;
        }

        // Check only odd numbers
        for (int i = 3; i * i <= n; i += 2) {
            if (!notPrime[i]) {
                for (int j = i * i; j <= n; j += i * 2) { // Skip even multiples
                    notPrime[j] = true;
                }
            }
        }

        // Count remaining primes
        for (int i = 3; i <= n; i += 2) {
            if (!notPrime[i]) {
                count++;
            }
        }
        return count;
    }
}