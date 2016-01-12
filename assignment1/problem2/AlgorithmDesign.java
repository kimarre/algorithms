import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * @author Kim Arre (karre@calpoly.edu)
 * CPE 349: Algorithms
 * Dr. Theresa Migler-VonDollen
 * Winter 2016
 */

public class AlgorithmDesign {
   public static void findSingleNum(ArrayList<Integer> list) {
      int size = list.size();
      // only pairs of two matching ones
      if (size % 2 == 0) {
         return;
      }

      // woop! found it.
      if (size == 3) {
         if (list.get(0) != list.get(1)) {
            System.out.println(list.get(0));
         } else {
            System.out.println(list.get(2));
         }
         return;
      }

      int middle = size / 2;

      if (list.get(middle).equals(list.get(middle - 1))) { // 2nd index is non-inclusive
         findSingleNum(new ArrayList<Integer>(list.subList(middle + 1, size)));
         findSingleNum(new ArrayList<Integer>(list.subList(0, middle + 1)));
         return;
      } else if (list.get(middle).equals( list.get(middle + 1))) {
         findSingleNum(new ArrayList<Integer>(list.subList(0, middle)));
         findSingleNum(new ArrayList<Integer>(list.subList(middle, size)));
         return;
      } else {
         // middle is different!
         System.out.println(list.get(middle));
         return;
      }
   }

   public static void main(String arr[]) throws FileNotFoundException {
      File f = new File("./input.txt");
      Scanner input = new Scanner(f);

      String text = input.nextLine();
      text = text.substring(1, text.length()-1); // Trim off brackets

      String stringArr[] = text.split(", ");
      int inputArr[] = new int[stringArr.length];

      // Concert into usable integer array
      for (int i = 0; i < stringArr.length; i++) {
         inputArr[i] = Integer.parseInt(stringArr[i]);
      }

      ArrayList<Integer> list = new ArrayList<Integer>();

      // Fill ArrayList
      for (int i = 0; i < inputArr.length; i++) {
         list.add(inputArr[i]);
      }

      // Do the thing
      findSingleNum(list);
   }
}
