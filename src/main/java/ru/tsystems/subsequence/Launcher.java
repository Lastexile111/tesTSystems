package ru.tsystems.subsequence;

import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        Subsequence s = new SubsequenceImpl();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));

        System.out.println(b);
    }
}
