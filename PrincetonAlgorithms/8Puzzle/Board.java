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

    // returns the number of tiles out of place, called the hamming distance
    // Example: a 3x3 board is solved, that is no tiles out of place if it looks like the following:
    // 1 2 3
    // 4 5 6
    // 7 8 0
    // where 0 represents an empty square
    // The following board:
    // 4 5 1
    // 2 3 6
    // 7 8 0
    // has a hamming distance of 5
    public int hamming() {
        int hamming = 0;

        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++) {
                // checks if the last square is 0
                if (k == size - 1 && j == size - 1 && board[k][j] == 0) {
                    hamming++;
                    break;
                }
                if (k + j != board[k][j] - 1) hamming++;
            }

        return hamming;
    }

    // returns the sum of Manhattan distances between tiles and goal
    // A manhattan distance is the distance a tile is the sum of the vertical and horizontal distances
    // it is from its goal position. For example:
    // 4 5 1
    // 2 3 6
    // 7 8 0
    // has a manhattan distance of: 1+1+2+2+2=8
    public int manhattan() {
        int manhattan = 0;
        int colDistance;
        int rowDistance;
        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++) {
                int tileValue = board[k][j];
                if (tileValue == 0) {
                    colDistance = size - 1 - k;
                    rowDistance = size - 1 - j;
                    manhattan += colDistance + rowDistance;
                } else {
                    colDistance = Math.abs(j - (tileValue - 1) % size);
                    rowDistance = Math.abs(k - (tileValue - 1) / size);
                    manhattan += colDistance + rowDistance;
                }
            }
        return manhattan;
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
                {0, 7, 8}};

        Board b = new Board(test);
        System.out.println(b.toString());
        System.out.println(b.hamming());
        System.out.print(b.manhattan()); // should be 2+2+2+2+2+0+2+1+1=14
    }

}
