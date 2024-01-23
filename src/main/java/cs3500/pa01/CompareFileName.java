package cs3500.pa01;

import java.io.File;
import java.nio.file.Path;

/**
 * Compares the file names of files.
 */
public class CompareFileName implements Compare {
  private File file;
  private Path path;
  private String fileName;

  /**
   * Instantiates CompareFileName.
   *
   * @param file the file
   */
  CompareFileName(File file) {
    this.file = file;
    this.path = file.toPath();
    this.fileName = path.getFileName().toString();
  }

  /**
   * Compares this file name with the given file name lexicographically.
   *
   * @param file the given file
   * @return int returns -1 if the given file comes first, 0 if they are the same, or 1 if this
   *     file comes first
   */
  @Override
  public int compareTo(File file) {
    if (this.fileName.compareTo(file.getName()) < 0) {
      return 1;
    } else if (this.fileName.compareTo(file.getName()) == 0) {
      return 0;
    } else {
      return -1;
    }
  }
}

