/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure;

import java.util.List;

/**
 *
 * @author diego
 */
public interface DocumentAdapter {

    List<Key> getChilds(Key key);

    Object getContent(Key key);
    
    boolean keyCaseSensitive();
}
