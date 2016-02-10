import java.util.*;
import java.io.*;

public class Triathlon {

   public static void main(String arr[]) throws FileNotFoundException {
      if (arr == null) {
         System.out.println("You forgot to give a test name!");
         return;
      }
      String filename = arr[0];
      ArrayList<Participant> campers = new ArrayList<Participant>();

      File f = new File("./tests/" + filename + ".txt");
      Scanner input = new Scanner(f);

      int result;

      while (input.hasNextInt()) {
         Participant entry = new Participant(input.nextInt(), input.nextInt(),
         input.nextInt(), input.nextInt());
         campers.add(entry);
      }

      Collections.sort(campers);
      printResults(campers);
   }

   public static void printResults(ArrayList<Participant> campers) {
      System.out.print("Sequence: ");
      int maxCompletionTime = 0;
      int runBikeTime = 0;

      for (int i = 0; i < campers.size(); i++) {
         int camperTime = 0;

         Participant camper = campers.get(i);
         System.out.print(camper.num + " ");

         for (int j = 0; j <= i; j++) {
            camperTime += campers.get(j).swimTime;
         }
         camperTime += camper.bikeTime + camper.runTime;

         if (maxCompletionTime < camperTime) {
            maxCompletionTime = camperTime;
         }
      }

      System.out.println();
      System.out.println("Completion time: " + maxCompletionTime);

   }
}
