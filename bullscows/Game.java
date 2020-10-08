package bullscows;

import java.util.Random;

public class Game {
    private static int codeLength;
    private static Random rnd;

    Game() {
        rnd = new Random(System.nanoTime());
    }

    public String generateSecretCode(int length) {
        if (length < 1 || length > 10) {
            System.out.println("Error: length of code must be between 1 and 10.");
            return null;
        }

        codeLength = length;
        StringBuilder secretCode = new StringBuilder(length);
        boolean[] digitUsed = new boolean[10];

        for (int i = 0; i < length;) {
            int digit = rnd.nextInt(10);
            if (i == 0 && digit == 0) {
                continue;
            }

            if (!digitUsed[digit]) {
                secretCode.append(digit);
                digitUsed[digit] = true;
                i++;
            }
        }

        return String.valueOf(secretCode);
    }

    public boolean grade(String code, String guess) {
        int bulls = 0;
        int cows  = 0;

        for (int index = 0; index < code.length(); index++) {
            if (code.charAt(index) == guess.charAt(index)) {
                bulls++;
            } else if (code.contains(guess.subSequence(index, index + 1))) {
                cows++;
            }
        }

        System.out.println(gradeString(bulls, cows));

        return bulls == codeLength;
    }

    private String gradeString(int bulls, int cows) {
        StringBuilder grade = new StringBuilder("Grade: ");

        if (bulls == 0 && cows == 0) {
            grade.append("None");
        } else {
            switch (bulls) {
                case 0:
                    break;
                case 1:
                    grade.append(String.format("%d bull", bulls));
                    break;
                default:
                    grade.append(String.format("%d bulls", bulls));
                    break;
            }

            if (bulls != 0 && cows != 0) {
                grade.append(" and ");
            }

            switch (cows) {
                case 0:
                    break;
                case 1:
                    grade.append(String.format("%d cow", cows));
                    break;
                default:
                    grade.append(String.format("%d cows", cows));
                    break;
            }
        }

        return grade.toString();
    }
}
