package com.alwex.tree;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aguiet on 28/05/2015.
 */
public class QuadTreeTest {

    @Test
    public void testInsertElements() {

        long startTime;
        long endTime;
        int maxTest = 1000000;

//        startTime = System.currentTimeMillis();
//        for (int i = 0; i <= maxTest; i++) {

            QuadTree.maxItemByNode = 1;
            QuadTree.maxLevel = 2;

            QuadTree<QuadRectangle> quadTree = new QuadTree<QuadRectangle>(new QuadRectangle(0, 0, 10, 10), 0);

            QuadRectangle r1 = new QuadRectangle(1, 1, 1, 1);
            QuadRectangle r2 = new QuadRectangle(2, 2, 1, 1);
            QuadRectangle r3 = new QuadRectangle(4, 4, 1, 1);
            QuadRectangle r4 = new QuadRectangle(6, 6, 1, 1);
            QuadRectangle r5 = new QuadRectangle(4, 4, 2, 2);
            QuadRectangle r6 = new QuadRectangle(0.5f, 6.5f, 0.5f, 0.5f);

            quadTree.insert(r1, r1);
            quadTree.insert(r2, r2);
            quadTree.insert(r3, r3);
            quadTree.insert(r4, r4);
            quadTree.insert(r5, r5);
            quadTree.insert(r6, r6);

            ArrayList<QuadRectangle> list = new ArrayList<QuadRectangle>();
            quadTree.getElements(list, new QuadRectangle(2, 2, 1, 1));

            ArrayList<QuadRectangle> expected = new ArrayList<QuadRectangle>();
            expected.add(r1);
            expected.add(r5);
            expected.add(r2);
            expected.add(r3);

            assertEquals(expected, list);

            list.clear();
            quadTree.getElements(list, new QuadRectangle(4, 2, 1, 1));

            expected.clear();
            expected.add(r1);
            expected.add(r5);
            expected.add(r2);
            expected.add(r3);

            ArrayList<QuadRectangle> zoneList = new ArrayList<QuadRectangle>();
            quadTree.getAllZones(zoneList);

            assertEquals(zoneList.size(), 9);

//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("Total execution time hoho: " + (endTime - startTime) + "ms");
    }

    @Test
    public void testIntersectElementsAreInserted() {
        QuadTree.maxItemByNode = 1;
        QuadTree.maxLevel = 2;

        QuadTree<QuadRectangle> quadTree = new QuadTree<QuadRectangle>(new QuadRectangle(0, 0, 10, 10), 0);

        QuadRectangle r1 = new QuadRectangle(1, 1, 1, 1);
        QuadRectangle r2 = new QuadRectangle(2, 2, 1, 1);

        quadTree.insert(r1, r1);
        quadTree.insert(r2, r2);

        ArrayList<QuadRectangle> list = new ArrayList<QuadRectangle>();
        quadTree.getElements(list, new QuadRectangle(2, 2, 1, 1));

        assertTrue(list.size() == 2);


        QuadRectangle r3 = new QuadRectangle(11, 11, 1, 1);

        list = new ArrayList<QuadRectangle>();
        quadTree.getElements(list, new QuadRectangle(2, 2, 1, 1));
    }
}