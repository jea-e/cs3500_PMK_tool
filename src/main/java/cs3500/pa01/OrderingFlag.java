package cs3500.pa01;

/**
 * Ordering flags.
 */
public enum OrderingFlag {
  FILENAME("filename"),
  CREATED("created"),
  MODIFIED("modified");

  private String flag;

  OrderingFlag(String flag) {
    this.flag = flag;
  }

  /**
   * Returns the flag as a String.
   *
   * @return String the flag
   */
  public String getFlag() {
    return this.flag;
  }

}
