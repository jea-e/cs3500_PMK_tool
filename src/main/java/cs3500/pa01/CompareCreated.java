package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Compares the creation time of files.
 */
public class CompareCreated implements Compare {
  private File file;
  private Path path;
  private BasicFileAttributes pathAttr;
  private FileTime dateCreated;

  /**
   * Instantiates CompareCreated.
   *
   * @param file the file
   * @throws IOException throws when path is not found
   */
  CompareCreated(File file) throws IOException {
    this.file = file;
    this.path = file.toPath();
    this.pathAttr = Files.readAttributes(path, BasicFileAttributes.class);
    this.dateCreated = pathAttr.creationTime();
  }

  /**
   * Compares the date created of this file and the given file.
   *
   * @param file the given file
   * @return int returns -1 if the given file was created first, 0 if they were created at the same
   *     time, or 1 if this file was created first
   */
  @Override
  public int compareTo(File file) {
    BasicFileAttributes fileAttr;
    try {
      fileAttr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (this.dateCreated.compareTo(fileAttr.creationTime()) < 0) {
      return -1; //given file created first
    } else if (this.dateCreated.compareTo(fileAttr.creationTime()) == 0) {
      return 0; //created at the same time
    } else {
      return 1; //this file created first
    }
  }
}
