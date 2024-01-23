package cs3500.pa01;

/**
 * Controls what is displayed to the user.
 */
public class View {

  /**
   * Prints the question.
   *
   * @param question the question
   */
  public void printQuestions(Question question) {
    System.out.println(question.getQuestion());
  }

  /**
   * Prints the user's choices.
   */
  public void printChoice() {
    System.out.println("1: Mark Easy");
    System.out.println("2: Mark Hard");
    System.out.println("3: See Answer");
    System.out.println("4: Exit");
  }

  /**
   * Prints the answer to the question.
   *
   * @param question the question
   */
  public void printAnswer(Question question) {
    System.out.println(question.getAnswer());
  }

  /**
   * Prints the stats of a session.
   *
   * @param questionsAnswered the number of questions answered
   * @param easyToHard the number of questions changed from easy to hard
   * @param hardToEasy the number of questions changed from hard to easy
   * @param easy the number of easy questions
   * @param hard the number of hard questions
   */
  public void printStats(int questionsAnswered, int easyToHard, int hardToEasy,
                         int easy, int hard) {
    System.out.println("Total questions answered: " + questionsAnswered);
    System.out.println("Questions changed from easy to hard: " + easyToHard);
    System.out.println("Questions changed from hard to easy: " + hardToEasy);
    System.out.println("Total easy questions: " + easy);
    System.out.println("Total hard questions: " + hard);
  }
}
