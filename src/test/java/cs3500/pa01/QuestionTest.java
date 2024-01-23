package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the methods of the question class.
 */
class QuestionTest {
  Question question1;
  Question question2;
  Question question3;
  Question question4;

  @BeforeEach
  public void init() {
    question1 = new Question("Easy: Where is NYC? ::: USA.");
    question2 = new Question("Hard: Where is Boston? ::: MA.");
    question3 = new Question("Hard: Where is NYC? ::: USA.");
    question4 = new Question("Easy: Where is Boston? ::: MA.");
  }

  @Test
  public void testGetDifficulty() {
    assertEquals("Easy: ", question1.getDifficulty());
    assertEquals("Hard: ", question2.getDifficulty());
  }

  @Test
  public void testGetQuestion() {
    assertEquals("Where is NYC?", question1.getQuestion());
    assertEquals("Where is Boston?", question2.getQuestion());
  }

  @Test
  public void testGetAnswer() {
    assertEquals("USA.", question1.getAnswer());
    assertEquals("MA.", question2.getAnswer());
  }

  @Test
  public void testIsEasy() {
    assertTrue(question1.isEasy());
    assertFalse(question2.isEasy());
  }

  @Test
  public void testChangeEasy() {
    assertTrue(isSame(question4, question2.changeEasy()));
  }

  @Test
  public void testChangeHard() {
    assertTrue(isSame(question3, question1.changeHard()));
  }

  /**
   * Determines if the questions are the same.
   *
   * @param question1 question to be compared
   * @param question2 question to be compared
   * @return Boolean whether they are the same
   */
  public Boolean isSame(Question question1, Question question2) {
    if (question1.getQuestion().equals(question2.getQuestion())) {
      if (question1.getAnswer().equals(question2.getAnswer())) {
        if (question1.getDifficulty().equals(question2.getDifficulty())) {
          return true;
        }
      }
    }
    return false;
  }
}