package cs3500.pa01;

/**
 * Questions class.
 */
public class Question {
  private String line;
  private String difficulty;

  Question(String line) {
    this.line = line;
    this.difficulty = line.substring(0, 6); //Either "Hard: " or "Easy: "
  }

  /**
   * Gets the difficulty of the question.
   *
   * @return String the difficulty
   */
  public String getDifficulty() {
    return this.difficulty;
  }

  /**
   * Gets the question of a line.
   *
   * @return String the question
   */
  public String getQuestion() {
    int questionStart = 6;
    int questionEnd = line.indexOf(":::", questionStart);
    return line.substring(questionStart, questionEnd).trim();
  }

  /**
   * Gets the answer of a line.
   *
   * @return String the answer
   */
  public String getAnswer() {
    int answerStart = line.indexOf(":::", 6);
    int answerEnd = line.length();
    return line.substring(answerStart + 3, answerEnd).trim();
  }

  /**
   * Determines if the question is easy.
   *
   * @return boolean whether the question is easy
   */
  public boolean isEasy() {
    return (this.getDifficulty().equals(Difficulty.EASY.getDifficultType()));
  }

  /**
   * Changes the question difficulty to easy.
   *
   * @return Question the question with difficulty easy
   */
  public Question changeEasy() {
    String questionAnswer = this.line.substring(6, line.length());
    Question output = new Question(Difficulty.EASY.getDifficultType().concat(questionAnswer));
    return output;
  }

  /**
   * Changes the question difficulty to hard.
   *
   * @return Question with difficulty hard.
   */
  public Question changeHard() {
    String questionAnswer = this.line.substring(6, line.length());
    Question output = new Question(Difficulty.HARD.getDifficultType().concat(questionAnswer));
    return output;
  }
}
