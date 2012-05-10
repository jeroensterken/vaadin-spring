package be.faros.vaadin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

@Component
@Scope("session")
public class MyVaadinApplication extends Application {

    private Window mainWindow;

    @Override
    public void init() {
        mainWindow = new Window("My Vaadin Application");

        setMainWindow(mainWindow);

        Button button = new Button("Click Me");
        button.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                mainWindow.addComponent(new Label("Thank you for clicking"));
            }
        });

        mainWindow.addComponent(button);
    }
}