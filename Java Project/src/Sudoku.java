public class Sudoku {

        private static final int SIZE = 9; // Standard Sudoku size

        // Method to solve the Sudoku puzzle using backtracking
        public boolean solveSudoku(int[][] board) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == 0) { // Find an empty cell
                        for (int num = 1; num <= SIZE; num++) {
                            if (isSafe(board, row, col, num)) {
                                board[row][col] = num; // Place number
                                if (solveSudoku(board)) { // Recurse
                                    return true;
                                }
                                board[row][col] = 0; // Backtrack
                            }
                        }
                        return false; // Trigger backtracking
                    }
                }
            }
            return true; // All cells filled successfully
        }

        // Check if placing num at board[row][col] is valid
        private boolean isSafe(int[][] board, int row, int col, int num) {
            // Check row
            for (int x = 0; x < SIZE; x++) {
                if (board[row][x] == num) {
                    return false;
                }
            }

            // Check column
            for (int x = 0; x < SIZE; x++) {
                if (board[x][col] == num) {
                    return false;
                }
            }

            // Check 3x3 subgrid
            int startRow = row - row % 3;
            int startCol = col - col % 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i + startRow][j + startCol] == num) {
                        return false;
                    }
                }
            }

            return true; // Safe to place the number
        }

        // Method to print the Sudoku board
        public void printBoard(int[][] board) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    System.out.print(board[row][col] + " ");
                }
                System.out.println();
            }
        }

        // Main method for testing
        public static void main(String[] args) {
            Sudoku solver = new Sudoku();

            // Sample Sudoku board (0 represents empty cells)
            int[][] board = {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

            System.out.println("Original Sudoku Board:");
            solver.printBoard(board);

            if (solver.solveSudoku(board)) {
                System.out.println("\nSolved Sudoku Board:");
                solver.printBoard(board);
            } else {
                System.out.println("No solution exists.");
            }
        }


}
