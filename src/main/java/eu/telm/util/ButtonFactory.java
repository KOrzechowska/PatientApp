package eu.telm.util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by kasia on 29.12.16.
 */
public class ButtonFactory {

    public static Button createButton(String nazwa, FontAwesome fontAwesome, List<Component> buttons, String styleName){
        Button button = new Button(nazwa, fontAwesome);
        button.addStyleName(styleName);
        buttons.add(button);
        return button;
    }
    public static Button createButton(String nazwa, FontAwesome fontAwesome, String styleName){
        Button button = new Button(nazwa, fontAwesome);
        button.addStyleName(styleName);
        return button;
    }

    public static void setEnabledButtons(List<Component> buttons, int nrOfFirstButtons, boolean isVisible){
        int i = 0;
        for(Component button : buttons) {
            if(i<nrOfFirstButtons)
            button.setEnabled(isVisible);
            i++;
        }
    }
}
