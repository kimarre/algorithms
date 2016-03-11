import java.util.*;
import java.io.*;
import java.lang.Math;


public class SmartGreedy {
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

      smartGreedyVertexCover();
   }

   public static void smartGreedyVertexCover() {
      String vertices = "";
      int numVertices = 0;
      int v;

      //System.out.print("Vertices: ");
      while (graphHasEdges()) {
         v = findHighestDegreeVertex();
         //System.out.print(v + " ");
         removeVertex(v);
         vertices.concat(Integer.toString(v));
         numVertices++;
      }

      //System.out.println("Num vertices: " + numVertices);
      System.out.print(numVertices);
      System.out.println();
   }

   public static int findHighestDegreeVertex() {
      int maxDegree = 0;
      int vertex = -1;
      int count;

      for (int i = 0; i < NUM_VERTICES; i++) {
         count = 0;

         for (int j = 0; j < NUM_VERTICES; j++) {
            if (input[i][j] == 1) {
               count++;
            }

            if (count > maxDegree) {
               maxDegree = count;
               vertex = i;
            }
         }
      }

      if (vertex == -1) {
         System.out.println("UH OH. Did not find a vertex with an edge.");
      }

      return vertex;
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

