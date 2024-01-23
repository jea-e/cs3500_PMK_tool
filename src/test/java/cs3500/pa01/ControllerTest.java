package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {
  Controller intTest;
  Controller intTestFail;
  Controller pathTest;
  Controller pathTestFail;
  ArrayList<Question> questionList;
  Question question1;
  Question question2;
  SortQuestions questionListSort;
  ArrayList<Question> sortedQuestions;
  Path studyGuidePath;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void init() {
    System.setOut(new PrintStream(outputStreamCaptor));
    intTest = new Controller(new StringReader("2"), System.out);
    intTestFail = new Controller(new StringReader("-1"), System.out);
    pathTest = new Controller(new StringReader("samples/StudyGuide.sr"), System.out);
    pathTestFail = new Controller(new StringReader("studyguide"), System.out);
    questionList = new ArrayList<>();
    question1 = new Question("Hard: What is the powerhouse of the cell? ::: Mitochondria");
    question2 = new Question("Easy: What is 1 + 1? ::: 2");
    questionList.add(question2);
    questionList.add(question1);
    questionListSort = new SortQuestions(questionList);
    sortedQuestions = new ArrayList<>();
    sortedQuestions = questionListSort.questionSequence(2);
    studyGuidePath = Path.of("samples/StudyGuide.sr");
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }


  @Test
  public void testWelcome() {
    pathTest.welcome();
    assertEquals("Welcome to the study session!", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testStartStudySession() {

  }

  @Test
  public void testEndSession() {
    pathTest.endSession();
    assertEquals("Ending session.", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testRunSession() {

  }

  /*
  @Test
  public void testGetListQuestions() throws IOException {
    assertEquals(sortedQuestions, intTest.getListQuestions(studyGuidePath));
  }
   */

  @Test
  public void testProvidePath() {
    assertEquals(studyGuidePath, pathTest.providePath());
    assertThrows(IllegalArgumentException.class, () -> pathTestFail.providePath());
  }

  @Test
  public void testQuestionCount() {
    assertEquals(2, intTest.questionCount());
    assertThrows(IllegalArgumentException.class, () -> intTestFail.questionCount());
  }

}