/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.extention;

import com.acidmanic.document.render.Renderer;
import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public interface DocumentProcessingDefinition {
    
    
    PropertyMapper[] getLeafPointerKeysProperties();
    
    HashMap<Class, Renderer> provideRenderers();
    
    boolean keyCaseSensitive();
}
