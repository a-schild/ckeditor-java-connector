/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.grid;

import com.vaadin.data.Container;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import org.aarboard.ckeditor.connector.providers.IObject;
import org.aarboard.ckeditor.connector.providers.IThumbnailProvider;

/**
 *
 * @author a.schild
 */
public class FlowLayout extends CssLayout implements Container.ItemSetChangeListener
{
    private static final long serialVersionUID = 1L;
    protected IndexedContainer   _dataSource= null;
    protected String        _colName;
    protected ColumnGenerator _colGen;
    
    public FlowLayout() 
    {
    }

    @Override
    protected String getCss(Component c) 
    {
        return "float: left";
    }

    public void setContainerDataSource(IndexedContainer dataSource)
    {
        _dataSource= dataSource;
        if (_dataSource != null)
        {
            _dataSource.addListener((Container.ItemSetChangeListener) this);
        }
        /*
        _dataSource.addListener((Container.PropertySetChangeListener)this);
        */
        refreshContainer();
    }
    
    public Container getContainerDataSource()
    {
        return _dataSource;
    }

    @Override
    public void containerItemSetChange(ItemSetChangeEvent event) 
    {
        refreshContainer();
    }

    public void containerPropertySetChange(PropertySetChangeEvent event) 
    {
        refreshContainer();
    }
    
    protected void refreshContainer()
    {
        removeAllComponents();
        if (_dataSource != null)
        {
            for (Object obj : _dataSource.getItemIds())
            {
                IObject iObj= (IObject) obj;
                Resource lIcon= null;
                if (obj instanceof IThumbnailProvider)
                {
                    IThumbnailProvider iconProvider= (IThumbnailProvider) obj;
                    lIcon= iconProvider.getThumbnail(-1, 60);
                }
                if (lIcon == null)
                {
                    lIcon= new ThemeResource("../runo/icons/64/document.png");
                }
                Component cmp= _colGen.generateCell(this, obj, _colName);
                GridItem gid= new GridItem(lIcon, iObj.getName(), cmp);
                addComponent(gid);
            }
        }
    }
    
    public void addGeneratedColumn(String colName, ColumnGenerator colGen)
    {
        _colName= colName;
        _colGen= colGen;
    }
    
    public interface ColumnGenerator
    {
        public Component generateCell(FlowLayout source, Object itemId, Object columnId);
    }
    
} 
