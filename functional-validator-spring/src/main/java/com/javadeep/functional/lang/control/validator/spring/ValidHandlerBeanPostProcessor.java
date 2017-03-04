package com.javadeep.functional.lang.control.validator.spring;

import com.javadeep.functional.lang.control.validator.Annotation.ValidHandler;
import com.javadeep.functional.lang.control.validator.handler.ValidHandlerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Register the {@code Valid} annotaion information.
 *
 * @author baojie
 * @since 1.0.0
 */
@Component
@SuppressWarnings("unused")
public class ValidHandlerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        Stream.of(methods).forEach(method ->
                Optional.ofNullable(AnnotationUtils.findAnnotation(method, ValidHandler.class))
                        .ifPresent(handler -> ValidHandlerFactory.addHandler(
                                handler.value().isEmpty() ? method.getName() : handler.value(),
                                bean, method)));
        return bean;
    }
}
