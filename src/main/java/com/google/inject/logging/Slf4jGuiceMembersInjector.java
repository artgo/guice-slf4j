package com.google.inject.logging;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;

public class Slf4jGuiceMembersInjector<T> implements MembersInjector<T> {
	private final Field field;
	private final Logger logger;

	public Slf4jGuiceMembersInjector(final Field field) {
		this.field = field;
		this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
		field.setAccessible(true);
	}

	public void injectMembers(final T instance) {
		try {
			field.set(instance, logger);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
