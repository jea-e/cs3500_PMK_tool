package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortQuestionsTest {
  Question question1;
  Question question2;
  Question question3;
  Question question4;
  Question question5;
  ArrayList<Question> listQuestions;
  ArrayList<Question> hardQuestions;
  ArrayList<Question> easyQuestions;
  ArrayList<Question> easyHard;
  ArrayList<Question> easyHardMinus;
  ArrayList<Question> listEasy;
  ArrayList<Question> listHard;

  @BeforeEach
  public void init() {
    question1 = new Question("Hard: Where is New York located? ::: USA.");
    question2 = new Question("Easy: Where is Boston? ::: MA.");
    question3 = new Question("Hard: How many sides in a triangle? ::: 3.");
    question4 = new Question("Hard: How many tentacles does an octopus have? ::: 8.");
    question5 = new Question("Easy: What letter starts the alphabet? ::: a.");

    listQuestions = new ArrayList<>();
    listQuestions.add(question1);
    listQuestions.add(question2);

    hardQuestions = new ArrayList<>();
    hardQuestions.add(question1);
    hardQuestions.add(question3);
    hardQuestions.add(question4);

    easyQuestions = new ArrayList<>();
    easyQuestions.add(question2);
    easyQuestions.add(question5);

    easyHard = new ArrayList<>();
    easyHard.add(question1);
    easyHard.add(question3);
    easyHard.add(question4);
    easyHard.add(question2);
    easyHard.add(question5);

    easyHardMinus = new ArrayList<>();
    easyHardMinus.add(question1);
    easyHardMinus.add(question3);

    listEasy = new ArrayList<>();
    listEasy.add(question2);

    listHard = new ArrayList<>();
    listHard.add(question1);
  }

  @Test
  public void testSortBy() {
    SortQuestions sortTest = new SortQuestions(listQuestions);
    assertEquals(listEasy, sortTest.sortBy(Difficulty.EASY.getDifficultType()));
    assertEquals(listHard, sortTest.sortBy(Difficulty.HARD.getDifficultType()));
    assertThrows(IllegalArgumentException.class, () -> sortTest.sortBy("hello"));
  }

  @Test
  public void testReturnNumHard() {
    SortQuestions sortTest = new SortQuestions(listQuestions);
    assertEquals(1, sortTest.returnNumHard());
  }

  @Test
  public void testReturnNumEasy() {
    SortQuestions sortTest = new SortQuestions(listQuestions);
    assertEquals(1, sortTest.returnNumEasy());
  }

  @Test
  public void testQuestionSequence() {
    SortQuestions sortTest = new SortQuestions(easyHard);
    assertEquals(easyHard, sortTest.questionSequence(10));
    assertEquals(easyHardMinus, sortTest.questionSequence(2));
    assertThrows(IllegalArgumentException.class, () -> sortTest.questionSequence(-1));
  }

}