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

    }

    // returns the board dimensions
    public int dimension() {

    }

    // returns the number of tiles out of place
    public int hamming() {

    }

    // returns the sum of Manhattan distances between tiles and goal
    public int manhattan() {

    }

    // checks if board is solved
    public boolean isGoal() {

    }

    // checks if two boards are equal
    public boolean equals(Object y) {

    }

    // returns the neighboring boards
    public Iterable<Board> neighbors() {

    }

    // returns a board that is created by exchanging any pair of tiles
    public Board twin() {

    }

    // for testing
    public static void main(String[] args) {

    }

}
