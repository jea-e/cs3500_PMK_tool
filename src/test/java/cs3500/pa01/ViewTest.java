package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {

  View view;
  Question question1;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void init() {
    view = new View();
    question1 = new Question("Easy: Where is NYC? ::: USA.");
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void testPrintQuestion() {
    view.printQuestions(question1);
    assertEquals("Where is NYC?", outputStreamCaptor.toString()
        .trim());
  }

  @Test
  public void testPrintChoice() {
    view.printChoice();
    assertEquals("1: Mark Easy\n2: Mark Hard\n3: See Answer\n4: Exit",
        outputStreamCaptor.toString().trim());
  }

  @Test
  public void testPrintAnswer() {
    view.printAnswer(question1);
    assertEquals("USA.", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testPrintStats() {
    view.printStats(5, 3, 2, 2, 3);
    assertEquals("Total questions answered: 5\nQuestions changed from easy to hard: 3\n"
            + "Questions changed from hard to easy: 2\nTotal easy questions: 2\nTotal hard "
            + "questions: 3",
        outputStreamCaptor.toString().trim());
  }

}