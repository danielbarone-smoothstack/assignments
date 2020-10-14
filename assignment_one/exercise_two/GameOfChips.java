import java.util.*;

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

public class GameOfChips {
  private Map<String, Player> players;
  public int chipsRemaining;

  public GameOfChips(int numPlayers) {
    players = new LinkedHashMap<String, Player>(numPlayers);
  }

  public boolean addPlayer(String name) {
    Player newPlayer = new Player(name);
    if (players.containsKey(name.toLowerCase())) {
      return false;
    }
    players.put(name.toLowerCase(), newPlayer);
    return true;
  }

  public void setNumChips(int chipsRemaining) {
    this.chipsRemaining = chipsRemaining;
  }

  public void play() {
    Iterator<Map.Entry<String, Player>> iterator = players.entrySet().iterator();
    Scanner in = new Scanner(System.in);
    while (iterator.hasNext()) {
      System.out.println("\n==========================\n");
      Map.Entry<String, Player> currPlayer = iterator.next();

      for (Map.Entry<String, Player> player : players.entrySet()) {
        Player p = player.getValue();
        p.printPlayer();
      }

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

      String output = String.format("There are %d chips remaining.", chipsRemaining);
      System.out.println(output);
  
      int maxNumChips;
      if (chipsRemaining % 2 == 0) {
        maxNumChips = chipsRemaining / 2;
      } else {
        maxNumChips = Math.max(((chipsRemaining - 1)/2), 1);
      }
      output = String.format("You may take any number of chips from 1 to %d. ", maxNumChips);
      System.out.print(output);
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
      
      currPlayer.getValue().addChips(chipsSelected);
      chipsRemaining -= chipsSelected;

      
    }
  }
  
}