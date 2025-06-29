package com.practices.practice.feature.java.gatheres;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class D_FlatMapping {

    static <T, R> Gatherer<T, ?, R> flatMapping(Function<? super T, ? extends Stream<R>> flatMapper) {

        /**
         * Note : note that shoud always close the stream, and assure that our stream  is sequential
         */
        return Gatherer.of((_, element, downstream) -> {
            // Should check stream, because it's AutoCloseable
            try (var stream = flatMapper.apply(element)) {
                return stream
                        .sequential() // Need sequential here, because we don't know if the source of flatmap used is thread safe or not
                        .allMatch(downstream::push);
            }
        });
    }

    public static void main(String[] args) {
        var strings = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        Gatherer<String, ?, String> flatMapper = flatMapping(s -> s.chars().mapToObj(Character::toString));

        var result = strings.stream().gather(flatMapper).distinct().sorted().toList();

        System.out.println("Result : " + result);
    }
}
