package com.practices.practice.feature.java.lombdaexpression.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapExemple {

    static <T, R>  List<R> map (List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {

        List<String> names = List.of("Alice", "Bob", "Charlie");

        // ✅ Fonction anonyme : transforme un nom en sa longueur
        List<Integer> lengths = map(names, String::length);
        System.out.println(lengths); // [5, 3, 7]
    }
}
