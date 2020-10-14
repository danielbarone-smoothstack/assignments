/*
 * Assignment 1.1
 *  Sets up the constraints for and validates
 *  user guesses.
 */
public class GuessValidator {
  private int numToGuess; // number user is looking for
  private int min;  // max of range
  private int max;  // min of range
  public int guessesRemaining;  // num guesses remaining

  /* Init guess parameters */
  public GuessValidator(int min, int max, int maxGuesses) {
    this.min = min;
    this.max = max;
    guessesRemaining = maxGuesses;
    numToGuess = (int)(Math.random() * ((max - min) + 1)) + min;
  }

  /* 
   * Verify guess in allowed range. 
   * Doesn't decrement guessesRemaining.
   */
  public boolean guessInRange(int guess) {
    if (guess >= min && guess <= max) {
      return true;
    }
    invalidGuess();
    return false;
  }

  /* Guess is valid, see if it meets criteria for "correct guess" */
  public boolean handleGuess(int guess) {
    guessesRemaining -= 1;
    if (Math.abs(guess - numToGuess) <= 10) {
      System.out.print("\nYou win! The number to guess was " + numToGuess);
      return true;
    } else {
      System.out.println("Incorrect guess, please try again. Remaining guesses: " + guessesRemaining);
      return false;
    }
  }

  /* Prompt user for a guess */
  public void promptForGuess() {
    String output = String.format("\nPlease enter a number between %d-%d: ", min, max);
    System.out.print(output);
  }

  /* Print invalid guess */
  public void invalidGuess() {
    String output = String.format("Invalid guess. Must be an integer in the range %d-%d.", min, max);
    System.out.println(output);
  }
}