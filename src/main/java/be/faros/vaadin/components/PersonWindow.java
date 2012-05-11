package be.faros.vaadin.components;

import be.faros.vaadin.event.VaadinEvent;
import be.faros.vaadin.event.VaadinEvent.Type;
import be.faros.vaadin.event.VaadinEventListener;
import be.faros.vaadin.model.Person;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class PersonWindow extends Window implements VaadinEventListener {

	PersonForm personForm;
	VaadinEventListener listener;

	public void setListener(VaadinEventListener listener) {
		this.listener = listener;
	}

	public PersonWindow() {
		super();
		setModal(true);
		addComponent(createForm());
		getContent().setSizeUndefined();
	}

	private Component createForm() {
		personForm = new PersonForm();
		personForm.setListener((VaadinEventListener) this);
		return personForm;
	}

	public void publishVaadinEvent(VaadinEvent businessEvent) {
		if (businessEvent.getType().equals(Type.ADDPERSON)) {
			getParent().removeWindow(this);
		}

		listener.publishVaadinEvent(businessEvent);
	}

	public void loadData(Person person) {
		personForm.loadData(person);
	}
}