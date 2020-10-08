package bullscows;

public class Controller {

    private static final GetInput input = new GetInput();
    private static final Game game = new Game();

    public void controller() {
        String secretCode = gameStart();
        System.out.println("Okay, let's start a game!");
        gameLoop(secretCode);
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private String gameStart() {
        String secretCode;

        do {
            int length = input.getCodeLength();
            int symbol = input.getNumberOfSymbols();
            secretCode = game.generateSecretCode(length, symbol);
        } while (secretCode == null);

        return secretCode;
    }

    private void gameLoop(String code) {
        boolean codeGuessed = false;
        int turn = 1;

        do {
            System.out.printf("Turn %d:%n", turn);
            String guess = input.getGuess();
            if (guess.length() == code.length()) {
                codeGuessed = game.grade(code, guess);
                turn++;
            } else {
                System.out.printf("Please enter a %d symbol guess.%n", code.length());
                System.exit(1);
            }
        } while (!codeGuessed);
    }
}
