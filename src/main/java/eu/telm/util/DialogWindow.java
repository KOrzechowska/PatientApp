package eu.telm.util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasia on 06.01.17.
 */
public class DialogWindow extends Window{

    private Boolean odpowiedz;
    private List<Component> buttons;

    public DialogWindow(String name, FontAwesome icon, String message, String button1,
                                            FontAwesome button1FA, String button2, FontAwesome button2FA){
        super(name);
        setIcon(icon);
        VerticalLayout subContent = new VerticalLayout();
        subContent.setMargin(true);
        setContent(subContent);
        subContent.addComponent(new Label(message));
        buttons = new ArrayList<>();
        ButtonFactory.createButton(button1,  button1FA, buttons, "deleteButton" );
        ButtonFactory.createButton(button2,  button2FA, buttons, "searchButton" );
        subContent.addComponent(RowFactory.createRowLayout(buttons, "rowOfButtons"));
        center();

    }

    public Boolean getOdpowiedz(){
        return odpowiedz;
    }

    public List<Component> getButtons() {
        return buttons;
    }
}
