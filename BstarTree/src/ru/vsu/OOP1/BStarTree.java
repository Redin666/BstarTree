package ru.vsu.OOP1;

import java.util.ArrayList;
import java.util.List;

class BStarNode {
    List<Integer> keys;
    List<BStarNode> children;
    boolean isLeaf;

    public BStarNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
    }
}

public class BStarTree {
    private BStarNode root;
    private int t; // Параметр t, где t >= 2

    public BStarTree(int t) {
        root = new BStarNode(true);
        this.t = t;
    }

    public void insert(int key) {
        BStarNode r = root;
        if (r.keys.size() == (2 * t - 1)) {
            BStarNode newRoot = new BStarNode(false);
            newRoot.children.add(r);
            split(newRoot, 0);
            root = newRoot;
            insertNonFull(newRoot, key);
        } else {
            insertNonFull(r, key);
        }
    }

    public void insertNonFull(BStarNode x, int key) {
        int i = x.keys.size() - 1;
        if (x.isLeaf) {
            x.keys.add(key);
            x.keys.sort(null);
        } else {
            while (i >= 0 && key < x.keys.get(i)) {
                i--;
            }
            i++;
            BStarNode child = x.children.get(i);
            if (child.keys.size() == (2 * t - 1)) {
                split(x, i);
                if (key > x.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(x.children.get(i), key);
        }
    }

    public void split(BStarNode x, int i) {
        BStarNode y = x.children.get(i);
        BStarNode z = new BStarNode(y.isLeaf);
        x.keys.add(i, y.keys.get(t - 1));
        x.children.add(i + 1, z);
        List<Integer> keysToMove = new ArrayList<>(y.keys.subList(t, 2 * t - 1));
        y.keys.subList(t - 1, 2 * t - 1).clear();
        z.keys.addAll(keysToMove);
        if (!y.isLeaf) {
            List<BStarNode> childrenToMove = new ArrayList<>(y.children.subList(t, 2 * t));
            y.children.subList(t, 2 * t).clear();
            z.children.addAll(childrenToMove);
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    public boolean search(BStarNode x, int key) {
        int i = 0;
        while (i < x.keys.size() && key > x.keys.get(i)) {
            i++;
        }
        if (i < x.keys.size() && key == x.keys.get(i)) {
            return true;
        } else if (x.isLeaf) {
            return false;
        } else {
            BStarNode child = x.children.get(i);
            return search(child, key);
        }
    }

}
