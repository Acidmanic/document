/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure.propertymapped;

import java.util.List;

/**
 *
 * @author diego
 */
public interface PropertyMapper {
    
    List<String> keySegmentValues(Object parent);
    
    String keySegmentName();
    
    Class parentType();
    
    Class valueType();
    
    Object propertyValue(Object parent,String keySegmentValue);
}
