package bullscows;

public class Game {
    private int codeLength;

    public String generateSecretCode(int length) {
        if (length < 1 || length > 10) {
            System.out.println("Error: length of code must be between 1 and 10.");
            return null;
        }

        this.codeLength = length;
        StringBuilder secretCode;

        do {
            secretCode = new StringBuilder(codeLength);
            long pseudoRandomNumber = System.nanoTime();
            boolean[] digitUsed = new boolean[10];
            int maxLength = length;

            if (codeLength == 1) {
                digitUsed[0] = true;
            }

            while (pseudoRandomNumber > 0 && maxLength > 0) {
                int digit = (int) (pseudoRandomNumber % 10);
                pseudoRandomNumber /= 10;
                if (!digitUsed[digit]) {
                    digitUsed[digit] = true;
                    secretCode.append(digit);
                    maxLength--;
                }
            }
        } while (secretCode.length() != codeLength);

        if (secretCode.charAt(0) == '0') {
            int swapWith = (Integer.parseInt(secretCode.substring(1, 2)) % (length - 1)) + 1;
            char swapDigit = secretCode.charAt(swapWith);
            secretCode.deleteCharAt(0).deleteCharAt(swapWith);
            secretCode.insert(0, swapDigit).insert(swapWith, '0');
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
