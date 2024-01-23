package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sorts a list of questions.
 */
public class SortQuestions implements Sort {
  private ArrayList<Question> list;

  SortQuestions(ArrayList<Question> list) {
    this.list = list;
  }

  /**
   * Sorts the questions depending on the given difficulty.
   *
   * @param tag the given difficulty
   * @return ArrayList the list of questions with the given difficulty
   */
  @Override
  public ArrayList<Question> sortBy(String tag) {
    ArrayList<Question> sortedList = new ArrayList<>();
    Question question;
    for (int i = 0; i < list.size(); i++) {
      question = list.get(i);
      if (tag.equals(Difficulty.EASY.getDifficultType())) {
        if (question.isEasy()) {
          sortedList.add(question);
        }
      } else if (tag.equals(Difficulty.HARD.getDifficultType())) {
        if (!question.isEasy()) {
          sortedList.add(question);
        }
      } else {
        throw new IllegalArgumentException("Not a valid difficulty type.");
      }
    }
    return sortedList;
  }

  /**
   * Determines the number of hard questions.
   *
   * @return int the number of hard questions
   */
  public int returnNumHard() {
    return this.sortBy("Hard: ").size();
  }

  /**
   * Determines the number of easy questions.
   *
   * @return int the number of hard questions
   */
  public int returnNumEasy() {
    return this.sortBy("Easy: ").size();
  }

  /**
   * Returns a list of randomized hard questions.
   *
   * @return ArrayList the list of questions
   */
  private ArrayList<Question> randomHardQuestions() {
    ArrayList<Question> outputList = new ArrayList<>();
    Random randInt = new Random();
    while (!list.isEmpty()) {
      int randIndex = randInt.nextInt(list.size());
      Question randomQuestion = list.remove(randIndex);
      outputList.add(randomQuestion);
    }
    return outputList;
  }

  /**
   * Returns a list of randomized easy questions.
   *
   * @return ArrayList the list of questions
   */
  private ArrayList<Question> randomEasyQuestions() {
    ArrayList<Question> outputList = new ArrayList<>();
    Random randInt = new Random();
    while (!list.isEmpty()) {
      int randIndex = randInt.nextInt(list.size());
      Question randomQuestion = list.remove(randIndex);
      outputList.add(randomQuestion);
    }
    return outputList;
  }

  /**
   * Combines a list of easy questions with the list of hard questions.
   *
   * @param hardQuestions the list of hard questions
   * @return ArrayList the list of combined questions
   */
  private ArrayList<Question> combineEasyHard(ArrayList<Question> hardQuestions) {
    ArrayList<Question> combinedList = new ArrayList<>();
    for (Question q : hardQuestions) {
      combinedList.add(q);
    }
    for (Question q : list) {
      combinedList.add(q);
    }
    return combinedList;
  }

  /**
   * Creates a list of questions with list size depending on argument.
   *
   * @param numOfQuestions the number of questions in the list
   * @return ArrayList the list of questions
   */
  public ArrayList<Question> questionSequence(int numOfQuestions) {
    ArrayList<Question> outputList = new ArrayList<>();
    if (numOfQuestions < 0) {
      throw new IllegalArgumentException("Cannot have a negative number of questions.");
    }
    if (list.size() <= numOfQuestions) {
      for (int i = 0; i < list.size(); i++) {
        outputList.add(list.get(i));
      }
    } else if (list.size() > numOfQuestions) {
      for (int i = 0; i < numOfQuestions; i++) {
        outputList.add(list.get(i));
      }
    }
    return outputList;
  }

  /**
   * Gets a list of questions from a .sr file.
   *
   * @param path the path of the file
   * @return ArrayList the list of questions
   * @throws IOException when the path of the file does not exist
   */
  private SortQuestions getQuestions(Path path) throws IOException {
    List<String> lines = Files.readAllLines(path);
    for (String line : lines) {
      Question question = new Question(line);
      list.add(question);
    }
    return this;
  }

  /**
   * Puts together all the questions
   *
   * @param path the path of the file to get the questions
   * @return ArrayList the list of questions
   * @throws IOException if the file is not found
   */
  public ArrayList<Question> putTogether(Path path) throws IOException {
    SortQuestions hardQuestions = new SortQuestions(this.getQuestions(path).sortBy("Hard: "));
    SortQuestions easyQuestions = new SortQuestions(this.getQuestions(path).sortBy("Easy: "));
    SortQuestions easyQuestionsSort = new SortQuestions(easyQuestions.randomEasyQuestions());
    return easyQuestionsSort.combineEasyHard(hardQuestions.randomHardQuestions());
  }
}