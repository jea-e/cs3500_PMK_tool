package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      ArrayList<File> filePaths = new ArrayList<>(); //new ArrayList<Path>
      FileReader fileReader = new FileReader(filePaths); //ArrayList<Path> -> new FileReader

      Path pathDir = Path.of(args[0]); //command line argument (path of directory or file)
      String orderingFlag = args[1]; //how to order the files
      Path pathOutput = Path.of(args[2]); //command line argument (path of output file)
      System.out.println(pathOutput);
      Files.walkFileTree(pathDir, fileReader); //start reading -> add .md files to list
      ArrayList<File> mdList = fileReader.getList(); //gets list of the path of .md files

      SortFiles unsortedList = new SortFiles(mdList);
      ArrayList<File> sortedList = unsortedList.sortBy(orderingFlag);
      FileEditor editor = new FileEditor(pathOutput.toFile());
      editor.writeFile(sortedList); //Output .md StudyGuide file
      editor.writeSr(sortedList); //Output .sr question file

    } else {
      Controller control = new Controller(new InputStreamReader(System.in), System.out);
      control.startStudySession();
    }
  }
}
