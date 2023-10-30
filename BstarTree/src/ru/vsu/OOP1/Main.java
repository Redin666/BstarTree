package ru.vsu.OOP1;

public class Main {
    public static void main(String[] args) {
        BStarTree<String> tree = new BStarTree<>(3); // Параметр t = 3
        KeyValuePair<String>[] keys = new KeyValuePair[]{
                new KeyValuePair<>("apple", "John", "Doe"),
                new KeyValuePair<>("banana", "Alice", "Smith"),
                new KeyValuePair<>("cherry", "Bob", "Johnson"),
        };

        System.out.println("Вставка ключей:");
        for (KeyValuePair<String> keyValuePair : keys) {
            tree.insert(keyValuePair.key, keyValuePair.name, keyValuePair.surname);
            System.out.println("Вставлен ключ: " + keyValuePair.key + " " + keyValuePair.name + " " + keyValuePair.surname);
        }

        String searchKey = "banana"; // Ключ, который вы хотите найти

        if (tree.search(searchKey)) {
            System.out.println("\nКлюч " + searchKey + " найден.");
        } else {
            System.out.println("\nКлюч " + searchKey + " не найден.");
        }
    }
}
