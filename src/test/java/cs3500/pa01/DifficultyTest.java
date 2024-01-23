package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test methods in Difficulty enum.
 */
class DifficultyTest {

  @Test
  public void testGetDifficultType() {
    assertEquals("Hard: ", Difficulty.HARD.getDifficultType());
    assertEquals("Easy: ", Difficulty.EASY.getDifficultType());
  }

}