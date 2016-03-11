import java.util.*;
import java.io.*;
import java.lang.Math;


public class BasicGreedy {
   static int NUM_VERTICES = 25;
   static int[][] input = new int[NUM_VERTICES][NUM_VERTICES];

   public static void main(String arr[]) throws FileNotFoundException {
      if (arr == null) {
         System.out.println("You forgot to give a test name!");
         return;
      }

      String filename = arr[0];
      File f = new File("./tests/" + filename + ".txt");
      Scanner scan = new Scanner(f);

      // Initialize the matrix to all 0's
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {
            input[i][j] = 0;
         }
      }

      // Fill the adjacency matrix
      while (scan.hasNextInt()) {
         int first = scan.nextInt();
         int second = scan.nextInt();

         input[first][second] = 1;
         input[second][first] = 1;
      }

      basicGreedyVertexCover();
   }

   public static void basicGreedyVertexCover() {
      String vertices = "";
      int numVertices = 0;
      int v;

      //System.out.print("Vertices: ");
      while (graphHasEdges()) {
         v = randomVertex();
         // System.out.print(v + " ");
         removeVertex(v);
         numVertices++;
      }

      //System.out.println("Num vertices: " + numVertices);
      System.out.print(numVertices);
      System.out.println();
   }

   public static int randomVertex() {
      Random rand = new Random();
      int num;

      do {
         num = rand.nextInt(NUM_VERTICES);
      } while (!vertexHasAnEdge(num));

      return num;
   }

   public static boolean vertexHasAnEdge(int num) {
      for (int i = 0; i < NUM_VERTICES; i++) {
         if (input[i][num] == 1) {
            return true;
         }
      }
      return false;
   }

   public static void removeVertex(int v) {
      for (int i = 0; i < NUM_VERTICES; i++) {
         input[v][i] = 0;
         input[i][v] = 0;
      }
   }

   public static boolean graphHasEdges() {
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {
            if (input[i][j] == 1) {
               return true;
            }
         }
      }

      return false;
   }

   public static void printMatrix() {
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {
            if (input[i][j] == 0) {
               System.out.print("   ");
            } else {
               System.out.print(" " + input[i][j] + " ");
            }
         }
         System.out.println();
      }

   }
}
      /*
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {

         }
      }
      */

