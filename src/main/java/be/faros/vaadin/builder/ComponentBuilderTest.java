package be.faros.vaadin.builder;

import java.lang.reflect.InvocationTargetException;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;

public class ComponentBuilderTest {

	public static void main(String[] args) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		 Button b = new ComponentBuilder<Button>(Button.class).setHeight(25, Sizeable.UNITS_PERCENTAGE).build();
		 System.out.println("--> height : "+b.getHeight());
	}
}