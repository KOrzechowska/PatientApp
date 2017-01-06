package eu.telm.util;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasia on 29.12.16.
 */
public class RowFactory {

    public static HorizontalLayout createRowLayout(List<Component> components){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        for(Component component : components)
            horizontalLayout.addComponent(component);
        return horizontalLayout;
    }

    public static HorizontalLayout createRowLayout(List<Component> components, String stylename){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        for(Component component : components)
            horizontalLayout.addComponent(component);
        horizontalLayout.addStyleName(stylename);
        horizontalLayout.setSizeFull();
        return horizontalLayout;
    }

    public static List<HorizontalLayout> createRowsLayout(List<Component> components){
        List<HorizontalLayout> horizontalLayouts = new ArrayList<>();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        int i = 1;
        for(Component component : components){
            horizontalLayout.addComponent(component);
            if(i%3 == 0 && i>0) {
                horizontalLayout.setSizeFull();
                horizontalLayout.setStyleName("rowOfPatientData");
                horizontalLayouts.add(horizontalLayout);
                horizontalLayout = new HorizontalLayout();

            }
            i++;
        }
        return  horizontalLayouts;
    }

    public static VerticalLayout createVerticalRowLayout(List<Component> components){
        VerticalLayout verticalLayout = new VerticalLayout();
        for(Component component : components) {
            verticalLayout.addComponent(component);
            verticalLayout.setComponentAlignment(component, Alignment.BOTTOM_CENTER);
        }
        return verticalLayout;
    }
}
