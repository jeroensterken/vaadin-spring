package be.faros.vaadin.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.vaadin.ui.AbstractComponent;

public class ComponentBuilder<T extends AbstractComponent> {

	private T component;

	ComponentBuilder(Class<T> impl) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<T> ctor = impl.getConstructor();
		component = ctor.newInstance();
    }

	public T build() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		return component;
	}

	/*
	 * Component methods
	 */
	public ComponentBuilder<T> setHeight(float height, int unit) {
		component.setHeight(height, unit);
		return this;
	}

	public ComponentBuilder<T> setSizeFull() {
		component.setSizeFull();
		return this;
	}
}