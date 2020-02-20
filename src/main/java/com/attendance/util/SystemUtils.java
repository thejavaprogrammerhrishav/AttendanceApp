/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.gluonhq.charm.glisten.application.MobileApplication;

/**
 *
 * @author pc
 */
public class SystemUtils {
    private static MobileApplication application;
    private static ViewResolver resolver = null;
    
    public static void init(){
        if(resolver==null){
            resolver = new ViewResolver();
        }
    }
    public static ViewResolver getResolver(){
        return resolver;
    }

    public static void setApplication(MobileApplication application) {
        SystemUtils.application = application;
    }
   
}
