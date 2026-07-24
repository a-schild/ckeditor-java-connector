/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.grid;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

import java.io.Serial;
import java.util.EnumMap;
import java.util.Map;

/**
 * Minimal drop-in replacement for the Vaadin 6 org.vaadin.addon.borderlayout
 * add-on, which has no Vaadin 8 release. Only the five regions the file browser
 * actually uses are supported (NORTH / SOUTH / WEST / CENTER / EAST). It is
 * backed by a 3x3 GridLayout: the middle column and middle row take all the
 * extra space, so CENTER grows while the surrounding regions keep their natural
 * size.
 *
 * @author a.schild
 */
public class BorderLayout extends CustomComponent
{
    @Serial
    private static final long serialVersionUID = 1L;

    public enum Constraint
    {
        NORTH, SOUTH, EAST, WEST, CENTER
    }

    private final GridLayout grid = new GridLayout(3, 3);
    private final Map<Constraint, Component> componentMap = new EnumMap<>(Constraint.class);

    public BorderLayout()
    {
        grid.setSizeFull();
        grid.setColumnExpandRatio(1, 1.0f);
        grid.setRowExpandRatio(1, 1.0f);
        setCompositionRoot(grid);
    }

    public void addComponent(Component c, Constraint constraint)
    {
        Component existing = componentMap.get(constraint);
        if (existing != null)
        {
            grid.removeComponent(existing);
        }
        componentMap.put(constraint, c);
        switch (constraint)
        {
            case NORTH:  grid.addComponent(c, 0, 0, 2, 0); break;
            case SOUTH:  grid.addComponent(c, 0, 2, 2, 2); break;
            case WEST:   grid.addComponent(c, 0, 1);       break;
            case CENTER: grid.addComponent(c, 1, 1);       break;
            case EAST:   grid.addComponent(c, 2, 1);       break;
        }
    }

    public void removeComponent(Component c)
    {
        grid.removeComponent(c);
        componentMap.values().remove(c);
    }
}
