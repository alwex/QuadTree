package com.alwex.tree;

import java.util.ArrayList;

/**
 * Created by alwex on 28/05/2015.
 */
public class QuadTree<T> {

    // the current nodes
    ArrayList<QuadNode<T>> nodes;

    // current rectangle zone
    private QuadRectangle zone;

    // GLOBAL CONFIGRATION
    // if this is reached,
    // the zone is subdivised
    public static int maxItemByNode = 5;
    public static int maxLevel = 10;

    int level;

    // the four sub regions,
    // may be null if not needed
    QuadTree<T>[] regions;

    public static final int REGION_SELF = -1;
    public static final int REGION_NW = 0;
    public static final int REGION_NE = 1;
    public static final int REGION_SW = 2;
    public static final int REGION_SE = 3;

    public QuadTree(QuadRectangle definition, int level) {
        zone = definition;
        nodes = new ArrayList<QuadNode<T>>();
        this.level = level;
    }

    protected QuadRectangle getZone() {
        return this.zone;
    }

    private int findRegion(QuadRectangle r) {
        int region = REGION_SELF;
        if (nodes.size() >= maxItemByNode && this.level < maxLevel) {
            if (regions == null) {
                // then create the subregions
                this.split();
            }

            if (regions[REGION_NW].getZone().contains(r)) {
                region = REGION_NW;
            } else if (regions[REGION_NE].getZone().contains(r)) {
                region = REGION_NE;
            } else if (regions[REGION_SW].getZone().contains(r)) {
                region = REGION_SW;
            } else if (regions[REGION_SE].getZone().contains(r)) {
                region = REGION_SE;
            }
        }

        return region;
    }

    private void split() {

        regions = new QuadTree[4];

        float newWidth = zone.width / 2;
        float newHeight = zone.height / 2;
        int newLevel = level + 1;

        regions[REGION_NW] = new QuadTree<T>(new QuadRectangle(
                zone.x,
                zone.y + zone.height / 2,
                newWidth,
                newHeight
        ), newLevel);

        regions[REGION_NE] = new QuadTree<T>(new QuadRectangle(
                zone.x + zone.width / 2,
                zone.y + zone.height / 2,
                newWidth,
                newHeight
        ), newLevel);

        regions[REGION_SW] = new QuadTree<T>(new QuadRectangle(
                zone.x,
                zone.y,
                newWidth,
                newHeight
        ), newLevel);

        regions[REGION_SE] = new QuadTree<T>(new QuadRectangle(
                zone.x + zone.width / 2,
                zone.y,
                newWidth,
                newHeight
        ), newLevel);
    }

    public void insert(QuadRectangle r, T element) {
        int region = this.findRegion(r);
        if (region == REGION_SELF || this.level == maxLevel) {
            nodes.add(new QuadNode<T>(r, element));
            return;
        } else {
            regions[region].insert(r, element);
        }

        if (nodes.size() >= maxItemByNode && this.level < maxLevel) {
            // redispatch the elements
            ArrayList<QuadNode<T>> tempNodes = new ArrayList<QuadNode<T>>();
            int length = nodes.size();
            for (int i = 0; i < length; i++) {
                tempNodes.add(nodes.get(i));

            }
            nodes.clear();

            for (QuadNode<T> node : tempNodes) {
                this.insert(node.r, node.element);
            }
        }
    }

    public ArrayList<T> getElements(ArrayList<T> list, QuadRectangle r) {
        int region = this.findRegion(r);

        int length = nodes.size();
        for (int i = 0; i < length; i++) {
            list.add(nodes.get(i).element);
        }

        if (region != REGION_SELF) {
            regions[region].getElements(list, r);
        } else {
            getAllElements(list, true);
        }

        return list;
    }

    public ArrayList<T> getAllElements(ArrayList<T> list, boolean firstCall) {
        if (regions != null) {
            regions[REGION_NW].getAllElements(list, false);
            regions[REGION_NE].getAllElements(list, false);
            regions[REGION_SW].getAllElements(list, false);
            regions[REGION_SE].getAllElements(list, false);
        }

        if (!firstCall) {
            int length = nodes.size();
            for (int i = 0; i < length; i++) {
                list.add(nodes.get(i).element);
            }
        }

        return list;
    }

    public void getAllZones(ArrayList<QuadRectangle> list) {
        list.add(this.zone);
        if (regions != null) {
            regions[REGION_NW].getAllZones(list);
            regions[REGION_NE].getAllZones(list);
            regions[REGION_SW].getAllZones(list);
            regions[REGION_SE].getAllZones(list);
        }
    }
}
