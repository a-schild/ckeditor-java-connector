/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.grid;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author a.schild
 */
public class GridItem extends GridLayout
{
    private static final long serialVersionUID = 1L;
    public final float ICON_HEIGHT= 10.0f;
    public final float GRID_HEIGHT= ICON_HEIGHT+3.2f;

    public GridItem(Resource rsc, String title, Component component) 
    {
        // 1col, 2 rows
        super(1, 2);
        addStyleName("GridItem");
        setMargin(true);
        setHeight(GRID_HEIGHT, UNITS_EM);
        
        Embedded e = new Embedded(null,
                        rsc);
        e.setAlternateText(title);
        e.setDescription(title);
        addComponent(e);
        setComponentAlignment(e, Alignment.MIDDLE_CENTER);

        e.setHeight(ICON_HEIGHT, UNITS_EM);
        HorizontalLayout hl= new HorizontalLayout();
        hl.addComponent(component);
        addComponent(hl);
        setComponentAlignment(hl, Alignment.MIDDLE_CENTER);
    }
   
    
}
