/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.attendance.dashboard.DashboardController;
import com.attendance.department.SelectDepartmentController;
import com.attendance.faculty.controller.ViewFacultyListController;
import com.attendance.login.forgot.ForgotPassword1Controller;
import com.attendance.login.forgot.ResetPasswordController;
import com.attendance.login.forgot.SecurityQuestionsController;
import com.attendance.loginactivity.controller.LoginActivityController;
import com.attendance.splash.SplashController;
import com.attendance.user.controller.EditProfileController;
import com.attendance.user.controller.LoginController;
import com.attendance.user.controller.LoginTypeController;
import com.attendance.user.controller.ProfileController;
import com.gluonhq.charm.glisten.mvc.View;
import com.attendance.notes.controller.DownloadNotesController;
import com.attendance.notes.controller.NotesDashboardController;
import com.attendance.notes.controller.UploadNotesController;
import com.attendance.routine.RoutineManagement;
import com.attendance.settings.SettingsController;
import com.attendance.splash.AboutUsController;
import com.attendance.student.attendance.controller.ClassDetailsController;
import com.attendance.student.attendance.controller.LoadStudentController;
import com.attendance.student.controller.ViewStudentsController;
import com.attendance.user.request.UserAccountRequest;

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
        return new LoadStudentController();
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
    
    public View getLoginActivityView() {
        return new LoginActivityController();
    }
    
    public View getStudentDetailsView() {
        return new ViewStudentsController();
    }
    
    public View getFacultyDetailsView() {
        return new ViewFacultyListController();
    }
    
    public View getForgotPasswordView() {
        return new ForgotPassword1Controller();
    }
    
    public View getSecurityQuestionView() {
        return new SecurityQuestionsController();
    }
    
    public View getResetPasswordView() {
        return new ResetPasswordController();
    }
    
    public View getClassDetailsView() {
        return new ClassDetailsController();
    }
    
    public View getSettingsView() {
        return new SettingsController();
    }
    
    public View getAboutUsView() {
        return new AboutUsController();
    }
    
    public View getUserAccountRequestView() {
        return new UserAccountRequest();
    }
    
    public View getRoutineManagementView() {
        return new RoutineManagement();
    }
}
