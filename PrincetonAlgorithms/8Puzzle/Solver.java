import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

// This solver will employ the A* algorithm to solve the 8 puzzle
public class Solver {


    private SearchNode goalNode;
    private Board initialBoard;
    private int moves;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int numberOfMoves;
        private SearchNode previous;
        private int priority;
        private int manhattan;

        public SearchNode(Board board, int numberOfMoves, SearchNode previous) {
            this.board = board;
            this.numberOfMoves = numberOfMoves;
            this.previous = previous;
            this.manhattan = board.manhattan();
            this.priority = manhattan + numberOfMoves;
        }

        @Override
        public int compareTo(SearchNode x) {
            if (x == null) throw new IllegalArgumentException("Argument is null");

            if (this.priority > x.priority) return 1;
            else if (this.priority < x.priority) return -1;
            else {
                if (this.manhattan > x.manhattan) return 1;
                else return 0;
            }
        }
    }

    // find a solution to the board provided in the argument using the A* algorithm
    // Search node: a board, number of moves and its previous node
    // The puzzle will be solved by inserting a search node-> deleting the search node with the minimum priority
    // and then inserting all neighbouring search nodes. This process will be repeated until the
    // deleted node is the goal board.
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Argument is null");

        MinPQ<SearchNode> pq = new MinPQ<>();
        Iterable<Board> neighbors = null;
        initialBoard = initial;
        moves = 0;
        // insert initial search node
        pq.insert(new SearchNode(initial, moves, null));
        SearchNode deleted = pq.delMin();

        while (true) {
            // If the board corresponding to the searchNode we deleted is solved then break out of loop
            if (deleted.board.isGoal()) {
                goalNode = deleted;
                break;
            }

            neighbors = deleted.board.neighbors();

            for (Board b : neighbors) {
                if (!b.equals(deleted.board)) {
                    pq.insert(new SearchNode(b, deleted.numberOfMoves + 1, deleted));
                }
            }

            moves = deleted.numberOfMoves + 1;
            deleted = pq.delMin();
        }
    }

    // returns true if the board is solvable
    public boolean isSolvable() {
        Board twin = initialBoard.twin();
        return false;
    }

    // returns the minimum number of boards to solve the board
    public int moves() {
        return moves;
    }

    // returns the sequence of moves if board is solvable, null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> sequence = new Stack<>();
        int step = goalNode.numberOfMoves;

        while (goalNode != null) {

            if (goalNode.numberOfMoves == step) {
                sequence.push(goalNode.board);
                step--;
                goalNode = goalNode.previous;
                continue;
            }
            goalNode = goalNode.previous;
        }

        return sequence;
    }

    // testing
    public static void main(String[] args) {
        int[][] test = {{1, 2, 3},
                {0, 7, 6},
                {5, 4, 8}};

        Board b = new Board(test);
        Solver s = new Solver(b);
        for (Board a : s.solution()) {
            System.out.println(a);
            //System.out.println("priority " + a.numberOfMoves);
        }
    }
}
