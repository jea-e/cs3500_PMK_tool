package cs3500.pa01;

/**
 * Different difficulty types.
 */
public enum Difficulty {
  HARD("Hard: "),
  EASY("Easy: ");

  private String difficultyType;

  Difficulty(String difficultyType) {
    this.difficultyType = difficultyType;
  }

  public String getDifficultType() {
    return this.difficultyType;
  }
}
