package com.javadeep.functional.lang.control.bean;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Copy the property values of the given source bean into the target bean.
 *
 * @param <S> Value type of given source bean.
 * @param <T> value type of the target bean.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class BeanCopy<S, T> {

    private final S source;
    private final T target;

    /**
     * Constructs a {@code BeanCopy}
     *
     * @param source The given source.
     * @param target The target value.
     */
    private BeanCopy(S source, T target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Creates and returns a new {@code BeanCopy} instance.
     *
     * @param source The given source.
     * @param target The target value.
     * @param <S> Value type of given source bean.
     * @param <T> value type of the target bean.
     * @return a new {@code BeanCopy} instance.
     * @throws NullPointerException if {@code source} is null.
     * @throws NullPointerException if {@code target} is null.
     */
    public static <S, T> BeanCopy<S, T> of(S source, T target) {
        Objects.requireNonNull(source, "source is null");
        Objects.requireNonNull(target, "target is null");
        return new BeanCopy<>(source, target);
    }

    /**
     * Copy the property value by {@code action}.
     *
     * @param action The copy action
     * @return The {@code BeanCopy} instance itself.
     * @throws NullPointerException if {@code action} is null.
     */
    public final BeanCopy<S, T> copy(BiConsumer<? super S, ? super T> action) {
        Objects.requireNonNull(action, "action is null");
        action.accept(source, target);
        return this;
    }

    /**
     * Set the property value by {@code action}.
     *
     * @param action The set action.
     * @return The {@code BeanCopy} instance itself.
     * @throws NullPointerException if {@code action} is null.
     */
    public final BeanCopy<S, T> set(Consumer<? super T> action) {
        Objects.requireNonNull(action, "action is null");
        action.accept(target);
        return this;
    }

    /**
     * Return the target value.
     *
     * @return The target value.
     */
    public final T get() {
        return target;
    }
}
