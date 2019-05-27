/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.providers;

/**
 * Classes implementing IObject can also implement this
 * interface, to be able to display corresponding file type icons
 * 
 * @author a.schild
 */
public interface IIconProvider 
{
    /**
     * Return the icon to be shown in the list view
     * If no icon should be shown, just return null
     * 
     * @return 
     */
    public String getListIcon();
   
}
