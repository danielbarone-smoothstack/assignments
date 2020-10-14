
public class GuessValidator {
  private int numToGuess;
  private int min;
  private int max;
  public int guessesRemaining;

  public GuessValidator(int min, int max, int maxGuesses) {
    this.min = min;
    this.max = max;
    guessesRemaining = maxGuesses;
    numToGuess = (int)(Math.random() * ((max - min) + 1)) + min;
  }

  public boolean guessInRange(int guess) {
    if (guess >= min && guess <= max) {
      return true;
    }
    invalidGuess();
    return false;
  }

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

  public void promptForGuess() {
    String output = String.format("\nPlease enter a number between %d-%d: ", min, max);
    System.out.print(output);
  }

  public void invalidGuess() {
    String output = String.format("Invalid guess. Must be an integer in the range %d-%d.", min, max);
    System.out.println(output);
  }
}