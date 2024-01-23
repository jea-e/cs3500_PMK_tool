package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileEditorTest {
  File arrays;
  File arraysSorted;
  File questions;
  File questionsSorted;
  File vectors;
  File vectorsSorted;
  File temp1;
  File temp2;
  File temp3;
  FileEditor fileEditor1;
  FileEditor fileEditor2;
  FileEditor fileEditor3;
  ArrayList<File> listFiles1;
  ArrayList<File> listFiles2;
  ArrayList<File> listFiles3;

  @BeforeEach
  public void init() throws IOException {
    arrays = new File("samples/arrays.md");
    arraysSorted = new File("samples/arraysSorted.md");
    questions = new File("samples/questions.md");
    questionsSorted = new File("samples/questionsSorted.md");
    vectors = new File("samples/vectors.md");
    vectorsSorted = new File("samples/vectorsSorted.md");

    temp1 = File.createTempFile("temp", ".md");
    temp2 = File.createTempFile("temp", ".md");
    temp3 = File.createTempFile("temp", ".md");

    fileEditor1 = new FileEditor(temp1);
    fileEditor2 = new FileEditor(temp2);
    fileEditor3 = new FileEditor(temp3);

    listFiles1 = new ArrayList<>();
    listFiles1.add(arrays);
    listFiles2 = new ArrayList<>();
    listFiles2.add(vectors);
    listFiles3 = new ArrayList<>();
    listFiles3.add(questions);
  }

  @Test
  public void testWriteFile() throws IOException {
    assertTrue(compareFiles(arraysSorted, fileEditor1.writeFile(listFiles1)));
    assertTrue(compareFiles(vectorsSorted, fileEditor2.writeFile(listFiles2)));
  }

  @Test
  public void testWriteSr() throws IOException {
    assertTrue(compareFiles(questionsSorted, fileEditor3.writeSr(listFiles3)));
  }

  /**
   * To test if the contents of the two file are equal
   *
   * @param file1 one of the files to be tested
   * @param file2 the other file to be tested
   * @return boolean whether the contents are equal
   */

  public static boolean compareFiles(File file1, File file2) {
    try (Scanner scanner1 = new Scanner(file1);
         Scanner scanner2 = new Scanner(file2)) {

      while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
        String line1 = scanner1.nextLine();
        String line2 = scanner2.nextLine();

        if (!line1.equals(line2)) {
          return false;
        }
      }

      return true;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  }
}