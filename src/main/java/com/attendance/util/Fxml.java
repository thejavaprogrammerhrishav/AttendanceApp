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

    private static String path = "/com/attendance/fxml/";

    public static FXMLLoader getSplashFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "welcome"));
    }

    public static FXMLLoader getLoginFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "login.fxml"));
    }

    public static FXMLLoader getDashboardFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "dashboard.fxml"));
    }

    public static FXMLLoader getNotesDashboardFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "notesdashboard.fxml"));
    }

    public static FXMLLoader getAttendanceFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + ""));
    }

    public static FXMLLoader getProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "profile.fxml"));
    }

    public static FXMLLoader getSelectDepartmentFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "department.fxml"));
    }

    public static FXMLLoader getSelectLoginFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "usertype.fxml"));
    }

    public static FXMLLoader getEditProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "editprofile.fxml"));
    }
    
    public static FXMLLoader getUploadNotesFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"uploadnotes.fxml"));
    }
    
    public static FXMLLoader getDownloadNotesFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"download.fxml"));
    }
    
    public static FXMLLoader getFileChooserFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"filechooser.fxml"));
    }
    
    public static FXMLLoader getFileChooserNodeFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"filechoosernode.fxml"));
    }
}
