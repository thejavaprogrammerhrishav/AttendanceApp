/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Programmer Hrishav
 */
public class FileChooserUtils {
    public static final int IMAGE=100;
    public static final int VIDEO=200;
    public static final int AUDIO=300;
    public static final int PDF=400;
    public static final int ALL=1000;
    
    public static final List<String> IMAGE_FORMATS=new ArrayList<>(Arrays.asList("jpg","png","jpeg","gif"));
    public static final List<String> VIDEO_FORMATS=new ArrayList<>(Arrays.asList("mp4","mkv","flv","mpeg","avi","webm","mov","ogg"));
    public static final List<String> AUDIO_FORMATS=new ArrayList<>(Arrays.asList("mp3","m4a","wav"));
    
    public static boolean validate(String path,int type){
        String ext=path.substring(path.lastIndexOf(".")+1);
        switch(type){
            case IMAGE: return IMAGE_FORMATS.stream().filter(f->f.toLowerCase().equals(ext.toLowerCase())).collect(Collectors.toList()).size()!=0;
            case VIDEO: return VIDEO_FORMATS.stream().filter(f->f.toLowerCase().equals(ext.toLowerCase())).collect(Collectors.toList()).size()!=0;
            case AUDIO: return AUDIO_FORMATS.stream().filter(f->f.toLowerCase().equals(ext.toLowerCase())).collect(Collectors.toList()).size()!=0;
            case PDF: return ext.toLowerCase().equals("pdf");
            default: return false;   
        }
    }
    
}
