/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.file.chooser.controller.util.FileChooserUtils;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.VIDEO_FORMATS;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.ImageUtils;
import com.attendance.util.ImageUtils.Icons;
import com.attendance.util.SystemUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class FileChooserNodeController extends AnchorPane {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label size;

    @FXML
    private Label date;

    private FXMLLoader fxml;
    private ExceptionDialog dialog;

    private FileChooserController controller;
    private String path;
    private boolean isDirectory;
    private long lastModified;
    private long length;
    private boolean colored = false;

    public FileChooserNodeController(FileChooserController controller, String path, boolean isDirectory) {
        this.controller = controller;
        this.path = path;
        this.isDirectory = isDirectory;
        fxml = Fxml.getFileChooserNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        FileChooserNodeController.this.setStyle("-fx-background-color: transparent;");
        String path = controller.path() + "/" + this.path;
        lastModified = new File(path).lastModified();
        length = new File(path).length();
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(lastModified)));
        name.setText(this.path);
        if (isDirectory) {
            directory();
        } else {
            file();
        }
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getPath() {
        return path;
    }

    public Long getSize() {
        return length;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    private void directory() {
        size.setText("");
        image.setImage(ImageUtils.getImage(Icons.FOLDER));
        image.setOnMouseClicked(e -> {
            FileChooserController.stack.push(this.path);
            String temp = controller.path();
            if (controller.size() == 1) {
                temp = temp + "/";
            }
            controller.refresh(new File(temp));
            controller.setPath(controller.path());
            FileChooserController.selpath = controller.path();
        });
    }

    private void file() {
        String path = controller.path() + "/" + this.path;
        try {
            String ext = this.path.substring(this.path.lastIndexOf(".") + 1).toLowerCase();
            if (VIDEO_FORMATS.contains(ext)) {
                ext = "video";
            }
            image.setImage(ImageUtils.getImage(Icons.valueOf(ext.toUpperCase())));
        } catch (Exception e) {
            image.setImage(ImageUtils.getImage(Icons.FILE));
        }
        size.setText(SystemUtils.getFileSize(new File(path)));
        image.setOnMouseClicked(e -> {
            FileChooserController.selpath = controller.path() + "/" + this.path;
            controller.decolor();
            if (colored) {
                reset();
                colored = false;
            } else {
                color();
                colored = true;
            }
        });
    }

    public void reset() {
        FileChooserNodeController.this.setStyle("-fx-background-color: transparent;");
        FileChooserNodeController.this.name.setStyle("-fx-text-fill: #364eff; -fx-font-size: 16px;");
        FileChooserNodeController.this.date.setStyle("-fx-text-fill: #9f9f9f; -fx-font-size: 12px;");
        FileChooserNodeController.this.size.setStyle("-fx-text-fill: #9f9f9f; -fx-font-size: 12px;");
    }

    public void color() {
        FileChooserNodeController.this.setStyle("-fx-background-color: green;");
        FileChooserNodeController.this.name.setStyle("-fx-text-fill: #fff; -fx-font-size: 16px;");
        FileChooserNodeController.this.date.setStyle("-fx-text-fill: lime; -fx-font-size: 12px;");
        FileChooserNodeController.this.size.setStyle("-fx-text-fill: lime; -fx-font-size: 12px;");
    }
    
    
}
