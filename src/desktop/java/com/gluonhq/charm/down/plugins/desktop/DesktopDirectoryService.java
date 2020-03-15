/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gluonhq.charm.down.plugins.desktop;


import com.gluonhq.charm.down.plugins.DirectoryService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author pc
 */
public class DesktopDirectoryService implements DirectoryService {

    @Override
    public List<String> getRootDirs() {
        File[] listRoots = File.listRoots();
        return Arrays.asList(listRoots).stream().map(f->f.getAbsolutePath().replace("\\", "")).collect(Collectors.toList());
    }

}
