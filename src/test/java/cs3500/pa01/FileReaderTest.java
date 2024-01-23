package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the FileReader class.
 */
class FileReaderTest {
  File arrays;
  Path arraysPath;
  Path samplesPath;
  BasicFileAttributes samplesAttr;
  ArrayList<File> fileList;
  FileReader fileReader;
  File temp;
  IOException fileNotFoundException;

  @BeforeEach
  public void init() throws IOException {
    arrays = new File("samples/arrays.md");
    arraysPath = Path.of("samples/arrays.md");
    samplesPath = Path.of("samples");
    samplesAttr = Files.readAttributes(samplesPath, BasicFileAttributes.class);
    fileList = new ArrayList<>();
    fileReader = new FileReader(fileList);
    temp = File.createTempFile("empty", ".md");
  }

  @Test
  public void testGetList() {
    fileList.add(arrays);
    assertEquals(fileList, fileReader.getList());
  }

  @Test
  public void testVisitFile() {
    try {
      Files.walkFileTree(arraysPath, fileReader);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testPreVisitDirectory() {
    assertEquals(FileVisitResult.CONTINUE, fileReader.preVisitDirectory(samplesPath, samplesAttr));
  }

  @Test
  public void testPostVisitDirectory() {
    assertEquals(FileVisitResult.CONTINUE,
        fileReader.postVisitDirectory(samplesPath, fileNotFoundException));
  }

  @Test
  public void testVisitFileFailed() {
    temp.delete();
    assertEquals(FileVisitResult.CONTINUE,
        fileReader.visitFileFailed(temp.toPath(), fileNotFoundException));
  }
}