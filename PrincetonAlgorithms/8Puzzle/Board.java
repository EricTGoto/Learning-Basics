// data type that models an n by n board with sliding tiles
// The 8 Puzzle is a common puzzle you can buy at a dollar store and you solve it by moving the numbered tiles
// so that they end up in row major order
// Written by Eric Goto, assignment from Princeton Coursera Algorithms course

public class Board {

    private int size;
    private int[][] board;

    // initialize a board with n by n tiles
    public Board(int[][] tiles) {
        size = tiles.length;
        board = new int[size][size];

        // create a copy of the input array for immutability
        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++)
                board[k][j] = tiles[k][j];
    }


    // returns a string representation of the board
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append(size + "\n ");

        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++) {
                boardString.append(board[j][k] + " ");
                if (j == size - 1) boardString.append("\n ");
            }
        return boardString.toString();
    }

    // returns the board dimensions
    public int dimension() {
        return size;
    }

    // returns the number of tiles out of place
    public int hamming() {
        return 0;
    }

    // returns the sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // checks if board is solved
    public boolean isGoal() {
        return false;
    }

    // checks if two boards are equal
    public boolean equals(Object y) {
        return false;
    }

    // returns the neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // returns a board that is created by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // for testing
    public static void main(String[] args) {
        int[][] test = {{3, 4, 5},
                {2, 1, 6},
                {0, 9, 8}};

        Board b = new Board(test);
        System.out.print(b.toString());
    }

}
