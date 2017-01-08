package com.alwex.tree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aguiet on 28/05/2015.
 */
public class QuadRectangleTest {

    @Test
    public void testContains() {
        QuadRectangle r1 = new QuadRectangle(0, 0, 10, 10);
        QuadRectangle r2 = new QuadRectangle(0, 0, 10, 10);

        // contains
        assertTrue(r1.contains(r2));

        // dont contains
        r2.width = 11;
        assertFalse(r1.contains(r2));
    }
}