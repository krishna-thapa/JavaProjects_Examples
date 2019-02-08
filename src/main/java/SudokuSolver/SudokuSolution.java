package SudokuSolver;

public class SudokuSolution {
    private final static int[] samplePuzzle = new int [] {
            0,0,2,5,0,0,0,0,3,
            0,4,0,0,6,7,0,0,0,
            1,5,0,0,0,3,0,0,0,
            0,0,8,0,0,0,0,0,4,
            5,6,0,0,0,0,0,1,7,
            4,0,0,0,0,0,8,0,0,
            0,0,0,6,0,0,0,8,1,
            0,0,0,1,8,0,0,2,0,
            2,0,0,0,0,5,7,0,0 };

    public static void main(String[] args) {

        System.out.println("Function which can print out a solved or unsolved Sudoku puzzle as text format");
        printSudoku(samplePuzzle);
        int[] location = {7,8};
        int number = 8;
        boolean output = solutionSudoku(samplePuzzle, location, number);
        System.out.println("false if there is already a conflicting number or else true if not: "+ output);
    }

    private static boolean solutionSudoku(int[] samplePuzzle, int[] location, int number) {
        boolean result = true;
        int samplePuzzle2d[][] = new int[9][9];

        //convert 1D to 2D matrix
        int count=0;
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(count == samplePuzzle.length) break;
                samplePuzzle2d[i][j] = samplePuzzle[count];
                count++;
            }
        }

        // check if the number is in the row
        for (int i = 0; i < 9; i++) {
            if (samplePuzzle2d[location[0]][i] == number){
                System.out.println("Same number conflict in row");
                result = false;
            }
        }

        // check if the number is in the column
        for (int i = 0; i < 9; i++) {
            if (samplePuzzle2d[i][location[1]] == number){
                System.out.println("Same number conflict in column");
                result = false;
            }
        }
        //check if the number is in the 3x3 box
        int startX = (location[0]/3)*3;
        int startY = (location[1]/3)*3;

        for(int i = startX; i < startX+3; i++){
            for(int j = startY; j < startY+3; j++){
                if (samplePuzzle2d[i][j] == number){
                    System.out.println("Same number conflict in 3x3 box");
                    result = false;
                }
            }
        }
        //System.out.println();
        return result;
    }

    /**
     * function which can print out a solved or unsolved Sudoku puzzle as text format
     * @param samplePuzzle
     */
    private static void printSudoku(int[] samplePuzzle){
        int index = 0;
        for(int i = 0; i < 9; i++){
            if((i % 3 == 0) && (i!=0)){
                System.out.println("---+---+---");
            }
            for(int j = 0; j < 9; j++){
                if ((j % 3 == 0) && (j!=0)){
                    System.out.print("|");
                }
                if(samplePuzzle[index] == 0){
                    System.out.print(" ");
                }else{
                    System.out.print(samplePuzzle[index]);
                }
                index++;
            }
            System.out.println();
        }
    }
}
