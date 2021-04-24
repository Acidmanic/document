/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.render;

import com.acidmanic.document.structure.Key;

/**
 *
 * @author diego
 */
public interface Renderer {
 
    void render(Key key,Object node,Object root);
}
