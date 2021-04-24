/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure.propertymapped;

import com.acidmanic.document.structure.DocumentAdapter;
import com.acidmanic.document.structure.Key;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public abstract class PropertyMappedDocumentAdapter implements DocumentAdapter {

    private final PropertyMapper[] properties;
    private final Object rootObject;

    public PropertyMappedDocumentAdapter(PropertyMapper[] properties, Object rootObject) {
        this.properties = properties;
        this.rootObject = rootObject;
    }

    @Override
    public List<Key> getChilds(Key key) {

        ArrayList<Key> childs = new ArrayList<>();

        if (key.segmentsCount() <= properties.length) {

            Object pointingObject = getContent(key);

            if (pointingObject != null) {

                PropertyMapper mapper = properties[key.segmentsCount() - 1];

                List<String> childSegments = mapper.keySegmentValues(pointingObject);

                for (String segment : childSegments) {

                    Key child = new Key(key);

                    child.append(segment);

                    childs.add(child);
                }
            }
        }
        return childs;
    }

    @Override
    public Object getContent(Key key) {
        if (key.segmentsCount() == 0) {
            return rootObject;
        }
        if (key.segmentsCount() <= properties.length) {
         
            Object object = rootObject;
            
            for(int segmentIndex =0;segmentIndex<key.segmentsCount();segmentIndex++){
                
                PropertyMapper mapper = properties[segmentIndex];
                
                String keySegmentValue = key.segment(segmentIndex);
                
                object = mapper.propertyValue(object,keySegmentValue);
                
                if(object==null){
                    break;
                }
            }
            return object;
        }
        return null;
    }

}
