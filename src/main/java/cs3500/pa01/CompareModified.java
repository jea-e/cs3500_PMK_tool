package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Compares the last modified dates of files.
 */
public class CompareModified implements Compare {

  private File file;
  private Path path;
  private BasicFileAttributes pathAttr;
  private FileTime dateModified;

  /**
   * Instantiates CompareModified.
   *
   * @param file the file
   * @throws IOException throws when path is not found
   */
  CompareModified(File file) throws IOException {
    this.file = file;
    this.path = file.toPath();
    this.pathAttr = Files.readAttributes(path, BasicFileAttributes.class);
    this.dateModified = pathAttr.lastModifiedTime();
  }

  /**
   * Compares the last modified dates of this file and the given file.
   *
   * @param file the given file
   * @return int returns -1 if the given file was modified first, 0 if they were modified at the
   *     same time, or 1 if this file was modified first
   */
  @Override
  public int compareTo(File file) {
    BasicFileAttributes fileAttr;
    try {
      fileAttr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (this.dateModified.compareTo(fileAttr.lastModifiedTime()) < 0) {
      return -1; //modified first
    } else if (this.dateModified.compareTo(fileAttr.lastModifiedTime()) == 0) {
      return 0; //modified at the same time
    } else {
      return 1; //modified last
    }
  }
}