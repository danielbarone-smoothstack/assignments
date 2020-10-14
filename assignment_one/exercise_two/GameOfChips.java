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

  public void printTurnInfo() {
    for (Map.Entry<String, Player> player : players.entrySet()) {
      Player currPlayer = player.getValue();
      currPlayer.printPlayer();
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
    System.out.print(output + "How many will you take, " + "..." + "? ");
  }
  
}