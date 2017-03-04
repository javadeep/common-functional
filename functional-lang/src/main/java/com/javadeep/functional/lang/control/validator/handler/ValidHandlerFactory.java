package com.javadeep.functional.lang.control.validator.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The factory of {@code ValidHandler}
 *
 * @author baojie
 * @since 1.0.0
 */
public class ValidHandlerFactory {

    private static final Map<String, ValidHandlerBean> HANDLER_MAP = new HashMap<>();

    /**
     * Register the handler
     *
     * @param handlerName handler name.
     * @param bean handler bean.
     * @param method handler method.
     * @throws NullPointerException if {@code handlerName} or {@code bean} or {@code method} is null.
     */
    public synchronized static void addHandler(String handlerName, Object bean, Method method) {
        Objects.requireNonNull(handlerName, "handlername is null");
        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(method, "method is null");
        String key = formatKey(handlerName, bean.getClass());
        HANDLER_MAP.put(key, ValidHandlerBean.of(bean, method));
    }

    /**
     * Get handler from handler name and the {@code Class}.
     *
     * @param handlerName The handler name.
     * @param clazz The {@code class}
     * @return The {@code ValidHandlerBean}.
     * @throws NullPointerException if {@code handlerName} or {@code clazz} is null.
     */
    public static Optional<ValidHandlerBean> getHandler(String handlerName, Class<?> clazz) {
        Objects.requireNonNull(handlerName, "handlerName is null");
        Objects.requireNonNull(clazz, "clazz is null");
        String key = formatKey(handlerName, clazz);
        return Optional.ofNullable(HANDLER_MAP.get(key));
    }

    private static String formatKey(String handlerName, Class<?> clazz) {
        return String.format("%s-%s", handlerName, clazz.getName());
    }
}

