package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Sorts a list of files.
 */
public class SortFiles implements Sort {
  private ArrayList<File> list;

  SortFiles(ArrayList<File> list) {
    this.list = list;
  }

  /**
   * Sorts the files depending on the given ordering flag.
   *
   * @param tag the given ordering flag to sort by
   * @return ArrayList the list of files to sort
   */
  @Override
  public ArrayList<File> sortBy(String tag) {
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        FileAttribute fileAttr = new FileAttribute(list.get(i));
        if (tag.equals(OrderingFlag.FILENAME.getFlag())) {
          if (fileAttr.compareName(list.get(j)) > 0) {
            File temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
          }
        } else if (tag.equals(OrderingFlag.CREATED.getFlag())) {
          try {
            if (fileAttr.compareCreated(list.get(j)) > 0) {
              File temp = list.get(i);
              list.set(i, list.get(j));
              list.set(j, temp);
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else if (tag.equals(OrderingFlag.MODIFIED.getFlag())) {
          try {
            if (fileAttr.compareModified(list.get(j)) > 0) {
              File temp = list.get(i);
              list.set(i, list.get(j));
              list.set(j, temp);
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else {
          throw new IllegalArgumentException("Not a valid ordering flag.");
        }
      }
    }
    return list;
  }
}