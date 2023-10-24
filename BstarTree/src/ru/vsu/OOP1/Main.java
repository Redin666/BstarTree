package ru.vsu.OOP1;

public class Main {
    public static void main(String[] args) {
        BStarTree tree = new BStarTree(3); // Параметр t = 3
        int[] keys = {3, 7, 1, 4, 6, 8, 2, 5, 9, 0, 0}; // Количество ключей

        System.out.println("Вставка ключей:");
        for (int key : keys) {
            tree.insert(key);
            System.out.println("Вставлен ключ: " + key);
        }


        int searchKey = 0; // ключ кторый вы ищите
        if (tree.search(searchKey)) {
            System.out.println("\nКлюч " + searchKey + " найден.");
        } else {
            System.out.println("\nКлюч " + searchKey + " не найден.");
        }
    }
}
