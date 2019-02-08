package ArrayMatrixMaze;

public class MazeSolution {

    static boolean found = false;  //to find out whether there is a solution in maze

    /**
     * Takes the inout format of the matrix
     * @param grid
     * @param startX
     * @param startY
     * @return outcome matrix grid if there is a solution
     */
    static String[][] solveMatrix(String[][] grid, int startX, int startY){

        int x[] = {1, 0, 0, -1};
        int y[] = {0, 1, -1, 0};
        grid[startX][startY] = "X";

            for(int i = 0; i < 4 ; i++){// check if there are 'X' around the S.
            int afterX = x[i] + startX;
            int afterY = y[i] + startY;
                if((afterY < grid[0].length-1) && (afterX < grid.length-1) && grid[afterX][afterY] == "E"){// if yes, return.
                    found = true;
                    return grid;
                }
            }

            for(int i = 0; i < 4 ; i++){// if no, check for 'O'
            int afterX = x[i] + startX;
            int afterY = y[i] + startY;

            if(found)    // path already found in earlier recursive call; no need to search anymore
                return grid;
            else{ // path not found yet, have to continue searching

                if((afterY < grid[0].length-1) && (afterX < grid.length-1) && grid[afterX][afterY] == " "){
                    if(afterY == 0){  //For horizontal wrapping
                        grid[afterX][afterY] = "X";
                        solveMatrix(grid, afterX, (afterY+grid[0].length-1));
                    }else if(afterX == 0) {  //For vertical wrapping
                        grid[afterX][afterY] = "X";
                        solveMatrix(grid, (afterX+grid.length-1), afterY);
                    }else{
                        solveMatrix(grid, afterX, afterY);
                    }
                    if(!found){  //revert X to empty path if path comes to meet dead end
                        grid[afterX][afterY] = " ";
                    }
                }
            }
        }
        return grid;
    }
}
