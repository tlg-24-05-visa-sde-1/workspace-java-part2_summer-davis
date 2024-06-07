package com.duckrace;

class BoardTest {

  public static void main(String[] args){
    Board board = new Board();
    // board.dumpStudentIdMap();

    // run a few races, each time we update() the board
    board.update(2, Reward.DEBIT_CARD);
    board.update(6, Reward.PRIZES);
    board.update(2, Reward.PRIZES);
    board.update(1, Reward.PRIZES);
    board.update(2, Reward.DEBIT_CARD);

    board.show();
  }
}
