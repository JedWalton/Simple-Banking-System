package banking;

import java.util.Random;

public class Util {
    private Util() {
    }

    public static int generatePin() {
        Random random = new Random();
        return 1000 + random.nextInt(9000 - 1);
    }

    public static long generateCardNum() {
        Random random = new Random();
        long numberGen = (100000000L + random.nextLong(900000000L));
        String bin = "400000";
        String cardNumberGenString = String.valueOf(numberGen);
        String checksum = "1";
        return Long.parseLong(bin.concat(cardNumberGenString).concat(checksum));
    }

    public static boolean passesLuhnsAlgorithm(long cardNumberGen) {
        String ccNumber = String.valueOf(cardNumberGen);
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            long n = Long.parseLong(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
