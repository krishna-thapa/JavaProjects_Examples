package ArrayMatrixMaze;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ArrayMatrixSolver {

    public static void main(String[] args) {
        String fileName;
        String line;
        String[] eachLine;
        List<String[]> rowListLines = new ArrayList<>();

        //https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files only", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
            fileName = chooser.getSelectedFile().getPath();
            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                while ((line = bufferedReader.readLine()) != null){
                    eachLine = line.trim().split("\\s+");
                    rowListLines.add(eachLine);
                }
                bufferedReader.close();

            }catch(FileNotFoundException e){
                System.out.println("File not found: "+ e);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        int startPointX = Integer.parseInt(rowListLines.get(1)[1]);
        int startPointY = Integer.parseInt(rowListLines.get(1)[0]);
        int rowCount = Integer.parseInt(rowListLines.get(0)[1]);
        int columnCount = Integer.parseInt(rowListLines.get(0)[0]);

        System.out.println("No of rows: " + rowCount);
        System.out.println("No of columns: " + columnCount);
        System.out.println();


        String[][] getGrid = getGridData(rowListLines);


        System.out.println("Maze output format before the solution is found.");
        showMatrixGrid(getGrid);
        String[][] solveMatrix;
        solveMatrix = MazeSolution.solveMatrix(getGrid,  startPointX,  startPointY);

        if(MazeSolution.found){
            System.out.println("Maze output format once the solution is found.");
            solveMatrix[startPointX][startPointY] = "S";
            showMatrixGrid(solveMatrix);
            System.out.println("Outcome: There is valid path available.");
        }else{
            System.out.println("No valid path found in input maze!");
        }
    }


    /**
     * Takes the input matrix and convert in Maze input/output formats before finding a solution
     * @param rowListLines
     * @return grid matrix in 2D array
     */
    private static String[][] getGridData(List<String[]> rowListLines){

            String[][] matrix;

            int rowCount = Integer.parseInt(rowListLines.get(0)[1]);
            int columnCount = Integer.parseInt(rowListLines.get(0)[0]);

            matrix = new String[rowCount][columnCount];

            int startPointX = Integer.parseInt(rowListLines.get(1)[1]);
            int startPointY = Integer.parseInt(rowListLines.get(1)[0]);
            int endPointX = Integer.parseInt(rowListLines.get(2)[1]);
            int endPointY = Integer.parseInt(rowListLines.get(2)[0]);

            int rowInitial = 3;
            for(int row=0; row<rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    if (rowListLines.get(rowInitial)[col].equalsIgnoreCase("1")) {
                        matrix[row][col] = "#";
                    }else {
                        matrix[row][col] = " ";
                    }
                }
                rowInitial++;
            }

            if(matrix[startPointX][startPointY].equals(" ")){
                matrix[startPointX][startPointY] = "S";
            }else{
                System.out.println("There is no Start Point!!");
            }if(matrix[endPointX][endPointY].equals(" ")){
                matrix[endPointX][endPointY] = "E";
            }else {
                System.out.println("There is no End Point!!");
            }

        return matrix;
    }

    /**
     * Print the Matrix in grid format in console
     * @param inputGrid
     */
    private static void showMatrixGrid(String[][] inputGrid){
        final int rows = inputGrid.length;
        final int columns = inputGrid[0].length;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                System.out.print(inputGrid[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
