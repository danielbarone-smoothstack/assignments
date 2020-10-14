import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    
    GameOfChips game;
    int numPlayers = 0;

    /* Prompt user for desired number of players */
    Scanner in = new Scanner(System.in);
    System.out.print("Enter an even number of players: ");
    String s = in.nextLine();

    /* Initialize the game with given number of players */
    try {
      numPlayers = Integer.parseInt(s);
      /* init with entered num of players */
      if (numPlayers % 2 == 0) {
        game = new GameOfChips(numPlayers);
      /* Invalid input (odd number of players). Defaulting to 2. */
      } else {
        System.out.println("Invalid input. Number of players defaulting to 2.");
        game = new GameOfChips(2);
      }
    /* Invalid input (not an integer), default to 2. */
    } catch (Exception e) {
      System.out.println("Invalid input. Number of players defaulting to 2.");
      game = new GameOfChips(2);  // init game with 2 players
    }

    /* Add players to the game */
    for (int i = 1; i <= numPlayers; i++) {
      do {
        System.out.print("Enter the name of player " + i + ": ");
        String name = in.nextLine();
        if (game.addPlayer(name)) {
          break;
        }
        String invalidPlayer = String.format("Cannot have two players named %s. ", name);
        System.out.print(invalidPlayer);
      } while (true);
    }

    /* Set initial number of chips */
    do {
      System.out.print("Enter the initial number of chips the pile contains: ");
      s = in.nextLine();
      try {
        int numChips = Integer.parseInt(s);
        if (numChips%2 != 0 && numChips > numPlayers) {
          game.setNumChips(numChips);
          break;
        }
        String output = String.format("You must start with an odd number of chips greater than or equal to %d. ", numPlayers + 1);
        System.out.print(output);
      } catch (Exception e) {
        System.out.print("Invalid input (not an integer). ");
      }
    } while (true);
    
    while (game.chipsRemaining > 0) {
      game.printTurnInfo();
      s = in.nextLine(); 
    }

  }
}