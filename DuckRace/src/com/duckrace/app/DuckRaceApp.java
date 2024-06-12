package com.duckrace.app;

import com.duckrace.Board;
import com.duckrace.Reward;

import java.util.Scanner;
import static com.duckrace.Reward.*;

/*
 * Application controller.  Directs overall flow of the application,
 * and prompts user for inputs, then "passes" those inputs into the System.
 */
public class DuckRaceApp {
  private final Board board = Board.getInstance();
  private final Scanner scanner = new Scanner(System.in);

  public void execute() {
    welcome();
    showBoard();
    int id = promptForId();
    Reward reward = promptForReward();
    updateBoard(id, reward);
    showBoard();
  }

  private void updateBoard(int id, Reward reward) {
    board.update(id, reward);
  }

  private Reward promptForReward() {
    Reward reward = null;

    boolean validInput = false;
    while (!validInput) {
      System.out.print("Please enter [D]ebit card or [P]rizes: ");
      String input = scanner.nextLine().trim().toUpperCase();
      if (input.matches("D|P")) { // warning: don't say "D | P" for readability
        validInput = true;
        reward = (input.equals("D")) ? DEBIT_CARD : PRIZES;  // ternary
/*        if (input.equals("D")) {
          reward = Reward.DEBIT_CARD;
        }
        else {
          reward = Reward.PRIZES;
        }*/
      }
    }
    return reward;
  }

  private int promptForId() {
    int id = 0;

    boolean validInput = false;
    while (!validInput) {
      System.out.print("Please enter id of the winner [1-16]: "); // don't hardcode the 16
      String input = scanner.nextLine().trim();  // BLOCKS for input, i.e., the Enter key
      if (input.matches("\\d{1,2}")) {    // any digit, one or two times
        id = Integer.parseInt(input);     // this is safe to do at this point (no exception)
        if (id >= 1 && id <= 16) {        // don't hardcode the 16 (write a method in board called "max id" to access studentMap size)
          validInput = true;
        }
      }
    }
    return id;
  }

  private void showBoard() {
    board.show();
  }

  private void welcome() {
    System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    System.out.println("W  E  L  C  O  M  E  *  T  O  *  T  H  E  *  D  U  C  K  *  R  A  C  E");
    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
  }
}
