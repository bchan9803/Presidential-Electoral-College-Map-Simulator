import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class ECVoteSim {
  public static void main(String[] args) {
    //
    HashMap<String, Integer> electoralCollegeMap = new HashMap<String, Integer>();
    electoralCollegeMap.put("Alabama", 9);
    electoralCollegeMap.put("Alaska", 3);
    electoralCollegeMap.put("Arizona", 11);
    //

    // System.out.println(electoralCollegeMap);

    Scanner getInput = new Scanner(System.in);

    System.out.println("Welcome to the Presidential Electoral College Vote Simulator! (created by Bryan Chan 2023)\n");

    System.out.println("Enter candidate #1 name: ");
    String candidateOne = getInput.nextLine();
    System.out.println("Enter candidate #2 name: ");
    String candidateTwo = getInput.nextLine();

    System.out.println(
        "\nFor the following states, enter who won using 1 (" + candidateOne + ") or 2 (" + candidateTwo + ").");
    System.out.println("***For example: Who won Texas: 1***");
    System.out.println("\n==========================================\n");

    for (Map.Entry<String, Integer> state : electoralCollegeMap.entrySet()) {
      System.out.println(state.getKey() + state.getValue());
    }

  }
}
