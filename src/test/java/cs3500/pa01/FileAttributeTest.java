package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the FileAttributes class, as well as CompareCreated, CompareFileName, and CompareModified.
 */
class FileAttributeTest {

  File tempArrays; //created first
  FileAttribute tempArraysAttr;
  File tempVectors; //created second
  FileAttribute tempVectorsAttr;
  File temp;

  @BeforeEach
  public void init() throws IOException {
    tempArrays = File.createTempFile("arrays", ".md");
    tempArraysAttr = new FileAttribute(tempArrays);
    tempArrays.setLastModified(12345);

    tempVectors = File.createTempFile("vectors", ".md");
    tempVectorsAttr = new FileAttribute(tempVectors);

    temp = File.createTempFile("empty", ".md");
  }

  @Test
  public void testCompareCreated() throws IOException {
    //arrays created first
    assertEquals(tempArraysAttr.compareCreated(tempVectors), -1);
    //arrays created first
    assertEquals(tempVectorsAttr.compareCreated(tempArrays), 1);
    //created same time
    assertEquals(tempVectorsAttr.compareCreated(tempVectors), 0);
  }

  @Test
  public void testCompareModified() throws IOException {
    //arrays modified first
    assertEquals(tempArraysAttr.compareModified(tempVectors), -1);
    //arrays modified first
    assertEquals(tempVectorsAttr.compareModified(tempArrays), 1);
    //same modified time
    assertEquals(tempVectorsAttr.compareModified(tempVectors), 0);
  }

  @Test
  public void testCompareName() {
    //arrays comes first
    assertEquals(tempArraysAttr.compareName(tempVectors), 1);
    //arrays comes first
    assertEquals(tempVectorsAttr. compareName(tempArrays), -1);
    //same name
    assertEquals(tempArraysAttr.compareName(tempArrays), 0);
  }

  @Test
  public void testCompareCreatedException() throws IOException {
    CompareCreated fileComparison = new CompareCreated(temp);
    temp.delete();
    assertThrows(RuntimeException.class, () -> fileComparison.compareTo(temp));
  }

  @Test
  public void testCompareModifiedException() throws IOException {
    CompareModified fileComparison = new CompareModified(temp);
    temp.delete();
    assertThrows(RuntimeException.class, () -> fileComparison.compareTo(temp));
  }
}