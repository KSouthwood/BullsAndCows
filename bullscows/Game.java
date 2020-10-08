package bullscows;

import java.util.Random;

public class Game {
    private static int codeLength;
    private static Random rnd;

    private static final String CODE_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final String ASTERISKS = "************************************";

    Game() {
        rnd = new Random(System.nanoTime());
    }

    public String generateSecretCode(int lengthOfCode, int numOfSymbols) {
        if (lengthOfCode < 1 || lengthOfCode > 36) {
            System.out.println("Error: length of code must be between 1 and 36.");
            System.exit(1);
            return null;
        }

        if (lengthOfCode > numOfSymbols) {
            System.out.println("Error: length of code cannot be more than number of symbols!");
            System.exit(1);
            return null;
        }

        codeLength = lengthOfCode;
        StringBuilder secretCode = new StringBuilder(lengthOfCode);
        boolean[] symbolUsed = new boolean[numOfSymbols];

        for (int i = 0; i < lengthOfCode;) {
            int symbol = rnd.nextInt(numOfSymbols);

            if (!symbolUsed[symbol]) {
                secretCode.append(CODE_SYMBOLS.charAt(symbol));
                symbolUsed[symbol] = true;
                i++;
            }
        }

        System.out.printf("The secret is prepared: %s ", ASTERISKS.substring(0, lengthOfCode));
        if (numOfSymbols == 1) {
            System.out.printf("(%s).%n", CODE_SYMBOLS.charAt(0));
        } else if (numOfSymbols < 11) {
            System.out.printf("(0-%s).%n", CODE_SYMBOLS.charAt(numOfSymbols - 1));
        } else if (numOfSymbols == 11) {
            System.out.printf("(0-9, %s).%n", CODE_SYMBOLS.charAt(numOfSymbols - 1));
        } else {
            System.out.printf("(0-9, a-%s).%n", CODE_SYMBOLS.charAt(numOfSymbols - 1));
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
