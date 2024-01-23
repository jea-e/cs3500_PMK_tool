package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests methods in the Ordering Flag enum.
 */
class OrderingFlagTest {

  @Test
  public void testGetFlag() {
    assertEquals(OrderingFlag.FILENAME.getFlag(), "filename");
    assertEquals(OrderingFlag.CREATED.getFlag(), "created");
    assertEquals(OrderingFlag.MODIFIED.getFlag(), "modified");
  }
}