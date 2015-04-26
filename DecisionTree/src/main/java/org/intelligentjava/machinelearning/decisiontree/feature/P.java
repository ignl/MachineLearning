package org.intelligentjava.machinelearning.decisiontree.feature;

import java.util.function.Predicate;

/**
 * Convenience class for various predicates.
 * 
 * @author Ignas
 *
 */
public class P {
    
    public static <T> Predicate<T> isEqual(T value) {
        return p -> p.equals(value);
    }

    public static Predicate<Double> moreThanD(double value) {
        return p -> p > value;
    }

    public static Predicate<Double> lessThanD(double value) {
        return p -> p < value;
    }

    public static Predicate<Integer> moreThan(int value) {
        return p -> p > value;
    }

    public static Predicate<Integer> lessThan(int value) {
        return p -> p < value;
    }

    public static Predicate<Integer> between(int from, int to) {
        return moreThan(from).and(lessThan(to));
    }

    public static Predicate<Double> betweenD(double from, double to) {
        return moreThanD(from).and(lessThanD(to));
    }

    public static Predicate<String> startsWith(String prefix) {
        return p -> p != null && p.startsWith(prefix);
    }
    
}
