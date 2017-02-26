package com.javadeep.functional.lang.data;

import java.util.Objects;

/**
 * A tuple of two elements which can be seen as cartesian product of two components.
 *
 * @param <T1> type of the 1st element
 * @param <T2> type of the 2nd element
 *
 * @author baojie
 * @version 1.0.0
 */
public final class Tuple2<T1, T2> {

    /**
     * The 1st element of this tuple.
     */
    private final T1 t1;

    /**
     * The 2nd element of this tuple.
     */
    private final T2 t2;

    /**
     * Constructs a tuple of two elements.
     *
     * @param t1 the 1st element
     * @param t2 the 2nd element
     */
    private Tuple2(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    /**
     *
     * Constructs a tuple of two elements.
     *
     * @param t1 t1 the 1st element
     * @param t2 the 2nd element
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @return The {@code Tuple2} instance.
     */
    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    /**
     * Return the 1st element.
     *
     * @return The 1st element.
     */
    public final T1 t1() {
        return t1;
    }

    /**
     * Return th3 2nd element.
     *
     * @return The 2nd element.
     */
    public final T2 t2() {
        return t2;
    }


    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof Tuple2 && Objects.equals(t1, ((Tuple2<?, ?>) obj).t1)
                    && Objects.equals(t2, ((Tuple2<?, ?>) obj).t2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1, t2);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", t1, t2);
    }
}
