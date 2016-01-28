import java.util.*;
import java.io.*;

public class ColoringAlgorithm {
   public static int NUM_VERTICES = 100;
   public static Queue<Integer> queue = new LinkedList<Integer>();
   public static int numComponents = 1;
   public static boolean graphResult = true;

   public static int RED = 1;
   public static int BLUE = 2;

   public static void main(String arr[]) throws FileNotFoundException {
      int[][] neighborList = new int[NUM_VERTICES][NUM_VERTICES];
      int[] vertices = new int[NUM_VERTICES];

      String filename = arr[0];

      // Iniitialize all as empty
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {
            neighborList[i][j] = 0;
         }
      }

      File f = new File("./tests/" + filename + ".txt");
      Scanner input = new Scanner(f);

      // Build the adjacency matrix
      while (input.hasNextLine()) {
         String text = input.nextLine();
         int first = Character.getNumericValue(text.charAt(0));
         int second = Character.getNumericValue(text.charAt(2));

         neighborList[first][second] = 1;
         neighborList[second][first] = 1;
      }

      int startIndex = findFirstConnectedVertex(neighborList);

      boolean result = isColorable(neighborList, vertices, startIndex);

      if (result) {
         System.out.println("The graph is two-colorable and has " + numComponents + " component(s).");
      } else {
         System.out.println("The graph is not two-colorable and has " + numComponents + " component(s).");
      }
   }

   public static boolean isColorable(int[][] neighborList, int[] vertices, int vertex) {
      int hasRed = -1;
      int hasBlue = -1;
      int nextVertex = -1;

      // Assumes empty queue to start with
      queue.add(vertex);

      while (queue.peek() != null) {
         vertex = queue.remove();

         hasRed = -1;
         hasBlue = -1;

         // If the vertex has already been colored
         if (vertices[vertex] != 0) {
            continue;
         }

         // Check to see if neighbors are both colors
         for (int i = 0; i < NUM_VERTICES; i++) {

            // if vertices are neighbors
            if (neighborList[vertex][i] == 1) {
               if (vertices[i] == RED) {
                  hasRed = i;
               } else if (vertices[i] == BLUE) {
                  hasBlue = i;
               } else {
                  if (nextVertex == -1) {
                     nextVertex = i;
                  }
                  queue.add(i);
               }
            }
         }

         if (hasRed != -1 && hasBlue != -1) {
            graphResult = false;
            vertices[vertex] = RED;
            break;
         }

         if (hasBlue != -1) {
            vertices[vertex] = RED;
         } else {
            vertices[vertex] = BLUE;
         }
      }

      // We've looked at all vertices for this component

      // See if there are still uncolored vertices
      for (int i = 0; i < NUM_VERTICES; i++) {
         if (vertices[i] == 0 && !isAlone(neighborList, i)) {
            numComponents++;
            return isColorable(neighborList, vertices, i);
         }
      }

      return graphResult;
   }

   public static boolean isAlone(int[][] neighborList, int vertex) {
      boolean result = true;

      for (int i = 0; i < NUM_VERTICES; i++) {
         if (neighborList[vertex][i] == 1) {
            result = false;
         }
      }
      return result;
   }

   public static int findFirstConnectedVertex(int[][] neighborList) {
      for (int i = 0; i < NUM_VERTICES; i++) {
         if (!isAlone(neighborList, i)) {
            return i;
         }
      }

      System.out.println("Ruh-roh. Shouldn't have gotten here...");
      return 0;
   }

   public static void printAllColors(int[] vertices) {
      for (int i = 0; i < NUM_VERTICES; i++) {
         if (vertices[i] == BLUE) {
            System.out.println(i + ": blue");
         }
         else if (vertices[i] == RED) {
            System.out.println(i + ": red");
         }
         else {
            System.out.println(i + ": uncolored");
         }
      }
   }

   public static void printMatrix(int[][] list) {
      for (int i = 0; i < NUM_VERTICES; i++) {
         for (int j = 0; j < NUM_VERTICES; j++) {
            System.out.print(list[i][j] + " ");
         }
         System.out.println();
      }
   }
}
