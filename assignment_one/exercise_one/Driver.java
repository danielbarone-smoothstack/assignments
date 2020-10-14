import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    /* Scanner to grab user input */
    Scanner in = new Scanner(System.in);
    /* Validator to manage user guesses */
    GuessValidator validator = new GuessValidator(1, 100, 5);
    /* init guess to 0 */
    int userGuess = 0;  
    /* false until correct guess found */
    boolean correctGuess = false;

    /* Collect the user's guesses while correct guess not found. */
    do {
      /* Prompt user for their guess. */
      validator.promptForGuess();
      String s = in.nextLine();
      try {
        userGuess = Integer.parseInt(s);
        /* If guess is valid, see if it is correct. */
        if(validator.guessInRange(userGuess)) {
          correctGuess = validator.handleGuess(userGuess);
          /* If 0 guesses remaining, exit. */
          if (validator.guessesRemaining <= 0) {
            System.out.println("\nSorry");
            break;
          }
        }
      /* Invalid guess */
      } catch (Exception e) {
        validator.invalidGuess();
      }
    } while (correctGuess == false);
  }
}