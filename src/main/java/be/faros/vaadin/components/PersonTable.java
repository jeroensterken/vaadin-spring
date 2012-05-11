package be.faros.vaadin.components;

import java.util.ArrayList;
import java.util.Collection;

import be.faros.vaadin.event.VaadinEvent;
import be.faros.vaadin.event.VaadinEvent.Type;
import be.faros.vaadin.event.VaadinEventListener;
import be.faros.vaadin.model.Person;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

public class PersonTable extends Table implements ItemClickListener {

	VaadinEventListener listener;

	public PersonTable() {
		super();
		setCaption("My PersonsTable");
		setContainerDataSource(createContainer(new ArrayList<Person>()));
		setSelectable(true);
		addListener((ItemClickListener) this);
	}

	public void setListener(VaadinEventListener listener) {
		this.listener = listener;
	}

	private Container createContainer(Collection<Person> persons) {
		BeanItemContainer<Person> container = new BeanItemContainer<Person>(
				Person.class);
		container.addAll(persons);
		return container;
	}

	public void refresh(Collection<Person> persons) {
		setContainerDataSource(createContainer(persons));
	}

	public void itemClick(ItemClickEvent event) {
		Person person = (Person) event.getItemId();
		VaadinEvent vEvent;

		if (event.isDoubleClick()) {
			vEvent = new VaadinEvent(Type.DOUBLECLICKPERSON, person);
			listener.publishVaadinEvent(vEvent);
		}

		vEvent = new VaadinEvent(Type.SELECTPERSON, person);
		listener.publishVaadinEvent(vEvent);
	}
}
