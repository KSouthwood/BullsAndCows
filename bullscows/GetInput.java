package bullscows;

import java.util.Scanner;

public class GetInput {
    private final static Scanner scanner = new Scanner(System.in);
    private String input;

    public String getGuess() {
        boolean goodInput = false;

        do {
            input = scanner.nextLine();
            if (input.matches("[0-9a-z]+")) {
                goodInput = true;
            } else {
                System.out.println("Invalid character in guess.");
            }
        } while (!goodInput);

        return input;
    }

    public int getCodeLength() {
        System.out.println("Please enter the secret code's length:");

        do {
            input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Error: Please enter a number between 1 and 36.");
            }
        } while (!input.matches("\\d+"));

        return Integer.parseInt(input);
    }

    public int getNumberOfSymbols() {
        System.out.println("Please enter the number of possible symbols in the code:");

        do {
            input = scanner.nextLine();
            if (!input.matches("[1-9]|[1-2][0-9]|3[0-6]")) {
                System.out.println("Error: Please enter a number between 1 and 36.");
            }
        } while (!input.matches("[1-9]|[1-2][0-9]|3[0-6]"));

        return Integer.parseInt(input);
    }

    public boolean playAgain() {
        System.out.println("Do you wish to play again?");
        int choice = 0;

        do {
            input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "y":
                case "yes":
                    choice = 1;
                    break;
                case "n":
                case "no":
                    choice = 2;
                    break;
                default:
                    System.out.println("Please answer yes or no.");
                    break;
            }
        } while (choice == 0);

        return choice == 1;
    }
}
