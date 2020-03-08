/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.model;

/**
 *
 * @author pc
 */
public class Details {
    
    private String academicyear;
    private String year;
    private String coursetype;
    private String date;
    private String department;
    private String faculty;
    private String subject;
    private String time;

    public Details() {
    }

    public Details(String academicyear, String year, String coursetype, String date, String department, String faculty, String subject, String time) {
        this.academicyear = academicyear;
        this.year = year;
        this.coursetype = coursetype;
        this.date = date;
        this.department = department;
        this.faculty = faculty;
        this.subject = subject;
        this.time = time;
    }

    public String getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
}
