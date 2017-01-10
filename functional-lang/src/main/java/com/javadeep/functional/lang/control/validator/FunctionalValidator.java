package com.javadeep.functional.lang.control.validator;

/**
 * A functional Chained call validator
 *
 * @author baojie
 * @since 1.0.0
 */
public final class FunctionalValidator<T> {

    private final T element;
    private boolean isFailFast = true;

    /**
     * Constructs a {@code FunctionalValidator}.
     *
     * @param element The element to be checked.
     */
    private FunctionalValidator(T element) {
        this.element = element;
    }

    /**
     * Constructs a {@code FunctionalValidator} from an element.
     *
     * @param element The element to be checked
     * @param <T> The type of element to be checked
     * @return a new {@code FunctionalValidator} instance.
     */
    public static <T> FunctionalValidator checkFrom(T element) {

        return new FunctionalValidator(element);
    }

    /**
     * The method to prevent the following validators from getting validated if any validator fails.
     *
     * @return the instance of {@code FunctionalValidator} itself.
     */
    public final FunctionalValidator failFast() {
        isFailFast = true;
        return this;
    }

    /**
     * The method to ignore the failures so that all the validators will work in order.
     *
     * @return the instance of {@code FunctionalValidator} itself.
     */
    public final FunctionalValidator failOver() {
        isFailFast = false;
        return this;
    }
}
