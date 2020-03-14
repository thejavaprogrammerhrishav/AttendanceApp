/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.gluonhq.charm.glisten.control.Alert;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Programmer Hrishav
 */
public class ExceptionDialog {

    private Alert utils;

    public ExceptionDialog() {

    }

    public void showInformation(Parent parent, String content) {
        utils = new Alert(AlertType.NONE, content);
        utils.showAndWait();
    }

    public void showError(Parent parent, String content) {
        utils = new Alert(AlertType.ERROR, content);
        utils.showAndWait();
    }

    public void showWarning(Parent parent, String content) {
        utils = new Alert(AlertType.WARNING, content);
        utils.showAndWait();
    }

    public void showSuccess(Parent parent, String content) {
        utils = new Alert(AlertType.INFORMATION, content);
        utils.showAndWait();
    }
}
