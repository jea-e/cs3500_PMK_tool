package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Filereader class that implements FileVisitor
 * Reads the files and determines what to do for each file/dir
 */
public class FileReader implements FileVisitor<Path> {

  private ArrayList<File> list;

  FileReader(ArrayList<File> l) {
    list = l;
  }

  /**
   * Returns the list of paths
   *
   * @return ArrayList the list of paths to be returned
   */
  public ArrayList<File> getList() {
    return list;
  }

  /**
   * Called everytime the file system walker encounters a file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return directive on how to process current item's siblings
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {

    String fileName = file.getFileName().toString();
    if (fileName.endsWith(".md")) {
      list.add(file.toFile());
    }

    return CONTINUE;
  }

  /**
   * What do do after processing all items in a directory
   *
   * @param dir a reference to the directory
   * @param exec {@code null} if the iteration of the directory copletes without
   *                         an error; otherwise the I/O exception that caused the
   *                         iteration of the directory to complete prematurely
   * @return in all cases, continue processing the sibling and child items of current item
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.format("Finishing Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * What to do at the beginning of processing a directory
   *
   * @param dir a reference to the directory
   * @param attrs the directory's basic attributes
   * @return a directive to continue processing siblings and children of current item
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) {
    System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * Called when a file cannot be visited for some undetermined reason
   *
   * @param file a reference to the file
   * @param exc the I/O exception that prevented the file from being visited
   * @return error
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(exc);
    return CONTINUE;
  }
}



