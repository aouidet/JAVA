package com.practices.practice.feature.java.gatheres;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Gatherer;

public class F_DistinctGatherer {

    public static void main(String[] args) {
        var ints = new Random(312L).ints(1_000_000, 1, 10).boxed().toList();
        Gatherer<Integer, ?, Integer> distinct = Gatherer.ofSequential(
                () -> new Object() {
            final HashSet<Integer> set = new HashSet<>();
        }, Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
            if (state.set.add(element)) {
                return downstream.push(element);
            } else {
                return true;
            }
        }));

        /**
         * should consult all elements from stream before apply sort
         */
        // finisher
        Gatherer<Integer, ?, Integer> distinctAndSort = Gatherer.ofSequential(
                () -> new Object() {
                    final Set<Integer> set = new TreeSet<>();
                },
                Gatherer.Integrator.ofGreedy((state, element, _) -> {
                    state.set.add(element);
                    return true;
                }),
                // finisher
                (state, downstream) -> {
            // state.set.stream().takeWhile(_ -> !downstream.isRejecting()).forEach(downstream::push);
             // same result
             boolean b = state.set.stream().allMatch(downstream::push);
                });

        var result = ints.stream().gather(distinct).toList();
        var resultSorted = ints.stream().gather(distinctAndSort).toList();

        System.out.println("Rsult : " + result);
        System.out.println("Result sorted elements : " + resultSorted);
    }
}
