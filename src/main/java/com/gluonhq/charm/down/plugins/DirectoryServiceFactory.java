/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gluonhq.charm.down.plugins;

import com.gluonhq.charm.down.DefaultServiceFactory;

/**
 *
 * @author pc
 */
public class DirectoryServiceFactory extends DefaultServiceFactory<DirectoryService>{
    
    public DirectoryServiceFactory() {
        super(DirectoryService.class);
    }
    
}
