package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * FileEditor class to edit/write the final output file
 */
public class FileEditor {
  private File file;
  private Path path;

  FileEditor(File file) {
    this.file = file;
    this.path = file.toPath();
  }

  /**
   * Determines if the line is enclosed in "[[" and "]]".
   *
   * @param text the line to be tested
   * @return Boolean whether the line is enclosed in brackets
   */
  private boolean brackets(String text) {
    int startIndex = 0;
    while (startIndex < text.length()) {
      int openBracketIndex = text.indexOf("[[", startIndex);
      if (openBracketIndex == -1) {
        break; // No more opening brackets found, exit the loop
      }
      int closeBracketIndex = text.indexOf("]]", openBracketIndex);
      if (closeBracketIndex == -1) {
        break; // No corresponding closing bracket found, exit the loop
      }
      startIndex =
          closeBracketIndex + 2; // Move the startIndex to the position after the closing bracket
      return true;
    }
    return false;
  }

  /**
   * Find the text enclosed in brackets.
   *
   * @param text the strings to be tested
   * @return a list of strings that are enclosed in brackets
   */
  private List<String> findEnclosedTexts(String text) {
    List<String> enclosedTexts = new ArrayList<>();

    int startIndex = 0;
    while (startIndex < text.length()) {
      int openBracketIndex = text.indexOf("[[", startIndex);
      if (openBracketIndex == -1) {
        break; // No more opening brackets found, exit the loop
      }

      int closeBracketIndex = text.indexOf("]]", openBracketIndex);
      if (closeBracketIndex == -1) {
        break; // No corresponding closing bracket found, exit the loop
      }

      String enclosedText = text.substring(openBracketIndex + 2, closeBracketIndex);
      enclosedTexts.add(enclosedText);

      startIndex =
          closeBracketIndex + 2; // Move the startIndex to the position after the closing bracket
    }

    return enclosedTexts;
  }

  /**
   * Determines if the line is a header.
   *
   * @param text the line to be tested
   * @return Boolean whether the line is a header
   */
  private Boolean header(String text) {
    return text.startsWith("#");
  }

  /**
   * Determines if the string contains ":::"
   *
   * @param text the line to be tested
   * @return Boolean whether the string contains ":::"
   */
  private Boolean containsColon(String text) {
    return text.contains(":::");
  }

  /**
   * Returns a list of important lines.
   *
   * @param lines the lines
   * @return List the lines that are important
   */
  private List<String> getImportantLines(List<String> lines) {
    List<String> importantLines = new ArrayList<>();
    boolean previousLineBracket = false;

    for (String line : lines) {
      line = line.trim();
      if (this.header(line)) {
        if (previousLineBracket) {
          importantLines.add("");
        }
        importantLines.add(line);
        previousLineBracket = false;
      } else if (this.brackets(line)) {
        List<String> bracketText = findEnclosedTexts(line);
        for (String text : bracketText) {
          if (text.contains(":::")) {
            importantLines.add(text);
          } else {
            importantLines.add("- " + text);
          }
          previousLineBracket = true;
        }
      }
    }
    return importantLines;
  }

  /**
   * Gets the lines that have ":::".
   *
   * @param lines the list of lines to test
   * @return List the list of lines that have ":::"
   */
  private List<String> getColon(List<String> lines) {
    List<String> colonLines = new ArrayList<>();
    for (String line : lines) {
      if (this.containsColon(line)) {
        colonLines.add(line);
      }
    }
    return colonLines;
  }

  /**
   * Gets the lines that do not have ":::".
   *
   * @param lines the list of lines to test
   * @return List the list of lines without ":::"
   */
  private List<String> removeColon(List<String> lines) {
    List<String> noColonLines = new ArrayList<>();
    for (String line : lines) {
      if (!this.containsColon(line)) {
        noColonLines.add(line);
      }
    }
    return noColonLines;
  }

  /**
   * Writes the file with only text enclosed in "[[" and "]]" and headers.
   *
   * @param listFiles the list of files to write and merge
   * @return File the file to output the contents
   * @throws IOException throws when file is not found
   */
  public File writeFile(ArrayList<File> listFiles) throws IOException {
    for (File file : listFiles) {
      List<String> lines = Files.readAllLines(file.toPath());
      List<String> importantLines1;
      List<String> importantLines2;
      importantLines1 = this.getImportantLines(lines);
      importantLines2 = this.removeColon(importantLines1);
      for (String text : importantLines2) {
        byte[] data = text.concat("\n").getBytes();
        Files.write(this.path, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
      }
    }
    return this.file;
  }

  /**
   * Writes the file with only questions.
   *
   * @param listFiles the list of files to write and merge
   * @return File the file to output the contents
   * @throws IOException throws when file is not found
   */
  public File writeSr(ArrayList<File> listFiles) throws IOException {
    String mdPath = this.path.toString();
    String srPath = mdPath.replace(".md", ".sr");
    Path pathS = Path.of(srPath);
    String difficulty = "Hard: ";
    for (File file : listFiles) {
      List<String> lines = Files.readAllLines(file.toPath());
      List<String> importantLines1;
      List<String> importantLines2;
      importantLines1 = this.getImportantLines(lines);
      importantLines2 = this.getColon(importantLines1);
      for (String text : importantLines2) {
        byte[] data = difficulty.concat(text.concat("\n")).getBytes();
        Files.write(pathS, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
      }
    }
    return pathS.toFile();
  }
}