/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.attendance.student.attendance.model.ClassDetails;
import com.attendance.student.attendance.model.MyClassDetails;

/**
 *
 * @author Programmer Hrishav
 */
public class CDUtils {

    public static MyClassDetails getDetails(ClassDetails d) {
        return new MyClassDetails(d.getClassId(), d.getFacultyName(), d.getSubjectTaught(), d.getDate(), d.getTime(), d.getSemester(), d.getYear(), d.getPaper(), d.getAcadamicyear(), d.getDepartment(), d.getCoursetype());
    }
}
