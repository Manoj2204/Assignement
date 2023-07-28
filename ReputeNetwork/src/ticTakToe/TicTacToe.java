package ticTakToe;
public class TicTacToe {

    public char findStateOfTicTacToe(char[][] grid) {
        // Checking rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != ' ') {
                return grid[i][0];
            }
        }

        // Checking columns
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != ' ') {
                return grid[0][i];
            }
        }

        // Checking diagonals
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != ' ') {
            return grid[0][0];
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != ' ') {
            return grid[0][2];
        }

        // No winner found
        return ' ';
    }

    public static void main(String[] args) {
        // Enter capital letters 'X' and 'O' only
        char[][] grid = {
            {'X', 'O', 'X'},
            {'O', 'X', 'X'},
            {'X', 'O', 'X'}
        };

        TicTacToe t = new TicTacToe();
        char winner = t.findStateOfTicTacToe(grid);
        if (winner == ' ') {
            System.out.println("Draw");
        } else {
            System.out.println("Winner is: " + winner);
        }
    }
}
