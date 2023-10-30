package ru.vsu.OOP1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class KeyValuePair<T> {
    T key;
    String name;
    String surname;

    public KeyValuePair(T key, String name, String surname) {
        this.key = key;
        this.name = name;
        this.surname = surname;
    }
}

class BStarNode<T extends Comparable<T>> {
    List<KeyValuePair<T>> keys;
    List<BStarNode<T>> children;
    boolean isLeaf;

    public BStarNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
    }
}

public class BStarTree<T extends Comparable<T>> {
    private BStarNode<T> root;
    private final int t;

    public BStarTree(int t) {
        root = new BStarNode<>(true);
        this.t = t;
    }

    public void insert(T key, String name, String surname) {
        KeyValuePair<T> keyValue = new KeyValuePair<>(key, name, surname);
        BStarNode<T> r = root;
        if (r.keys.size() == (2 * t - 1)) {
            BStarNode<T> newRoot = new BStarNode<>(false);
            newRoot.children.add(r);
            split(newRoot, 0);
            root = newRoot;
            insertNonFull(newRoot, keyValue);
        } else {
            insertNonFull(r, keyValue);
        }
    }

    public void insertNonFull(BStarNode<T> x, KeyValuePair<T> keyValue) {
        int i = x.keys.size() - 1;
        if (x.isLeaf) {
            x.keys.add(keyValue);
            x.keys.sort(Comparator.comparing(o -> o.key));
        } else {
            while (i >= 0 && keyValue.key.compareTo(x.keys.get(i).key) < 0) {
                i--;
            }
            i++;
            BStarNode<T> child = x.children.get(i);
            if (child.keys.size() == (2 * t - 1)) {
                split(x, i);
                if (keyValue.key.compareTo(x.keys.get(i).key) > 0) {
                    i++;
                }
            }
            insertNonFull(x.children.get(i), keyValue);
        }
    }

    public void split(BStarNode<T> x, int i) {
        BStarNode<T> y = x.children.get(i);
        BStarNode<T> z = new BStarNode<>(y.isLeaf);
        x.keys.add(i, y.keys.get(t - 1));
        x.children.add(i + 1, z);
        List<KeyValuePair<T>> keysToMove = new ArrayList<>(y.keys.subList(t, 2 * t - 1));
        y.keys.subList(t - 1, 2 * t - 1).clear();
        z.keys.addAll(keysToMove);
        if (!y.isLeaf) {
            List<BStarNode<T>> childrenToMove = new ArrayList<>(y.children.subList(t, 2 * t));
            y.children.subList(t, 2 * t).clear();
            z.children.addAll(childrenToMove);
        }
    }

    public boolean search(T key) {
        return search(root, key);
    }

    public boolean search(BStarNode<T> x, T key) {
        int i = 0;
        while (i < x.keys.size() && key.compareTo(x.keys.get(i).key) > 0) {
            i++;
        }
        if (i < x.keys.size() && key.equals(x.keys.get(i).key)) {
            return true;
        } else if (x.isLeaf) {
            return false;
        } else {
            BStarNode<T> child = x.children.get(i);
            return search(child, key);
        }
    }
}
