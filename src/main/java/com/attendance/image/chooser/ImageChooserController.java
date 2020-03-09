/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.image.chooser;

import com.attendance.util.Fxml;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.Dialog;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Programmer Hrishav
 */
public class ImageChooserController extends AnchorPane {

    @FXML
    private JFXButton takephoto;

    @FXML
    private JFXButton gallery;

    private FXMLLoader fxml;
    private static Dialog<Image> dialog;
    

    protected ImageChooserController() {
        fxml = Fxml.getImageChooserFxml();
        fxml.setRoot(this);
        fxml.setController(this);

        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ImageChooserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e->{
            dialog.hide();
        });
        takephoto.setOnAction(e -> {
            Services.get(PicturesService.class).ifPresent(c -> {
                c.takePhoto(true).ifPresent(img -> ImageChooserController.dialog.setResult(img));
                dialog.hide();
            });
        });
        
        gallery.setOnAction(e -> {
            Services.get(PicturesService.class).ifPresent(c -> {
                c.loadImageFromGallery().ifPresent(img -> ImageChooserController.dialog.setResult(img));
                dialog.hide();
            });
        });
    }

    public static Image show(){
        dialog=new Dialog<>();
        dialog.setContent(new ImageChooserController());
        dialog.getButtons().clear();
        dialog.setTitle(null);
        return dialog.showAndWait().get();
    }

}
