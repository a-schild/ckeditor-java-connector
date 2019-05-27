/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.providers;

import com.vaadin.terminal.Resource;

/**
 *
 * @author a.schild
 */
public interface IThumbnailProvider 
{
    /**
     * Return the icon to be shown in the list view
     * If no icon should be shown, just return null
     * 
     * @param width     Width of thumbnail, can be -1 if it should be scaled 
     * @param height    Height of thumbnail, can be -1 if it should be scaled 
     * @return 
     */
    public Resource getThumbnail(int width, int height) ;
    
}
