package bullscows;

import java.util.Scanner;

public class GetInput {
    private final static Scanner scanner = new Scanner(System.in);

    public String getGuess() {
        String input;
        boolean goodInput = false;

        do {
            input = scanner.nextLine();
            if (input.matches("\\d{1,10}")) {
                goodInput = true;
            } else {
                System.out.println("Invalid guess. Must be all digits.");
            }
        } while (!goodInput);

        return input;
    }

    public int getCodeLength() {
        String input;
        System.out.println("Please enter the secret code's length:");

        do {
            input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Please enter a number between 1 and 9.");
            }
        } while (!input.matches("\\d+"));

        return Integer.parseInt(input);
    }

}
