package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Everything do to with a file's attributes.
 */
public class FileAttribute {
  private File file;
  private Path path;

  /**
   * Instantiates FileAttribute
   *
   * @param file the path of this file
   */
  FileAttribute(File file) {
    this.file = file;
    this.path = file.toPath();
  }

  /**
   * Compares the date created of this file and the given file.
   *
   * @param file the given file
   * @return int returns -1 if the given file was created first, 0 if they were created at the same
   *     time, or 1 if this file was created first
   * @throws IOException when the file is not found
   */
  public int compareCreated(File file) throws IOException {
    CompareCreated created = new CompareCreated(this.file);
    return created.compareTo(file);
  }

  /**
   * Compares the last modified dates of this file and the given file.
   *
   * @param file the given file
   * @return int returns -1 if the given file was modified first, 0 if they were modified at the
   *     same time, or 1 if this file was modified first
   * @throws IOException when the file is not found
   */
  public int compareModified(File file) throws IOException {
    CompareModified modified = new CompareModified(this.file);
    return modified.compareTo(file);
  }

  /**
   * Compares this file name with the given file name lexicographically.
   *
   * @param file the given file
   * @return int returns -1 if the given file comes first, 0 if they are the same, or 1 if this
   *     file comes first
   */
  public int compareName(File file) {
    CompareFileName filename = new CompareFileName(this.file);
    return filename.compareTo(file);
  }
}