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

    HashMap<String, Integer> candidateVoteCount = new HashMap<String, Integer>();
    //

    // System.out.println(electoralCollegeMap);

    Scanner getInput = new Scanner(System.in);

    System.out.println("Welcome to the Presidential Electoral College Vote Simulator! (created by Bryan Chan 2023)\n");

    System.out.print("Enter candidate #1 name: ");
    String candidateOne = getInput.nextLine();
    System.out.print("Enter candidate #2 name: ");
    String candidateTwo = getInput.nextLine();

    System.out.println(
        "\nFor the following states, enter who won using 1 (" + candidateOne + ") or 2 (" + candidateTwo + ").");
    System.out.println("***For example: Who won Texas: 1***");
    System.out.println("\n==========================================\n");

    candidateVoteCount.put(candidateOne, 0);
    candidateVoteCount.put(candidateTwo, 0);

    for (Map.Entry<String, Integer> state : electoralCollegeMap.entrySet()) {

      Boolean getUserInput = true;
      while (getUserInput) {
        System.out.print("Who won " + state.getKey() + ": ");
        Integer choice = getInput.nextInt();

        if (choice == 1) {
          candidateVoteCount.replace(candidateOne, candidateVoteCount.get(candidateOne) + state.getValue());
          getUserInput = false;
        } else if (choice == 2) {
          candidateVoteCount.replace(candidateTwo, candidateVoteCount.get(candidateTwo) + state.getValue());
          getUserInput = false;
        } else {
          System.out.println("\nTry again.");
          getUserInput = true;
        }
      }
    }

    // System.out.println(candidateVoteCount);

    // Find out who has the most votes
    String candidateWithMostVotes = "";
    Integer candidateWithMostVotesTotal = 0;
    String candidateWithLeastVotes = "";
    Integer candidateWithLeastVotesTotal = 0;
    if (candidateVoteCount.get(candidateOne) > candidateVoteCount.get(candidateTwo)) {
      candidateWithMostVotes = candidateOne;
      candidateWithMostVotesTotal = candidateVoteCount.get(candidateOne);
      candidateWithLeastVotes = candidateTwo;
      candidateWithLeastVotesTotal = candidateVoteCount.get(candidateTwo);
    } else if (candidateVoteCount.get(candidateOne) > candidateVoteCount.get(candidateTwo)) {
      candidateWithMostVotes = candidateTwo;
      candidateWithMostVotesTotal = candidateVoteCount.get(candidateTwo);
      candidateWithLeastVotes = candidateOne;
      candidateWithLeastVotesTotal = candidateVoteCount.get(candidateOne);
    }

    System.out.println("\n================== RESULTS ==================\n");

    if (candidateWithMostVotesTotal >= 270) {
      System.out
          .println(candidateWithMostVotes + " won the presidency with " + candidateWithMostVotesTotal + " votes!");
    } else {
      System.out.println(
          "Neither candidate has obtained 270 votes! The House of Representatives will vote on the next president.");
      System.out.println(candidateWithMostVotes + " came in top with  " + candidateWithMostVotesTotal + " votes!");
    }

    System.out.println(candidateWithLeastVotes + " got " + candidateWithLeastVotesTotal + " votes.");

    System.out.println("\n===========================================");

  }
}
