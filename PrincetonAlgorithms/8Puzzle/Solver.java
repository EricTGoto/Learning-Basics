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

        initialBoard = initial;
        isSolvable();
    }

    // returns true if the board is solvable
    // Also does all the solving since when you check if a board is solvable you are also solving it
    public boolean isSolvable() {
        Board twin = initialBoard.twin();

        MinPQ<SearchNode> twinPQ = new MinPQ<>();
        MinPQ<SearchNode> initialPQ = new MinPQ<>();

        Iterable<Board> twinNeighbors;
        Iterable<Board> initialNeighbors;

        twinPQ.insert(new SearchNode(twin, 0, null));
        initialPQ.insert(new SearchNode(initialBoard, 0, null));

        SearchNode deletedTwin = twinPQ.delMin();
        SearchNode deletedInitial = initialPQ.delMin();

        moves = 0;

        while (true) {

            if (deletedTwin.board.isGoal()) {
                goalNode = null;
                moves = -1;
                moves = -1;
                return false;
            } else if (deletedInitial.board.isGoal()) {
                twinPQ = null;
                initialPQ = null;
                goalNode = deletedInitial;
                moves = deletedInitial.numberOfMoves;
                return true;
            }

            twinNeighbors = deletedTwin.board.neighbors();
            initialNeighbors = deletedInitial.board.neighbors();

            for (Board b : twinNeighbors) {
                if (deletedTwin.numberOfMoves == 0 || !b.equals(deletedTwin.previous.board)) {
                    twinPQ.insert(new SearchNode(b, deletedTwin.numberOfMoves + 1, deletedTwin));
                }
            }

            for (Board b : initialNeighbors) {
                if (deletedInitial.numberOfMoves == 0 || !b.equals(deletedInitial.previous.board)) {
                    initialPQ.insert(new SearchNode(b, deletedInitial.numberOfMoves + 1, deletedInitial));
                }
            }

            deletedTwin = twinPQ.delMin();
            deletedInitial = initialPQ.delMin();
        }
    }


    // returns the minimum number of boards to solve the board
    public int moves() {
        return moves;
    }

    // returns the sequence of moves if board is solvable, null if unsolvable
    public Iterable<Board> solution() {

        if (goalNode == null) return null;
        SearchNode goalCopy = new SearchNode(goalNode.board, goalNode.numberOfMoves, goalNode.previous);

        Stack<Board> sequence = new Stack<>();
        int step = goalCopy.numberOfMoves;

        while (goalCopy != null) {

            if (goalCopy.numberOfMoves == step) {
                sequence.push(goalCopy.board);
                step--;
                goalCopy = goalCopy.previous;
                continue;
            }
            goalCopy = goalCopy.previous;
        }

        return sequence;
    }

    // testing
    public static void main(String[] args) {
        int[][] test = {{1, 2, 3},
                {0, 7, 6},
                {5, 4, 8}};

        int[][] test3 = {{1, 2, 3},
                {4, 6, 5},
                {7, 8, 0}};

        int[][] test2 = {{1, 0}, {2, 3}};

        Board b = new Board(test);
        Solver s = new Solver(b);
        System.out.println(s.isSolvable());
        for (Board a : s.solution()) {
            System.out.println(a);
            //System.out.println("priority " + a.numberOfMoves);
        }
    }
}
