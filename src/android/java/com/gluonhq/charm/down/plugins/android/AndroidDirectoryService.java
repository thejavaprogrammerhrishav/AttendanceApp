/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gluonhq.charm.down.plugins.android;

import com.gluonhq.charm.down.plugins.DirectoryService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafxports.android.FXActivity;

/**
 *
 * @author pc
 */
public class AndroidDirectoryService implements DirectoryService {

    @Override
    public List<String> getRootDirs() {
        File[] dirs = FXActivity.getInstance().getBaseContext().getExternalFilesDirs(null);
        return Arrays.asList(dirs).stream().map(File::getAbsolutePath).map(p->p.substring(0,p.indexOf("Android"))).collect(Collectors.toList());

    }
}
