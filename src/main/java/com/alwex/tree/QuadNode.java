package com.alwex.tree;

/**
 * Created by samsung on 28/05/2015.
 */
public class QuadNode<T> {
    QuadRectangle r;
    T element;

    QuadNode(QuadRectangle r, T element) {
        this.r = r;
        this.element = element;
    }

    @Override
    public String toString() {
        return r.toString();
    }
}
