package be.faros.vaadin.components;

import be.faros.vaadin.event.VaadinEvent;
import be.faros.vaadin.event.VaadinEvent.Type;
import be.faros.vaadin.event.VaadinEventListener;
import be.faros.vaadin.model.Person;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class PersonForm extends Form implements ClickListener {

	enum Buttons {
		SAVE, RESET
	};

	Person person;
	VaadinEventListener listener;

	public void setListener(VaadinEventListener listener) {
		this.listener = listener;
	}

	public PersonForm() {
		super();
		person = new Person();
		BeanItem<Person> beanItem = new BeanItem<Person>(person);

		// get rid of null
		setFormFieldFactory(new FormFieldFactory() {
			public Field createField(Item item, Object propertyId,
					Component uiContext) {
				Field field = DefaultFieldFactory.get().createField(item,
						propertyId, uiContext);
				if (field instanceof TextField) {
					((TextField) field).setNullRepresentation("");
				}
				return field;
			}
		});

		setItemDataSource(beanItem);

		// add button
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		Button save = new Button("SAVE");
		save.addListener((ClickListener) this);
		save.setData(Buttons.SAVE);
		footer.addComponent(save);
		Button reset = new Button("RESET");
		reset.addListener((ClickListener) this);
		reset.setData(Buttons.RESET);
		footer.addComponent(reset);
		setFooter(footer);

	}

	public void buttonClick(ClickEvent event) {
		if (event.getButton().getData().equals(Buttons.SAVE)) {
			VaadinEvent bEvent = new VaadinEvent(Type.ADDPERSON, person);
			listener.publishVaadinEvent(bEvent);
		} else if (event.getButton().getData().equals(Buttons.RESET)) {
			loadData(new Person());
		}
	}

	public void loadData(Person person) {
		this.person = person;
		BeanItem<Person> beanItem = new BeanItem<Person>(person);
		setItemDataSource(beanItem);
	}
}