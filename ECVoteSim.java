import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class ECVoteSim {
  public static void main(String[] args) {

    String file = "./USElectoralMap.csv";
    BufferedReader reader = null;
    String line = "";

    HashMap<String, Integer> electoralCollegeMap = new HashMap<String, Integer>();

    try {
      reader = new BufferedReader(new FileReader(file));
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");

        String state = parts[0].trim();
        String electoralVoteStr = parts[1].trim();

        if (!state.equals("") && !state.equals("state") && !electoralVoteStr.equals("")
            && !electoralVoteStr.equals("vote")) {
          Integer electoralVoteInt = Integer.parseInt(electoralVoteStr);
          electoralCollegeMap.put(state, electoralVoteInt);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // For testing purposes
    // System.out.println("\n\nelectoralCollegeMap: " + electoralCollegeMap);

    HashMap<String, Integer> candidateVoteCount = new HashMap<String, Integer>();
    //

    // System.out.println(electoralCollegeMap);

    Scanner getInput = new Scanner(System.in);

    System.out.println("\nWelcome to the Presidential Electoral College Vote Simulator! (created by Bryan Chan 2023)\n");

    System.out.print("Enter candidate #1 name: ");
    String candidateOne = getInput.nextLine();
    System.out.print("Enter candidate #2 name: ");
    String candidateTwo = getInput.nextLine();

    System.out.println(
        "\nFor the following states, enter who won using 1 (" + candidateOne + ") or 2 (" + candidateTwo + ").");
    System.out.println("***For example: Who won Texas (40 E.C. votes): 1***");
    System.out.println("\n==========================================\n");

    candidateVoteCount.put(candidateOne, 0);
    candidateVoteCount.put(candidateTwo, 0);

    Integer stateCount = 1;

    for (Map.Entry<String, Integer> state : electoralCollegeMap.entrySet()) {

      Boolean getUserInput = true;
      while (getUserInput) {
        System.out.print("[" + stateCount + "] Who won " + state.getKey() + " (" + state.getValue() + " E.C. votes): ");

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
      stateCount++;
    }


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

    } else if (candidateVoteCount.get(candidateTwo) > candidateVoteCount.get(candidateOne)) {
      candidateWithMostVotes = candidateTwo;
      candidateWithMostVotesTotal = candidateVoteCount.get(candidateTwo);
      candidateWithLeastVotes = candidateOne;
      candidateWithLeastVotesTotal = candidateVoteCount.get(candidateOne);
    }

    System.out.println("\n================== RESULTS ==================\n");

    if (candidateWithMostVotesTotal >= 270) {
      System.out.println(candidateWithMostVotes + " won the presidency with " + candidateWithMostVotesTotal + " votes!");
    } else {
      System.out.println("Neither candidate has obtained 270 votes! The House of Representatives will vote on the next president.");
      System.out.println(candidateWithMostVotes + " came in top with " + candidateWithMostVotesTotal + " votes!");
    }

    System.out.println(candidateWithLeastVotes + " got " + candidateWithLeastVotesTotal + " votes.");

    System.out.println("\n===========================================");

  }
}
