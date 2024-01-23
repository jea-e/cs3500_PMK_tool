package cs3500.pa01;

import java.io.File;

/**
 * Compares files.
 */
public interface Compare {

  /**
   * Compares this file with the given file.
   *
   * @param file the file
   * @return int returns -1, 0, or 1 to represent returning this file or the given file
   */
  int compareTo(File file);
}
