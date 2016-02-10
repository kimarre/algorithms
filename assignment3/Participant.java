import java.util.*;
import java.io.*;

public class Participant implements Comparable<Participant> {
   int num; // Numbered camper according to input
   int swimTime;
   int bikeTime;
   int runTime;

   public Participant(int num, int swim, int bike, int run) {
      this.num = num;
      this.swimTime = swim;
      this.bikeTime = bike;
      this.runTime = run;
   }

   public int getBikeRunTime() {
      return this.bikeTime + this.runTime;
   }

   @Override
   public int compareTo(Participant camper) {
      int otherTime = ((Participant)camper).getBikeRunTime();
      return otherTime - this.getBikeRunTime();
   }

   @Override
   public String toString() {
      return "[ num=" + num + ", swim=" + swimTime + ", bike=" + bikeTime + ", run=" + runTime + " ]";
   }
}
