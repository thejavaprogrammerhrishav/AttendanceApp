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

    public static FXMLLoader getProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "profile.fxml"));
    }

    public static FXMLLoader getSelectDepartmentFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "department.fxml"));
    }

    public static FXMLLoader getSelectLoginFxml() {
        return new FXMLLoader(Fxml.class.getResource(path + "usertype.fxml"));
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
    
    public static FXMLLoader getFileChooserOrderFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"order.fxml"));
    }
    
    public static FXMLLoader getFileChooserOtherFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"other.fxml"));
    }
    
    public static FXMLLoader getFileChooserViewFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"view.fxml"));
    }
    
    public static FXMLLoader getNewFolderFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"newfolder.fxml"));
    }
    
    public static FXMLLoader getUpdateAttendanceFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"updateattendance.fxml"));
    }
    
    public static FXMLLoader getAttendanceNodeFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"attendancenode.fxml"));
    }
    
    public static FXMLLoader getLoadStudentFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"loadstudent.fxml"));
    }
    
    public static FXMLLoader getEditProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"editmyprofile.fxml"));
    }
    
    public static FXMLLoader getLoginActivityNodeFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"loginactivitynode.fxml"));
    }
    
    public static FXMLLoader getMyProfileFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"myprofile.fxml"));
    }
    
    public static FXMLLoader getResetPasswordFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"resetyourpassword.fxml"));
    }
    
    public static FXMLLoader getLoginActivityFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"loginactivity.fxml"));
    }
    
    public static FXMLLoader getViewStudentsFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewstudents.fxml"));
    }
    
    public static FXMLLoader getViewStudentsFilterFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewstudentsfilter.fxml"));
    }
    
    public static FXMLLoader getResetPasswordResultFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"resetpasswordresult.fxml"));
    }
    
    public static FXMLLoader getViewFacultyFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewfaculty.fxml"));
    }
    
    public static FXMLLoader getViewStudentListFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewstudentslist.fxml"));
    }
    
    public static FXMLLoader getViewStudentsListFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewstudentslist.fxml"));
    }
    
    public static FXMLLoader getViewStudentsDetailsFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewstudentsdetails.fxml"));
    }
    
    public static FXMLLoader getVIewFacultyDetailsFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewfacultydetails.fxml"));
    }
    
    public static FXMLLoader getSecurityQuestionsFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"securityquestions.fxml"));
    }
    
    public static FXMLLoader getImageChooserFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"imagechooser.fxml"));
    }
    
    public static FXMLLoader getViewFacultyNodeFxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"viewfacultylist.fxml"));
    }
    
    public static FXMLLoader getForgotPassword1Fxml() {
        return new FXMLLoader(Fxml.class.getResource(path+"forgotpassword.fxml"));
    }
    
}
