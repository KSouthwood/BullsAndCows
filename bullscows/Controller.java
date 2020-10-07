package bullscows;

import java.util.Scanner;

public class Controller {
    private final static Scanner scanner = new Scanner(System.in);

    public void controller() {
        while (true) {
            String guess = getInput();
            grade("1357", guess);
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

    private String getInput() {
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
}
