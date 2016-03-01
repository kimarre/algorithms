import java.util.*;
import java.io.*;
import java.lang.Math;

public class GridGame {
   public static void main(String arr[]) throws FileNotFoundException {
      if (arr == null) {
         System.out.println("You forgot to give a test name!");
         return;
      }

      String filename = arr[0];
      File f = new File("./tests/" + filename + ".txt");
      Scanner scan = new Scanner(f);

      int rows = scan.nextInt();
      int columns = scan.nextInt();

      int[][] input = new int[rows][columns];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            input[i][j] = scan.nextInt();
         }
      }

      findHighestScore(input, rows, columns);
   }

   public static void findHighestScore(int[][] input, int rowsBefore, int columnsBefore) {
      int rows = rowsBefore + 1;
      int columns = columnsBefore + 1;

      int[][] score = new int[rows][columns];

      // Fill the first row and column with 0's
      for (int i = 0; i < rows; i++) {
         score[i][0] = 0;
      }
      for (int i = 0; i < columns; i++) {
         score[0][i] = 0;
      }

      // fill the rest of the score grid
      for (int i = 1; i < rows; i++) {
         for (int j = 1; j < columns; j++) {
            int left = score[i][j-1];
            int top = score[i-1][j];
            int self = input[i-1][j-1]; // this is different

            score[i][j] = Math.max(Math.max(top, left) + self, self);
         }
      }

      // Determine the highest val in the right column and bottom row
      int maxScore = score[1][1];

      for (int i = 1; i < rows; i++) { // right column
         maxScore = Math.max(maxScore, score[i][columns-1]);
      }
      for (int i = 1; i < columns; i++) { // bottom row
         maxScore = Math.max(maxScore, score[rows-1][i]);
      }

      System.out.println(maxScore);
   }

   public static void printGrid(int[][] grid, int rows, int columns) {
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            System.out.print(grid[i][j] + " ");
         }
         System.out.println();
      }
   }
}
