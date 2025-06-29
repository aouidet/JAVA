package com.practices.practice.feature.java.gatheres;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Gatherer;

public class D_JointGatherer {

    static <T> Gatherer<T, ?, String> join(Function<? super T, ? extends CharSequence> mapper, String separator) {
        return Gatherer.ofSequential(
                StringBuilder::new,  // initialisation de l'état
                Gatherer.Integrator.ofGreedy((builder, element, _) -> {
                    if (!builder.isEmpty()) {
                        builder.append(separator);
                    }
                    builder.append(mapper.apply(element));
                    return true;
                }),
                (builder, downstream) -> {
                    if (!downstream.isRejecting() && !builder.isEmpty()) {
                        downstream.push(builder.toString());
                    }
                }
        );
    }

    public static void main(String[] args) {

        var strings = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Gatherer<String, ?, String> joining = join(Objects::toString, " | ");
        var result = strings.stream().gather(joining).toList();
        System.out.println("Result : " + result);


    }
}
