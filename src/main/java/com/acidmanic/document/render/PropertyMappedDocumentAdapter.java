/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.render;

import com.acidmanic.document.structure.propertymapped.PropertyMappedDocumentAdapterBase;
import com.acidmanic.document.structure.propertymapped.PropertyMapper;

/**
 *
 * @author diego
 */
class PropertyMappedDocumentAdapter extends PropertyMappedDocumentAdapterBase {

    private final boolean keyCaseSensitive;

    public PropertyMappedDocumentAdapter(boolean keyCaseSensitive, PropertyMapper[] properties, Object rootObject) {
        super(properties, rootObject);
        this.keyCaseSensitive = keyCaseSensitive;
    }

    @Override
    public boolean keyCaseSensitive() {
        return this.keyCaseSensitive;
    }

}
