package bullscows;

import java.util.Scanner;

public class Controller {
    private final static Scanner scanner = new Scanner(System.in);

    public void controller() {
        while (true) {
            int length = getCodeLength();
            System.out.println("The random secret number is " + generateSecretCode(length));
//            String guess = getGuess();
//            grade("1357", guess);
            return;
        }
    }

    private void grade(String code, String guess) {
        int bulls = 0;
        int cows  = 0;

        for (int index = 0; index < code.length(); index++) {
            if (code.charAt(index) == guess.charAt(index)) {
                bulls++;
            } else if (code.contains(guess.subSequence(index, index + 1))) {
                cows++;
            }
        }

        System.out.print("Grade: ");
        if (bulls != 0 && cows != 0) {
            System.out.printf("%d bull(s) and %d cow(s).", bulls, cows);
        } else if (bulls != 0) {
            System.out.printf("%d bull(s).", bulls);
        } else if (cows != 0) {
            System.out.printf("%d cow(s).", cows);
        } else {
            System.out.print("None.");
        }
        System.out.printf(" The secret code is %s.%n", code);
    }

    private String getGuess() {
        String input;
        boolean goodInput = false;

        do {
            input = scanner.nextLine();
            if (input.matches("\\d\\d\\d\\d")) {
                goodInput = true;
            } else {
                System.out.println("Invalid guess. Must be four digits.");
            }
        } while (!goodInput);

        return input;
    }

    private int getCodeLength() {
        String input;

        do {
            input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Please enter a number between 1 and 9.");
            }
        } while (!input.matches("\\d+"));

        return Integer.parseInt(input);
    }

    private String generateSecretCode(int codeLength) {
        if (codeLength < 1 || codeLength > 10) {
            System.out.println("Error: length of code must be between 1 and 10.");
            return null;
        }

        StringBuilder secretCode;

        do {
            secretCode = new StringBuilder(codeLength);
            long pseudoRandomNumber = System.nanoTime();
            boolean[] digitUsed = new boolean[10];
            int length = codeLength;

            if (codeLength == 1) {
                digitUsed[0] = true;
            }

            while (pseudoRandomNumber > 0 && length > 0) {
                int digit = (int) (pseudoRandomNumber % 10);
                pseudoRandomNumber /= 10;
                if (!digitUsed[digit]) {
                    digitUsed[digit] = true;
                    secretCode.append(digit);
                    length--;
                }
            }
        } while (secretCode.length() != codeLength);

        if (secretCode.charAt(0) == '0') {
            int swapWith = (Integer.parseInt(secretCode.substring(1, 2)) % (codeLength - 1)) + 1;
            char swapDigit = secretCode.charAt(swapWith);
            secretCode.deleteCharAt(0).deleteCharAt(swapWith);
            secretCode.insert(0, swapDigit).insert(swapWith, '0');
        }

        return String.valueOf(secretCode);
    }
}
