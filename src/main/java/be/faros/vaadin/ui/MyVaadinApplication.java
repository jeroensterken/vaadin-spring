package be.faros.vaadin.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.context.annotation.Scope;

import be.faros.vaadin.components.PersonForm;
import be.faros.vaadin.components.PersonTable;
import be.faros.vaadin.components.PersonWindow;
import be.faros.vaadin.event.VaadinEvent;
import be.faros.vaadin.event.VaadinEvent.Type;
import be.faros.vaadin.event.VaadinEventListener;
import be.faros.vaadin.model.Person;

import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@org.springframework.stereotype.Component
@Scope("session")
public class MyVaadinApplication extends Application implements VaadinEventListener {

    private Window mainWindow;

	private Collection<Person> persons;
	private PersonTable personTable;
	private PersonForm personForm;
	private PersonWindow personWindow;

	private MenuBar menubar;

    @Override
    public void init() {
		createInitData();
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		Window mainWindow = new Window("MyVaadinApplication",layout);
		setMainWindow(mainWindow);

		mainWindow.addComponent(createMenuBar());
		mainWindow.addComponent(createForm());
		mainWindow.addComponent(createTable());
    }

	private void createInitData() {
		persons = new ArrayList<Person>();
		Person person1 = new Person("Vanderauwera", "Jos", new Date(286239600));
		Person person2 = new Person("Selie", "Peter", new Date(286239600));
		persons.add(person1);
		persons.add(person2);
	}

	private Component createMenuBar() {
		menubar = new MenuBar();
		final MenuBar.MenuItem file = menubar.addItem("File", null);
		file.addItem("Create Person", new Command() {
			public void menuSelected(MenuItem selectedItem) {
				MyVaadinApplication.this.openPersonAddWindow();
			}
		});
		return menubar;
	}

	private Component createForm() {
		personForm =new PersonForm();
		personForm.setListener(this);
		return personForm;
	}

	private Component createTable() {
		personTable = new PersonTable();
		personTable.refresh(persons);
		personTable.setListener(this);
		return personTable;
	}

	private void openPersonAddWindow() {
		personWindow = new PersonWindow();
		personWindow.setListener(this);
		getMainWindow().addWindow(personWindow);
	}

	public void publishVaadinEvent(VaadinEvent vaadinEvent) {
		if (vaadinEvent.getType().equals(Type.ADDPERSON)) {
			addPerson((Person)vaadinEvent.getData());
		} else if(vaadinEvent.getType().equals(Type.SELECTPERSON)) {
			selectPerson((Person)vaadinEvent.getData());
		} else if(vaadinEvent.getType().equals(Type.DOUBLECLICKPERSON)) {
			doubleClickPerson((Person)vaadinEvent.getData());
		}
	}

	private void doubleClickPerson(Person data) {
		openPersonAddWindow();
		personWindow.loadData(data);
	}

	private void selectPerson(Person data) {
		personForm.loadData(data);
	}

	private void addPerson(Person person) {
		persons.add(person);
		personTable.refresh(persons);
	}
}