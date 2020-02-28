/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.attendance.dashboard.DashboardController;
import com.attendance.file.chooser.controller.FileChooserController;
import com.attendance.splash.SplashController;
import com.attendance.user.controller.EditProfileController;
import com.attendance.user.controller.LoginController;
import com.attendance.user.controller.LoginTypeController;
import com.attendance.user.controller.ProfileController;
import com.gluonhq.charm.glisten.mvc.View;
import com.attendance.notes.controller.DownloadNotesController;
import com.attendance.notes.controller.NotesDashboardController;
import com.attendance.notes.controller.UploadNotesController;

/**
 *
 * @author pc
 */
public class ViewResolver {
    public View getSplashView() {
        return new SplashController();
    }
    
    public View getLoginView(String type) {
        return new LoginController(type);
    }
    
    public View getDashboardView() {
        return new DashboardController();
    }
    
    public View getNotesDashboardView() {
        return new NotesDashboardController();
    }
    
    public View getAttendanceView() {
        return new AttendanceController();
    }
    
    public View getProfileView() {
        return new ProfileController();
    }
    
    public View getSelectDepartmentView() {
        return new SelectDepartmentController();
    }
    
    public View getSelectLoginView() {
        return new LoginTypeController();
    }
    
    public View getEditProfileView() {
        return new EditProfileController();
    }
    
    public View getUploadNotesView() {
        return new UploadNotesController();
    }
    
    public View getDownloadNotesView() {
        return new DownloadNotesController();
    }
    
    public View getFileChooserView() {
        return new FileChooserController();
    }
}
