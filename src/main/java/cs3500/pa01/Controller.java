package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Delegates user interactions with other classes.
 */
public class Controller {
  Readable input;
  Appendable output;

  Controller(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  /**
   * Welcomes the user to the study session
   */
  public void welcome() {
    System.out.println("Welcome to the study session!");
  }

  /**
   * Starts the study session.
   *
   * @throws IOException when the path is not found
   */
  public void startStudySession() throws IOException {
    welcome();
    runSession();
  }

  /**
   * Ends the study session.
   */
  public void endSession() {
    System.out.println("Ending session.");
  }

  /**
   * Runs a study session.
   *
   * @throws IOException when path is not found
   */
  public void runSession() throws IOException {
    Path path = providePath();
    View view = new View();
    ArrayList<Question> questionList = getListQuestions(path);

    int countQuestions = 0;
    int easyToHard = 0;
    int hardToEasy = 0;

    for (Question question : questionList) {
      view.printQuestions(question);
      view.printChoice();
      Scanner myScanner = new Scanner(input);
      String next = myScanner.nextLine();
      if (next.equals("1")) {
        questionList.remove(question);
        questionList.add(question.changeEasy());
        hardToEasy++;
        countQuestions++;
      } else if (next.equals("2")) {
        questionList.remove(question);
        questionList.add(question.changeHard());
        easyToHard++;
        countQuestions++;
      } else if (next.equals("3")) {
        view.printAnswer(question);
        countQuestions++;
      } else if (next.equals("4")) {
        break;
      } else {
        throw new IllegalArgumentException("Not a valid choice.");
      }
    }

    SortQuestions newList = new SortQuestions(questionList);
    int countEasy = newList.returnNumEasy();
    int countHard = newList.returnNumHard();
    view.printStats(countQuestions, easyToHard, hardToEasy, countEasy, countHard);
    endSession();
  }

  /**
   * Gets a list of questions.
   *
   * @return ArrayList the list of questions
   * @throws IOException if the path is not found
   */
  public ArrayList<Question> getListQuestions(Path path) throws IOException {
    ArrayList<Question> questionList = new ArrayList<>();
    SortQuestions sortQuestionList = new SortQuestions(questionList);
    ArrayList<Question> sortedList = sortQuestionList.putTogether(path);
    SortQuestions sortQuestionsSortedList = new SortQuestions(sortedList);
    ArrayList<Question> outputList = sortQuestionsSortedList.questionSequence(questionCount());
    return outputList;
  }

  /**
   * Asks the user to provide the .sr path
   *
   * @return Path the path of the .sr file
   * @throws IllegalArgumentException when the path is not to a .sr file
   */
  public Path providePath() {
    System.out.println("Please provide the .sr path: ");
    Scanner myScanner = new Scanner(input);
    String next = myScanner.nextLine();
    if (next.endsWith(".sr")) {
      return Path.of(next);
    } else {
      throw new IllegalArgumentException("The path provide was not a .sr path.");
    }
  }

  /**
   * Allows the user to choose how many questions.
   *
   * @return int the number of questions
   */
  public int questionCount() {
    System.out.println("How many questions would you like?");
    Scanner myScanner = new Scanner(input);
    String next = myScanner.nextLine();
    if (Integer.parseInt(next) > -1) {
      System.out.println(next + " questions will be shown");
      return Integer.parseInt(next);
    } else {
      throw new IllegalArgumentException("Question count cannot be negative.");
    }
  }
}
