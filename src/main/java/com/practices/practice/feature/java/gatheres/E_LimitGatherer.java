package com.practices.practice.feature.java.gatheres;

import java.util.List;
import java.util.stream.Gatherer;

public class E_LimitGatherer {

    public static void main(String[] args) {


        var strings = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        Gatherer<String, ?, String> limiter = Gatherer.ofSequential(
                // utiliser les type non noté
                () -> new Object() { long count = 0L; },
                (state, element, downstream) -> {
                    state.count ++;
                    if(state.count > 3L) {
                        return false;
                    } else {
                        return downstream.push(element);
                    }
                }
        );

        var result = strings.stream().gather(limiter).toList();
        System.out.println("Result = " + result);

    }
}
