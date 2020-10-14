import java.util.*;

/* Auxilary class for the game's players */
class Player {
  public String name;
  public int numChips;

  public Player(String name) {
    this.name = name;
    numChips = 0;
  }

  public void printPlayer() {
    String output = String.format("%s has %d chips.", name, numChips);
    System.out.println(output);
  }

  public void addChips(int numChips) {
    this.numChips += numChips;
  }
}

/* 
 * GameOfChips Class
 */
public class GameOfChips {
  /* 
   * Keeps track of players in a
   * LinkedHashMap to effeciently check
   * if duplicate name exists when adding player.
   */
  private Map<String, Player> players;
  public int chipsRemaining; // chips left

  /* Initialize the LinkedHashMap */
  public GameOfChips(int numPlayers) {
    players = new LinkedHashMap<String, Player>(numPlayers);
  }

  /* Add players to the map if not a duplicate */
  public boolean addPlayer(String name) {
    Player newPlayer = new Player(name);
    if (players.containsKey(name.toLowerCase())) {
      return false;
    }
    players.put(name.toLowerCase(), newPlayer);
    return true;
  }

  /* init game's starting number of chips */
  public void setNumChips(int chipsRemaining) {
    this.chipsRemaining = chipsRemaining;
  }

  /* Code for one round -- iterates through all players once */
  public void play() {
    /* Define iterator to keep track of current player */
    Iterator<Map.Entry<String, Player>> iterator = players.entrySet().iterator();
    Scanner in = new Scanner(System.in); // to read user input
    
    /* while we haven't cycled through all players... */
    while (iterator.hasNext()) {
      /* Print info related to current state of the game */
      System.out.println("\n==========================\n");
      Map.Entry<String, Player> currPlayer = iterator.next();
      for (Map.Entry<String, Player> player : players.entrySet()) {
        Player p = player.getValue();
        p.printPlayer();
      }

      /* 
       * If game over, determine the winner.
       * If num players > 2, winner is person with
       * highest even number of chips
       */
      if (chipsRemaining <= 0) {
        Player winner = new Player("");
        int currHighestScore = 0;
        for (Map.Entry<String, Player> player : players.entrySet()) {
          Player p = player.getValue();
          if (p.numChips > currHighestScore && p.numChips%2 == 0) {
            winner = p;
            currHighestScore = p.numChips;
          }
        }
        String gameOver = String.format("%s wins!\n", winner.name);
        System.out.println(gameOver);
        break;
      }

      /* Print remaining number of chips. */
      String output = String.format("There are %d chips remaining.", chipsRemaining);
      System.out.println(output);
  
      /* Calculate max number of chips the current user can take */
      int maxNumChips;
      if (chipsRemaining % 2 == 0) {
        maxNumChips = chipsRemaining / 2;
      } else {
        maxNumChips = Math.max(((chipsRemaining - 1)/2), 1);
      }
      output = String.format("You may take any number of chips from 1 to %d. ", maxNumChips);
      System.out.print(output);
      
      /* Validate chip number selection then add those chips to the player */
      int chipsSelected = 0;
      do {
        System.out.print("How many will you take, " + currPlayer.getValue().name + "? ");
        String s = in.nextLine();
        try {
          chipsSelected = Integer.parseInt(s);
          if (chipsSelected >= 1 && chipsSelected <= maxNumChips) {
            break;
          }
          output = String.format("Invalid entry. You must enter an integer in range 1 - %d. ", maxNumChips);
          System.out.println(output);
        } catch (Exception e) {
          output = String.format("Invalid entry. You must enter an integer in range 1 - %d. ", maxNumChips);
          System.out.println(output);
        }
      } while (true);
      
      currPlayer.getValue().addChips(chipsSelected);  // add chips to the user
      chipsRemaining -= chipsSelected;  // Decrement remaining number of chips
    }
  }
  
}