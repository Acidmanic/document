/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.render;

import com.acidmanic.document.extention.DocumentProcessingDefinition;
import com.acidmanic.document.structure.DocumentAdapter;
import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.document.structure.scan.DocumentScanner;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class RenderEngine {

    public void render(HashMap<Class, Renderer> renderers, DocumentAdapter adapter) {

        DocumentScanner scanner = new DocumentScanner(adapter);

        Object root = scanner.getRoot();

        scanner.scan((key, node) -> {

            if (node != null) {

                Class type = node.getClass();

                if (renderers.containsKey(type)) {

                    Renderer renderer = renderers.get(type);

                    renderer.render(key, node, root);
                }
            }

        });
    }

    public void render(HashMap<Class, Renderer> renderers,
            PropertyMapper[] structure,
            boolean keyCaseSensitive,
            Object contentObject) {

        PropertyMappedDocumentAdapter adapter
                = new PropertyMappedDocumentAdapter(keyCaseSensitive, structure, contentObject);

        this.render(renderers, adapter);
    }

    public void render(DocumentProcessingDefinition definition, Object contentObject) {

        this.render(definition.provideRenderers(),
                definition.getLeafPointerKeysProperties(),
                definition.keyCaseSensitive(),
                contentObject);
    }
}
