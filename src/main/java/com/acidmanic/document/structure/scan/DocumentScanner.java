/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure.scan;

import com.acidmanic.delegates.arg3.Action;
import com.acidmanic.document.structure.DocumentAdapter;
import com.acidmanic.document.structure.Key;
import java.util.List;

/**
 *
 * @author diego
 */
public class DocumentScanner {

    private final DocumentAdapter documentAdapter;

    public DocumentScanner(DocumentAdapter documentAdapter) {
        this.documentAdapter = documentAdapter;
    }

    public Object getRoot() {

        boolean keyCaseSensitive = this.documentAdapter.keyCaseSensitive();

        Key root = new Key(keyCaseSensitive);

        Object rootObject = this.documentAdapter.getContent(root);

        return rootObject;
    }

    public void scan(Action<Key, Object, List<Key>> entryScanner) {

        boolean keyCaseSensitive = this.documentAdapter.keyCaseSensitive();

        Key root = new Key(keyCaseSensitive);

        Object rootObject = this.documentAdapter.getContent(root);

        scan(entryScanner, root, rootObject);
    }

    private void scan(Action<Key, Object, List<Key>> entryScanner, Key parentKey, Object parent) {

        List<Key> childKeys = this.documentAdapter.getChilds(parentKey);

        entryScanner.perform(parentKey, parent, childKeys);

        for (Key childKey : childKeys) {

            Object childContent = this.documentAdapter.getContent(childKey);

            scan(entryScanner, childKey, childContent);
        }
    }

}
