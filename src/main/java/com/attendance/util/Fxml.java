/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author pc
 */
public class Fxml {

    private static String path = "";

    public static FXMLLoader getSplashFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getLoginFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getDashboardFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getNotesDashboardFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getAttendanceFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getSelectDepartmentFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getSelectLoginFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getEditProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }
    
    public static FXMLLoader getUploadNotesFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+""));
    }
    
    public static FXMLLoader getDownloadNotesFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+""));
    }
}
