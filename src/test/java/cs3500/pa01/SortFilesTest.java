package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests methods in the SortFiles class
 */
class SortFilesTest {
  File tempArrays; //created first
  File tempVectors; //created second
  ArrayList<File> testList1;
  ArrayList<File> testList2;
  ArrayList<File> fileNameList;
  ArrayList<File> createdList;
  ArrayList<File> modifiedList;

  @BeforeEach
  public void init() throws IOException {
    tempArrays = File.createTempFile("arrays", ".md");
    tempArrays.setLastModified(12345);

    tempVectors = File.createTempFile("vectors", ".md");

    testList1 = new ArrayList<>();
    testList1.add(tempArrays);
    testList1.add(tempVectors);

    testList2 = new ArrayList<>();
    testList2.add(tempVectors);
    testList2.add(tempArrays);

    fileNameList = new ArrayList<>();
    fileNameList.add(tempVectors);
    fileNameList.add(tempArrays);

    createdList = new ArrayList<>();
    createdList.add(tempArrays);
    createdList.add(tempVectors);

    modifiedList = new ArrayList<>();
    modifiedList.add(tempArrays);
    modifiedList.add(tempVectors);
  }

  @Test
  public void testSortByFlag() {
    SortFiles testSort1 =  new SortFiles(testList1);
    SortFiles testSort2 = new SortFiles(testList2);

    assertEquals(testSort1.sortBy("filename"), fileNameList);
    assertEquals(testSort2.sortBy("filename"), fileNameList);

    assertEquals(testSort1.sortBy("created"), createdList);
    assertEquals(testSort2.sortBy("created"), createdList);

    assertEquals(testSort1.sortBy("modified"), modifiedList);
    assertEquals(testSort2.sortBy("modified"), modifiedList);

    assertThrows(IllegalArgumentException.class, () -> testSort1.sortBy("hello"));

  }
}