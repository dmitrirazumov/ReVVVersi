package com.example.bigbo.revvversi;

import static org.junit.Assert.*;
import org.junit.Test;


public class GameTest {

    private int[][] testValues = new int[8][8];
    private ActivityGame game = new ActivityGame();

    @Test
    public void testCounter() {
        testValues[1][2] = testValues[3][2] = testValues[2][3] = testValues[3][3] = testValues[4][3] = testValues[3][4] = 1;
        testValues[1][1] = testValues[2][2] = testValues[4][4] = 2;
        int[] testCounter = new int[]{6, 3};

        assertArrayEquals(testCounter, game.counter(testValues));
        resetValues();

        testValues[3][4] = testValues[4][3] = 1;
        testValues[4][4] = testValues[3][3] = 2;
        testCounter = new int[]{2, 2};

        assertArrayEquals(testCounter, game.counter(testValues));
        resetValues();

        testValues[0][1] = testValues[1][1] = testValues[2][1] = testValues[2][3] = testValues[3][3] = testValues[3][4] =
                testValues[5][3] = testValues[5][4] = testValues[6][3] = 1;
        testValues[2][2] = testValues[4][2] = testValues[4][3] = testValues[4][4] = testValues[4][5] = 2;
        testCounter = new int[]{9, 5};

        assertArrayEquals(testCounter, game.counter(testValues));
        resetValues();

        testValues[2][1] = testValues[2][3] = testValues[3][4] = testValues[4][3] = 1;
        testValues[1][1] = testValues[2][2] = testValues[3][3] = testValues[4][4] = 2;
        testCounter = new int[]{4, 4};

        assertArrayEquals(testCounter, game.counter(testValues));
    }

    private void resetValues() {
        testValues = new int[8][8];
        game = new ActivityGame();
    }
}