/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.document.structure.scan;

import com.acidmanic.delegates.arg2.Action;
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

    public void scan(Action<Key, Object> entryScanner) {

        boolean keyCaseSensitive = this.documentAdapter.keyCaseSensitive();

        Key root = new Key(keyCaseSensitive);

        Object rootObject = this.documentAdapter.getContent(root);

        scan(entryScanner, root, rootObject);
    }

    private void scan(Action<Key, Object> entryScanner, Key parentKey, Object parent) {

        entryScanner.perform(parentKey, parent);

        List<Key> childKeys = this.documentAdapter.getChilds(parentKey);

        for (Key childKey : childKeys) {

            Object childContent = this.documentAdapter.getContent(childKey);

            scan(entryScanner, childKey, childContent);
        }
    }

}
