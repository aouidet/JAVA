package com.practices.practice.feature.java.gatheres;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Gatherer;

public class SimpleGatherer {

    // exemple de simuler une fonction de mapping
    static <T, R> Gatherer<T, ?, R> mapping(Function<? super T, ? extends R> mapper) {
        return Gatherer.of((_, element, downstream) -> downstream.push(mapper.apply(element)));
    }

    //ofGreedy c'est un intégrateur qui ne decide pas de lui meme
    // exemple simuler un filter
    static <T> Gatherer<T, ?, T> filtering(Predicate<? super T> filter) {
        return Gatherer.of(Gatherer.Integrator.ofGreedy((_, element, downstream) -> {
            if (filter.test(element)) {
                return downstream.push(element);
            } else {
                return true; // true pour faire passer les suivant element passser par gatherer
            }
        }));


    }


    public static void main(String[] args) {

        var strings = List.of("one", "two", "three", "four", "five");

        Gatherer<String, ?, String> agatherer = Gatherer.of((_, element, downstream) -> downstream.push(element));

        Gatherer<String, ?, String> mapper = mapping(String::toUpperCase);

        Gatherer<String, ?, String> filter = filtering(s -> s.length() > 3);

        var result = strings.stream().gather(mapper).toList();
        var resultFilter = strings.stream().gather(filter).toList();


        System.out.println("Result = " + result);
        System.out.println("result Filter = " + resultFilter);
    }
}
