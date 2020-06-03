// data type that models an n by n board with sliding tiles
// The 8 Puzzle is a common puzzle you can buy at a dollar store and you solve it by moving the numbered tiles
// so that they end up in row major order
// Written by Eric Goto, assignment from Princeton Coursera Algorithms course

import edu.princeton.cs.algs4.Stack;

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
                boardString.append(board[k][j] + " ");
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
                // if the value is 0, skip it as it doesn't count towards the hamming distance
                if (board[k][j] == 0) {
                    continue;
                }
                if (k * size + j != board[k][j] - 1) hamming++;
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
                    continue;
                    // colDistance = size - 1 - k;
                    // rowDistance = size - 1 - j;
                    // manhattan += colDistance + rowDistance;
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
        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++) {
                if (k == size - 1 && j == size - 1) return board[k][j] == 0;
                if (board[k][j] - 1 != k * size + j) return false;
            }
        return true;
    }

    // checks if two boards are equal
    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null) return false;

        if (y.getClass() != this.getClass()) return false;

        Board test = (Board) y;

        // if the boards aren't the same size, they aren't equal
        if (test.board.length != size) return false;

        // check every index
        for (int k = 0; k < size; k++)
            for (int j = 0; j < size; j++) {
                if (board[k][j] != test.board[k][j]) return false;
            }

        return true;
    }

    // returns the neighboring boards
    public Iterable<Board> neighbors() {

        Stack<Board> s = new Stack<Board>();


        // find the index of the 0
        int row = -1, col = 0; // row, col is location of 0
        for (int k = 0; k < size; k++) {
            for (int j = 0; j < size; j++) {
                if (board[k][j] == 0) {
                    row = k;
                    col = j;
                    break;
                }
            }
            if (row == k) break;
        }

        for (int z = row - 1; z <= row + 1; z++)
            for (int x = col - 1; x <= col + 1; x++) {
                if (z == row - 1 && x != col) continue;
                if (z == row && x == col) continue;
                if (z == row + 1 && x != col) continue;
                if (z >= size || z < 0) continue;
                if (x >= size || x < 0) continue;

                Board temp = new Board(board);
                board[row][col] = board[z][x];
                board[z][x] = 0;
                s.push(new Board(board));
                board = temp.board;
                temp = null;
            }
        return s;
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
        System.out.println(b.hamming()); // should print 7
        System.out.println(b.manhattan()); // should be 2+2+2+2+2+0+2+1+1=14
        System.out.println(b.isGoal());

        int[][] test1 = {{1, 2, 3},
                {4, 0, 6},
                {7, 8, 5}};

        Board c = new Board(test1);

        System.out.println(c.hamming()); // should return 1
        //System.out.println(c.toString());
        //System.out.println(c.isGoal());

        //Iterable<Board> a = c.neighbors();
        //for (Board z : a)
        //System.out.println(z.toString());
    }

}
