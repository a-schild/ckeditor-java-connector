/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aarboard.ckeditor.connector.upload;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.aarboard.ckeditor.connector.VaadinBrowser;
import org.aarboard.ckeditor.connector.providers.IFolder;
import org.aarboard.ckeditor.connector.providers.ProviderStatus;

/**
 *
 * @author a.schild
 */
public class SimpleFileUploader extends CustomComponent
                        implements Upload.SucceededListener,
                                   Upload.FailedListener,
                                   Upload.Receiver {

    protected final IFolder   _parentFolder;
    protected final VaadinBrowser    _mainWindow;
    protected File _uploadFile;

    public SimpleFileUploader(VaadinBrowser mainWindow, IFolder parent)
    {
        _mainWindow= mainWindow;
        _parentFolder= parent;
        final Upload upload = new Upload("Dateiupload", this);

        // Use a custom button caption instead of plain "Upload".
        upload.setButtonCaption("Upload");

        // Listen for events regarding the success of upload.
        upload.addListener((Upload.SucceededListener) this);
        upload.addListener((Upload.FailedListener) this);

        setCompositionRoot(upload);
    }
    
    
    protected void handleFile(File file, String fileName, String mimeType, long length)
    {
        ProviderStatus status= _parentFolder.handleUpload(file, fileName, mimeType, length);
        if (status.getStatus() == ProviderStatus.Status.FILE_RENAMED)
        {
            _mainWindow.showWarningNotification("A file with name '"+fileName
                    +"' already existed.<br />The new file has been renamed to '"+status.getMessage()+"'");
        }
        else if(status.getStatus() == ProviderStatus.Status.OPERATION_OK)
        {
            // Not needed _mainWindow.showInfoNotification("File '"+fileName+"' uploaded");
        }
        _mainWindow.updateFolder();
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        handleFile(_uploadFile, event.getFilename(), event.getMIMEType(), event.getLength());
        _uploadFile= null;
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
            _mainWindow.showErrorNotification("Upload failed");
            _uploadFile= null;
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType)
    {
        FileOutputStream fos = null; // Output stream to write to
        try 
        {
            _uploadFile = File.createTempFile("aarcat_up", "image");
            // Open the file for writing.
            fos = new FileOutputStream(_uploadFile);
        } catch (final IOException e) {
            // Error while opening the file. Not reported here.
            e.printStackTrace();
            return null;
        }

        return fos; // Return the output stream to write to
    }
    
}
