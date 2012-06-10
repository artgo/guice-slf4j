package com.google.inject.logging;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class Slf4jGuiceTypeListener implements TypeListener {

	public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
		Class<? super I> cls = type.getRawType();
		
		do {
			for (final Field field : cls.getDeclaredFields()) {
				if ((field.getType() == Logger.class) && field.isAnnotationPresent(InjectLogger.class)) {
					encounter.register(new Slf4jGuiceMembersInjector<I>(field));
				}
			}
			
			// Got through all parents as well
			cls = cls.getSuperclass();
		} while (cls != null);
	}
}
