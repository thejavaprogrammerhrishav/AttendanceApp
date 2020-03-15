/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.file.chooser.controller.FileChooserController;
import com.attendance.file.chooser.controller.util.FileChooserUtils;
import static com.attendance.notes.controller.UploadNotesController.parent;
import com.attendance.notes.model.Notes;
import com.attendance.notes.service.NotesService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.ImageUtils;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class DownloadNotesController extends View {

    @FXML
    private TextField path;

    @FXML
    private JFXButton pathbrowse;

    @FXML
    private JFXButton download;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton browse;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label size;

    @FXML
    private Label date;

    @FXML
    private Label by;

    private FXMLLoader fxml;
    private NotesService service;
    public static String parent;
    private Notes notes;
    private ExceptionDialog dialog;

    public DownloadNotesController() {
        fxml = Fxml.getDownloadNotesFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(DownloadNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (NotesService) SystemUtils.getContext().getBean("notesservice");
        
        reset();

        browse.setOnAction(this::browse);
        pathbrowse.setOnAction(this::pathbrowse);
        download.setOnAction(this::download);
        back.setOnAction(this::back);

    }

    private void browse(ActionEvent evt) {
        SystemUtils.getApplication().removeViewFactory("download notes");
        SystemUtils.getApplication().addViewFactory("download notes", () -> new NotesController(c -> load(c), 1));
        SystemUtils.getApplication().switchView("download notes");
    }

    private void pathbrowse(ActionEvent evt) {
        FileChooserController.show("directory", FileChooserUtils.ALL, c -> path.setText(c));
    }

    private void download(ActionEvent evt) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(path.getText()+"/"+notes.getFileName());
            fout.write(notes.getFile());
            fout.flush();
            fout.close();
            dialog.showSuccess(this, "Notes Downloaded Successfully");
        } catch (IOException ex) {
            Logger.getLogger(DownloadNotesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(DownloadNotesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void back(Event evt) {
        if (parent.equalsIgnoreCase("dashboard")) {
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        }
        if (parent.equalsIgnoreCase("notes dashboard")) {
            SystemUtils.getApplication().switchView(AppView.NOTES_DASHBOARD_VIEW);
        }
    }

    private void load(Notes notes) {
        this.notes = notes;
        name.setText(notes.getFileName());
        size.setText(notes.getFileSize() + " MB");
        date.setText(notes.getUploadDate());
        by.setText("By: " + notes.getFacultyName());

        String name = notes.getFileName();
        String ext = name.substring(name.lastIndexOf(".") + 1);
        switch (ext) {
            case "jpg":
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.JPG));
                break;
            case "png":
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.PNG));
                break;
            case "gif":
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.GIF));
                break;
            case "pdf":
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.PDF));
                break;
            case "docx":
            case "doc":
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.DOC));

                break;
            default:
                image.setImage(ImageUtils.getImage(ImageUtils.Icons.FILE));
        }
    }

    private void reset() {
        name.setText("");
        size.setText("");
        date.setText("");
        by.setText("");

        image.setImage(null);

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    

}
